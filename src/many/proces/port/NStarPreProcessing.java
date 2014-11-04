package many.proces.port;

import java.util.Iterator;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.operator.ports.OutputPort;

public class NStarPreProcessing extends Operator{

	private InputPort exampleSetInput1 =getInputPorts().createPort("example input set for NoTypes, NoRelations",ExampleSet.class);
	private InputPort exampleSetInput2 =getInputPorts().createPort("example input set for RelationDetails",ExampleSet.class);

	private OutputPort exampleSetOutput =getOutputPorts().createPort("hete graph");

	public NStarPreProcessing(OperatorDescription description) throws Exception{
		super(description);
		/*if(exampleSetInput1.isConnected()){
			ExampleSet exampleSet=exampleSetInput1.getData(ExampleSet.class);

			Iterator<Example> itor = exampleSet.iterator();
			Example ex = itor.next();
			Attributes atts = ex.getAttributes();
			Iterator<Attribute> it = atts.iterator();
			Attribute attNoTypes=it.next();
			//		Attribute attNoRelations=it.next();
			double dd=Double.parseDouble(ex.getValueAsString(attNoTypes));
			int numInputPorts=(int)dd;//get number of input port
			for (int i = 0; i < numInputPorts; i++) {
				getInputPorts().createPort("Types"+i);
			}
		}*/
	}

	@Override
	public void doWork() throws OperatorException {

	}
}
