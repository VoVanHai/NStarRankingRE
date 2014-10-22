package hcm.org.pagerank;

/**
 * Created by IntelliJ IDEA.
* User: josh
* Date: Feb 21, 2009
* Time: 12:02:50 PM
* To change this template use File | Settings | File Templates.
*/
public interface WeightedEdge<I> {
    Node<I> getHead();
    Node<I> getTail();
    float getWeight();
}
