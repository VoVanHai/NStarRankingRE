package hcm.org;

import hcm.org.nstar.model.Keyword;
import hcm.org.nstar.model.Publication;

import java.util.HashMap;
import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import com.rapidminer.example.Attribute;
import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;

public class GenGraph {
	private Graph<Object, DefaultEdge> graph=null;
	private HashMap<String, Publication> listPubication;
	/*private HashMap<String, Publication> listKeyword;
	private HashMap<String, Publication> listKeyword;*/

	public Graph<Object, DefaultEdge> getGraph() {
		return graph;
	}

	public GenGraph() {
		if(listPubication!=null)
			listPubication=new HashMap<String, Publication>();
		if(graph==null)
			graph=new DirectedWeightedMultigraph<Object, DefaultEdge>(DefaultEdge.class);
	}

	public void genGraph(ExampleSet exampleSet,String type) {
		Iterator<Example> itor = exampleSet.iterator();
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
		}
	}


	private Object findVertex(Attribute attIdPaper) {
		return null;
	}

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
	}

}
