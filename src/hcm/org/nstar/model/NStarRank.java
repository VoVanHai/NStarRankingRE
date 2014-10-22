package hcm.org.nstar.model;

import org.jgrapht.Graph;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.ExampleSetFactory;

public class NStarRank{

	//va tham so la mot ma tran
	//???
	private int [][]params;

	public int[][] getParams() {
		return params;
	}

	public void setParams(int[][] params) {
		this.params = params;
	}

	public ExampleSet[] evalByAlgorithm(Graph<?, ?> graph) {

		doAl();

		double[][] data1={
				{1,2,3,4},
				{5,6,7,8}
		};
		double[][] data2={
				{1,2,3,5},
				{5,6,7,9}
		};
		ExampleSet s1=ExampleSetFactory.createExampleSet(data1);
		ExampleSet s2=ExampleSetFactory.createExampleSet(data2);
		return new ExampleSet[]{s1,s2};
	}

	private void doAl() {
		
	}

}
