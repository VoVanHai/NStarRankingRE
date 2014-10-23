package hcm.org.demo;

import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

public class PrintGraph {
	public static void printGrah(Graph<Object, DefaultEdge>graph){
		
		System.out.println("No. Vertex = "+graph.vertexSet().size());
		System.out.println("No. Edge = "+graph.edgeSet().size());
        Iterator<Object> iter =new DepthFirstIterator<Object, DefaultEdge>(graph);
        Object vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            System.out.println(
                "Vertex " + vertex.toString() + " is connected to: "
                + graph.edgesOf(vertex).toString());
        }
	}
}
