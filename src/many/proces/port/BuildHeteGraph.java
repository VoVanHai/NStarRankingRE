package many.proces.port;

import java.util.Iterator;

import my.graph.TypedVerTex;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;

public class BuildHeteGraph {
	private Graph<TypedVerTex, DefaultEdge> graph=null;
	public Graph<TypedVerTex, DefaultEdge> getGraph() {
		return graph;
	}
	public BuildHeteGraph() {
		if(graph==null)
			graph=new DirectedWeightedMultigraph<TypedVerTex, DefaultEdge>(DefaultEdge.class);
	}
	/**
	 * build graph
	 * @param exampleSet
	 * @param graphType: "vertex" or "edge"
	 * @param objectType: type of object to add (use to determine later)
	 */
	public void addVertex(ExampleSet exampleSet, ObjectTypeOfGraph graphType, String objectType) {
		Iterator<Example> itor = exampleSet.iterator();
		if(graphType==ObjectTypeOfGraph.VERTEX){
			while(itor.hasNext()){
				Example ex=itor.next();
				TypedVerTex tv=new TypedVerTex(objectType,ex);
				graph.addVertex(tv);
				System.out.println("**Add vertex "+tv);
			}
		}
	}
	public void addVertex(TypedVerTex tVertex) {
		graph.addVertex(tVertex);
	}

	public TypedVerTex findVertex(String keyValue,String vertexType) {
		BreadthFirstIterator<TypedVerTex, DefaultEdge>itor
			=new BreadthFirstIterator<TypedVerTex, DefaultEdge>(graph);
		//DepthFirstIterator<TypedVerTex, DefaultEdge>itor=new DepthFirstIterator<TypedVerTex, DefaultEdge>(graph);
		while(itor.hasNext()){
			Object obj = itor.next();
			TypedVerTex vertex=(TypedVerTex)obj;
			//not state node
			if(!vertex.getType().equalsIgnoreCase("state"))
				if(vertex.getId().equalsIgnoreCase(keyValue)
						&& vertex.getType().equalsIgnoreCase(vertexType)){
					return vertex;
				}
		}
		//System.out.println("***Not found <"+keyValue+" - "+vertexType);
		return null;
	}
	public void addEdge(ExampleSet exampleSet, String from, String to) {
		Iterator<Example> itor = exampleSet.iterator();	
		while(itor.hasNext()){
			Example ex=itor.next();
			Attributes atts = ex.getAttributes();
			Iterator<Attribute> it = atts.iterator();

			Attribute attSource=it.next();
			Attribute attDest=it.next();

			String valSource=ex.getValueAsString(attSource);
			String valDest=ex.getValueAsString(attDest);
			TypedVerTex fromVertex = findVertex(valSource, from);
			TypedVerTex toVertex = findVertex(valDest, to);
			System.out.print("--add edge from "+fromVertex+" to "+toVertex);
			if(fromVertex!=null && toVertex!=null){
				graph.addEdge(fromVertex, toVertex);
				System.out.println("==>Successed");
			}
			else System.out.println("==>failed");
		}
	}

	public DefaultEdge addEdge(TypedVerTex sourceVertex, TypedVerTex targetVertex){
		return graph.addEdge(sourceVertex, targetVertex);
	}
}
