package hcm.org.pagerank;

/**
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: Feb 21, 2009
 * Time: 1:26:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface WeightedNode<N> {
    N getNode();
    double getWeight();
}
