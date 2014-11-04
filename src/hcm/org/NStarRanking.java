package hcm.org;


import hcm.org.demo.PrintGraph;
import hcm.org.nstar.model.NStarRank;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.operator.ports.PortPairExtender;

public class NStarRanking extends Operator{

	private InputPort exampleSetInput1 =getInputPorts().createPort("example input set 1",ExampleSet.class); 
	private InputPort exampleSetInput2 =getInputPorts().createPort("example input set 2",ExampleSet.class);
	private InputPort exampleSetInput3 =getInputPorts().createPort("example input set 3",ExampleSet.class);
	private InputPort exampleSetInput4 =getInputPorts().createPort("example input set 4",ExampleSet.class);


	
	private OutputPort exampleSetOutput1 =getOutputPorts().createPort("example output set 1");
	private OutputPort exampleSetOutput2 =getOutputPorts().createPort("example output set 2");

	
	private InputPort []exampleSetInputs=new InputPort[4];/*{
			getInputPorts().createPort("example input set 11",ExampleSet.class),
			getInputPorts().createPort("example input set 12",ExampleSet.class),
			getInputPorts().createPort("example input set 13",ExampleSet.class),
			getInputPorts().createPort("example input set 14",ExampleSet.class)
	};*/
	
		
	
	public NStarRanking(OperatorDescription description) {
		super(description);
		/*exampleSetInput1.addPrecondition(
				new SimplePrecondition(exampleSetInput1, 
						new MetaData(ExampleSet.class)));*/
		for (int j = 0; j < 5; j++) {
			getInputPorts().createPort("name "+j);
		}
	}

	@Override
	public void doWork() throws OperatorException {
		//1. get input data
		ExampleSet exampleSet1=exampleSetInput1.getData(ExampleSet.class);
		ExampleSet exampleSet2=exampleSetInput2.getData(ExampleSet.class);
		ExampleSet exampleSet3=exampleSetInput3.getData(ExampleSet.class);
		ExampleSet exampleSet4=exampleSetInput4.getData(ExampleSet.class);
		
		
		//2. generate graph from input data
		GenGraph x=new GenGraph();
		/*x.genGraph(exampleSet1,"publication"); 
		x.genGraph(exampleSet2,"keywords"); 
		x.genGraph(exampleSet3,"keywords_publication"); 
		x.genGraph(exampleSet4,"publication_publication");*/
		
		//problem: how to known what is vertex/edge from input exampleSet in RM
		
		String vertexType1=exampleSet1.getSource().replace("Retrieve ", "");//Retrieve Papers
		String vertexType2=exampleSet1.getSource().replace("Retrieve ", "");//Retrieve Papers
		String vertexType3=exampleSet1.getSource().replace("Retrieve ", "");//Retrieve Papers
		String vertexType4=exampleSet1.getSource().replace("Retrieve ", "");//Retrieve Papers
		
		x.genGraph(exampleSet1,"vertex",vertexType1); 
		x.genGraph(exampleSet2,"vertex",vertexType2); 

		x.genGraph(exampleSet3,"edge","from type???????????","to type"); 
		x.genGraph(exampleSet4,"edge","from type","to type");
		
		//so egde tuy thuoc vao file tham so ban dau
		//x.genGraph(exampleSet4,"edge","from type","to type");
		//x.genGraph(exampleSet4,"edge","from type","to type");
		
		Graph<Object, DefaultEdge> graph=x.getGraph();
		
		//debug purpose
		System.out.println("--------------------------------------");
		PrintGraph.printObjectGraph(graph);
		System.out.println("--------------------------------------");
		
		
		//3. Chay thuat toan NStar
		ExampleSet []results=new NStarRank().evalByAlgorithm(graph);

		/*//4. Lay ket qua
		ExampleSet resultSet=genarateResult(result);*/

		//4. tra cho output
		//exampleSetOutput.deliver(resultSet);
		exampleSetOutput1.deliver(results[0]);   
		exampleSetOutput2.deliver(results[1]);   
	}

}
/*Attributes attibutes = exampleSet.getAttributes();
Attribute sourceAttribute=attibutes.get("relative time");
String newName="date("+sourceAttribute.getName()+")";
Attribute targetAttribute=AttributeFactory.createAttribute(newName,Ontology.DATE_TIME);
targetAttribute.setTableIndex(sourceAttribute.getTableIndex());
attibutes.addRegular(targetAttribute);
attibutes.remove(sourceAttribute);
for(Example example:exampleSet){
	double timeStamValue=example.getValue(targetAttribute);
	example.setValue(targetAttribute, timeStamValue*1000);
}*/