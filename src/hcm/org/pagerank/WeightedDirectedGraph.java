package hcm.org.pagerank;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
* User: josh
* Date: Feb 21, 2009
* Time: 12:03:02 PM
* To change this template use File | Settings | File Templates.
*/
public interface WeightedDirectedGraph<I> {
    Node<I> node(I identifier);
    Collection<Node<I>> allNodes();
}
