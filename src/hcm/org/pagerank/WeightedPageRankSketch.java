package hcm.org.pagerank;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: Feb 7, 2009
 * Time: 7:50:15 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("all")
public class WeightedPageRankSketch {

    /**
     * The parameterized node type N avoids the need for wrapper objects,
     * and/or the need to extend/implement a particular "node" class or interface.
     * The parameterized identifier type I allows different types of identifiers
     * (strings, numbers, etc.) to be used.
     */
	
    public interface NameMe<N, I> {
        N node(I identifier);

        /**
         * Note: it's best to keep identifiers short, as they are stored in a
         * global collection of identifiers.
         * @param node
         * @return  the unique identifier of a node
         */
        I identifierOf(N node);

        Iterator<I> allIdentifiers();

        Iterator<Node> neighborsOf(N node);        
    }

    public interface LinkIterator<N> {
        boolean hasNext();

        void advance();

        N getNode();

        double getWeight();
    }

    public void calculatePageRank(final Collection<Node> nodes) {
        int iterations = 52;

        for (int i = 0; i < iterations; i++) {
            for (Node node : nodes) {

            }
        }
    }
}
