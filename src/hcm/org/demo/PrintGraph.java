package hcm.org.demo;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import my.graph.TypedVerTex;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

public class PrintGraph { 
	public static void printGraph(Graph<TypedVerTex, DefaultEdge> graph){
		System.out.println("No. Vertex = "+graph.vertexSet().size());
		System.out.println("No. Edge = "+graph.edgeSet().size());
        Iterator<TypedVerTex> iter =new DepthFirstIterator<TypedVerTex, DefaultEdge>(graph);
        TypedVerTex vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            System.out.println(
                "Vertex " + vertex.toString() + " is connected to: "
                + graph.edgesOf(vertex).toString());
        }
	}
	public static void printGraph(Graph<TypedVerTex, DefaultEdge> graph,String filePath)throws Exception{
		PrintWriter out=new PrintWriter(new FileOutputStream(filePath),true);
		out.println("No. Vertex = "+graph.vertexSet().size());
		out.println("No. Edge = "+graph.edgeSet().size());
        Iterator<TypedVerTex> iter =new DepthFirstIterator<TypedVerTex, DefaultEdge>(graph);
        TypedVerTex vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            out.println(
                "Vertex " + vertex.toString() + " is connected to: "
                + graph.edgesOf(vertex).toString());
        }
        out.close();
	}
	public static void printObjectGraph(Graph<Object, DefaultEdge> graph){
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
	public static void printObjectGraph(Graph<Object, DefaultEdge> graph,String filePath)throws Exception{
		PrintWriter out=new PrintWriter(new FileOutputStream(filePath),true);
		
		out.println("No. Vertex = "+graph.vertexSet().size());
		out.println("No. Edge = "+graph.edgeSet().size());
        Iterator<Object> iter =new DepthFirstIterator<Object, DefaultEdge>(graph);
        Object vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            out.println(
                "Vertex " + vertex.toString() + " is connected to: "
                + graph.edgesOf(vertex).toString());
        }
        out.close();
	}
}
