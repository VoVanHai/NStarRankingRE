package many.proces.port;

import hcm.org.demo.PrintGraph;
import hcm.utils.Tuple;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import my.graph.TypedVerTex;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.UserError;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.tools.Observable;
import com.rapidminer.tools.Observer;

public class NStarParametperPort extends Operator{


	private InputPort exampleSetInput =getInputPorts().createPort("example input set 1",ExampleSet.class);

	private OutputPort exampleSetOutput1 =getOutputPorts().createPort("NoTypes,	NoRelations");
	//private OutputPort exampleSetOutput2 =getOutputPorts().createPort("RelationDetails");

	public NStarParametperPort(OperatorDescription description) {
		super(description);
		addObserver(new Observer<Operator>() {
			@Override
			public void update(Observable<Operator> observable, Operator op) {
				if(op!=null)
					if(op.getInputPorts()!=null){
						int ports=op.getInputPorts().getNumberOfPorts();
						List<InputPort> allPorts = op.getInputPorts().getAllPorts();
						int count=0;
						for(InputPort p:allPorts){
							if(p.isConnected())
								count++;
						}
						//create & add new port when number of ports is full
						if(count==ports)
							op.getInputPorts().createPort("inp#"+count+1);
					}
			}
		}, true);
	}
	public void doWork() throws OperatorException {
		//read params of NoTypes,NoRelations,RelationDetails
		Tuple<double[][], String[][]>params=readParams();

		//generate heterogeneous graph from data
		Graph<TypedVerTex, DefaultEdge> graph=generateGraph(params);

		System.out.println("----------------------------------");
		PrintGraph.printGraph(graph);
	}
	/**
	 * 
	 * @param params
	 * @throws UserError
	 */
	private Graph<TypedVerTex, DefaultEdge> generateGraph(Tuple<double[][], String[][]> params) throws UserError {
		double[][] data1=params.getKey();
		//String [][]data2=params.getValue();

		int noTypes=(int)data1[0][0];
		int noNoRels=(int)data1[0][1];

		//------------
		BuildHeteGraph x=new BuildHeteGraph();
		List<InputPort> allPorts = this.getInputPorts().getAllPorts();
		if(allPorts.size()<2)
			throw new RuntimeException("invalid number of ports");

		//get all types port
		for (int i = 1; i <= noTypes; i++) {
			InputPort port=allPorts.get(i);
			if(port.isConnected()){
				ExampleSet exampleSet=port.getData(ExampleSet.class);
				String vertexType=exampleSet.getSource().replace("Retrieve ", "").trim();
				x.addVertex(exampleSet,ObjectTypeOfGraph.VERTEX,vertexType);
			}
		}

		//get All relation ports
		for (int i = noTypes+1; i <= noTypes+noNoRels; i++) {
			InputPort port=allPorts.get(i);
			if(port.isConnected()){
				ExampleSet exampleSet=port.getData(ExampleSet.class);
				String vertexType=exampleSet.getSource().replace("Retrieve ", "").trim();
				System.out.println("---reading relation exampleset "+vertexType);
				//tach ra 2 bang
				String []ss=vertexType.split("_");
				String fromType=ss[0];
				String toType=ss[1];
				//duyet du lieu
				Iterator<Example> itor = exampleSet.iterator();
				while(itor.hasNext()){
					Example ex = itor.next();
					Attributes atts = ex.getAttributes();
					Iterator<Attribute> it = atts.iterator();
					Attribute from=it.next();
					Attribute to=it.next();
					String fromVal=ex.getValueAsString(from);
					String toVal=ex.getValueAsString(to);
					TypedVerTex v1 = x.findVertex(fromVal, fromType);
					TypedVerTex v2 = x.findVertex(toVal, toType);
					//System.out.println(v1+";\t"+v2);
					if(v1!=null && v2!=null){
						//create state vertex and add to graph
						TypedVerTex state=new TypedVerTex("state", ex);
						x.addVertex(state);
						//add edge from v1->state
						x.addEdge(v1, state);
						//add edge from v2->state
						x.addEdge(v2, state);///state-->v2????
					}
				}
			}
		}
		return x.getGraph();
	}

	/**
	 * read input parameters
	 * @return
	 * @throws UserError
	 */
	private Tuple<double[][], String[][]> readParams() throws UserError {
		/* input data format form:
		 * NoTypes,	NoRelations,	RelationDetails
		 * 4,		4,				{<a,b>;<a,c>;<b;d>;...} 
		 */
		ExampleSet exampleSet=exampleSetInput.getData(ExampleSet.class);
		Iterator<Example> itor = exampleSet.iterator();
		Double noTypes=null;
		Double noRels=null;
		String [][]data2=null;
		if(itor.hasNext()){
			Example ex=itor.next();
			Attributes atts = ex.getAttributes();
			Iterator<Attribute> it = atts.iterator();

			Attribute attNoTypes=it.next();
			Attribute attNoRelations=it.next();
			Attribute attRelationDetails=it.next();
			noTypes=Double.parseDouble(ex.getValueAsString(attNoTypes));
			noRels=Double.parseDouble(ex.getValueAsString(attNoRelations));

			//{<1,2>;<1,3>;...}
			String details=ex.getValueAsString(attRelationDetails).toLowerCase();
			details=details.replace("{", "").replace("}", "").trim();
			StringTokenizer stk=new StringTokenizer(details,";");
			int noTokens=stk.countTokens();
			data2=new String[noTokens][2];
			int i=0;
			while(stk.hasMoreTokens()){
				String tk=stk.nextToken();
				tk=tk.replace("<", "").replace(">", "").trim();
				String []rels=tk.split("-");
				String first=rels[0];
				String second=rels[1];
				String []pairs={first,second};
				data2[i++]=pairs;
			}

		}
		double[][] data1={{noTypes,noRels}};
		Tuple<double[][], String[][]>result=new Tuple<double[][], String[][]>(data1,data2);

		ExampleSet eo=ExampleSetFactory.createExampleSet(data2);
		exampleSetOutput1.deliver(eo);   
		return result;
	}

}
