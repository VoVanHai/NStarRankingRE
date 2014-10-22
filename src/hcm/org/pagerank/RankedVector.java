package hcm.org.pagerank;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: Feb 21, 2009
 * Time: 1:24:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RankedVector<N> {
    double getWeight(N node);
    Collection<WeightedNode<N>> entriesInOrder();
}
