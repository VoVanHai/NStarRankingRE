package hcm.org;

import hcm.utils.Tuple;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;

public class GenGraph {
	private Graph<Object, DefaultEdge> graph=null;
	//private HashMap<String, Publication> listPubication;
	/*private HashMap<String, Publication> listKeyword;
	private HashMap<String, Publication> listKeyword;*/

	public Graph<Object, DefaultEdge> getGraph() {
		return graph;
	}

	public GenGraph() {
		/*if(listPubication!=null)
			listPubication=new HashMap<String, Publication>();*/
		if(graph==null)
			graph=new DirectedWeightedMultigraph<Object, DefaultEdge>(DefaultEdge.class);
		System.out.println("****create graph*****");
	}
	/**
	 * 
	 * @param exampleSet
	 * @param type
	 */
	public void genGraph_old(ExampleSet exampleSet,String type) {
		/*Iterator<Example> itor = exampleSet.iterator();
		if(type.equalsIgnoreCase("publication")){
			while(itor.hasNext()){
				Example ex=itor.next();
				Attributes atts = ex.getAttributes();
				Publication pub=createPub(ex,atts);
				graph.addVertex(pub);
				//listPubication.put(pub.getId(),pub);
			}
		}
		else if(type.equalsIgnoreCase("keywords")){
			while(itor.hasNext()){
				Example ex=itor.next();
				Attributes atts = ex.getAttributes();
				Keyword pub=createKeyword(atts);
				graph.addVertex(pub);
			}
		}
		else if(type.equalsIgnoreCase("keywords_publication")){
			while(itor.hasNext()){
				Example ex=itor.next();
				Attributes atts = ex.getAttributes();
				Iterator<Attribute> it = atts.iterator();
				Attribute attIdPaper=it.next();
				Attribute attIdKeyword=it.next();

				//tao dinh state gan giua pub va keyw
				Object state=new State(attIdPaper,attIdKeyword);
				graph.addVertex(state);

				Object sourceVertex=findVertex(attIdPaper);
				Object targetVertex=findVertex(attIdKeyword);
				graph.addEdge(sourceVertex, targetVertex);
				graph.addEdge(sourceVertex, state);
				graph.addEdge(targetVertex, state);
			}
		}
		else if(type.equalsIgnoreCase("publication_publication")){
			while(itor.hasNext()){
				Example ex=itor.next();
				Attributes atts = ex.getAttributes();
				Iterator<Attribute> it = atts.iterator();
				Attribute attIdPaper1=it.next();
				Attribute attIdPaper2=it.next();
				Object sourceVertex=findVertex(attIdPaper1);
				Object targetVertex=findVertex(attIdPaper2);
				graph.addEdge(sourceVertex, targetVertex);
				//edge.setValue=State
			}
		}*/
	}

	/*
	private Keyword createKeyword(Attributes atts) {
		return null;
	}

	private Publication createPub(Example ex, Attributes atts) {
		Publication pub=new Publication();
		Iterator<Attribute> it = atts.iterator();
		while(it.hasNext()){
			Attribute att=it.next();
			String name=att.getName();
			String val=ex.getValueAsString(att);
			if(name.equals("idPaper"))pub.setIdPaper(Long.parseLong(val));
		}

		return pub;
	}*/

	/**
	 * generate graph from an ExampleSet
	 * @param exampleSet
	 * @param type: "vertex" of graph OR "edge" of graph
	 */
	public void genGraph(ExampleSet exampleSet, String type) {
		Iterator<Example> itor = exampleSet.iterator();
		if(type.equalsIgnoreCase("vertex")){
			while(itor.hasNext()){
				Example ex=itor.next();
				Attributes atts = ex.getAttributes();
				//we can get id by using: Attribute id = atts.getId();
				
				Attribute attID=atts.getId();
				String key=ex.getValueAsString(attID);
				//HashMap<String, Attributes>vertex=new HashMap<String, Attributes>();
				Tuple<String, Attribute>vertex=new Tuple<String, Attribute>(key, attID);
				graph.addVertex(vertex);
				/*//debug purpose
				 if(atts!=null){
					Attribute att=atts.getId();
					if(att!=null){
						String val=ex.getValueAsString(att);
						String line=(att.getName()+"="+val)+";";
						System.out.println("--add vertex "+":\t"+line);
					}
				}*/
			}
		}
		else if(type.equalsIgnoreCase("edge")){
			while(itor.hasNext()){
				Example ex=itor.next();
				Attributes atts = ex.getAttributes();
				
				Iterator<Attribute> it = atts.iterator();
				//get ids in columns (relation in this situation must be 2 columns)
				Attribute attSource=it.next();
				Attribute attDest=it.next();
				String valSource=ex.getValueAsString(attSource);
				String valDest=ex.getValueAsString(attDest);
				
				Object sourceVertex=findVertex(valSource);
				Object targetVertex=findVertex(valDest);
				if(sourceVertex!=null && targetVertex!=null){
					graph.addEdge(sourceVertex, targetVertex);
					System.out.println("--add edge from "+valSource+" to "+valDest);
				}
			}
		}
	}
	/**
	 * find a vertex in graph
	 * @param keyValue is the key node
	 * @return a founded vertex in graph
	 */
	@SuppressWarnings("unchecked")
	private Object findVertex(String keyValue) {
		BreadthFirstIterator<Object, DefaultEdge>itor=new BreadthFirstIterator<Object, DefaultEdge>(graph);
		//DepthFirstIterator<Object, DefaultEdge>itor=new DepthFirstIterator<Object, DefaultEdge>(graph);
		while(itor.hasNext()){
			Object obj = itor.next();
			Tuple<String, Attribute>vertex=(Tuple<String, Attribute>)obj;
			
			if(vertex.getKey().equalsIgnoreCase(keyValue))
				return vertex;
		}
		return null;
	}

}
