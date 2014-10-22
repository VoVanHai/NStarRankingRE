package hcm.org;


import hcm.org.nstar.model.NStarRank;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;
import com.rapidminer.operator.ports.metadata.MetaData;
import com.rapidminer.operator.ports.metadata.SimplePrecondition;

public class NStarRanking extends Operator{

	private InputPort exampleSetInput1 =getInputPorts()
			.createPort("example input set 1",ExampleSet.class); 
	private InputPort exampleSetInput2 =getInputPorts()
			.createPort("example input set 2",ExampleSet.class);
	private InputPort exampleSetInput3 =getInputPorts()
			.createPort("example input set 3",ExampleSet.class);
	private InputPort exampleSetInput4 =getInputPorts()
			.createPort("example input set 4",ExampleSet.class);
	
	private OutputPort exampleSetOutput1 =getOutputPorts().createPort("example output set 1");
	private OutputPort exampleSetOutput2 =getOutputPorts().createPort("example output set 2");

	public NStarRanking(OperatorDescription description) {
		super(description);
		exampleSetInput1.addPrecondition(
				new SimplePrecondition(exampleSetInput1, 
						new MetaData(ExampleSet.class)));
	}

	@Override
	public void doWork() throws OperatorException {
		//1. lay du lieu dau vao
		ExampleSet exampleSet1=exampleSetInput1.getData(ExampleSet.class);
		System.out.println("-----finish ex01");
		ExampleSet exampleSet2=exampleSetInput2.getData(ExampleSet.class);
		System.out.println("-----finish ex02");
		ExampleSet exampleSet3=exampleSetInput3.getData(ExampleSet.class);
		System.out.println("-----finish ex02");
		ExampleSet exampleSet4=exampleSetInput4.getData(ExampleSet.class);
		System.out.println("-----finish ex04");
		
		//2. dua du lieu vao graph
		GenGraph x=new GenGraph();
		//publication
		x.genGraph(exampleSet1,"publication"); 
		x.genGraph(exampleSet2,"keywords"); 
		x.genGraph(exampleSet3,"keywords_publication"); 
		x.genGraph(exampleSet4,"publication_publication"); 
		Graph<Object, DefaultEdge> graph=x.getGraph();
		
		//3. Chay thuat toan NStar
		ExampleSet []results=new NStarRank().evalByAlgorithm(graph);

		/*//4. Lay ket qua
		ExampleSet resultSet=genarateResult(result);*/

		//4. tra cho output
		//exampleSetOutput.deliver(resultSet);
		exampleSetOutput1.deliver(results[0]);   
		exampleSetOutput2.deliver(results[1]);   
	}


	private ExampleSet genarateResult(Object result) {
		return null;
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