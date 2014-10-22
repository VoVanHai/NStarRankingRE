package hcm.org.pagerank;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
* User: josh
* Date: Feb 21, 2009
* Time: 12:02:34 PM
* To change this template use File | Settings | File Templates.
*/
public interface Node<I> {
    Collection<WeightedEdge<I>> inEdges();
    float totalOutboundEdgeWeight();
    I identifier();
}
