package hcm.org.pagerank;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * User: josh
 * Date: Feb 21, 2009
 * Time: 12:04:10 PM
 */
public class PageRank<I> {
    private final RankedVector<I> rankedVector;

    /**
     * Note: assumes that the graph will not change until the constructor has completed.
     *
     * @param graph the weighted graph on which to run the PageRank algorithm
     * @param dampingFactor a typical value is 0.85
     */
    public PageRank(final WeightedDirectedGraph<I> graph,
                    final double dampingFactor) {
        HashMap<I, Double> lastRanking = new HashMap<I, Double>();
        HashMap<I, Double> nextRanking = new HashMap<I, Double>();

        // Initialize "last" ranking with initial values.
        Double startRank = 1.0 / graph.allNodes().size();
        for (Node<I> n : graph.allNodes()) {
            lastRanking.put(n.identifier(), startRank);
        }

        int iterations = 52;
        double dampingFactorComplement = 1.0 - dampingFactor;
        for (int i = 0; i < iterations; i++) {
            // Iterate over all nodes.
            for (Node<I> node : graph.allNodes()) {
                double totalInEdgeWeight = 0;
                for (WeightedEdge<I> edge : node.inEdges()) {
                    Node<I> tail = edge.getTail();
                    totalInEdgeWeight += (lastRanking.get(tail.identifier()) / tail.totalOutboundEdgeWeight());
                }

                Double nextRank = dampingFactorComplement
                        + (dampingFactor * totalInEdgeWeight);

                nextRanking.put(node.identifier(), nextRank);
            }

            lastRanking = nextRanking;
        }

        rankedVector = new PageRankVector<I>(lastRanking);
    }

    public RankedVector<I> getRanking() {
        return rankedVector;
    }

    private class PageRankVector<I> implements RankedVector<I> {
        private final LinkedHashMap<I, WeightedNode<I>> sortedEntryList;

        public PageRankVector(final HashMap<I, Double> ranking) {
            // TODO: use the List implementation with the fastest sort.
            List<PageRankNode<I>> nodeList = new LinkedList<PageRankNode<I>>();
            for (I identifier : ranking.keySet()) {
                PageRankNode<I> node
                        = new PageRankNode<I>(identifier, ranking.get(identifier));
                nodeList.add(node);
            }
            Collections.sort(nodeList);

            sortedEntryList = new LinkedHashMap<I, WeightedNode<I>>();
            for (WeightedNode<I> node : nodeList) {
                sortedEntryList.put(node.getNode(), node);
            }
        }

        public double getWeight(final I index) {
            return sortedEntryList.get(index).getWeight();
        }

        public Collection<WeightedNode<I>> entriesInOrder() {
            return sortedEntryList.values();
        }
    }

    private class PageRankNode<I> implements WeightedNode<I>, Comparable<WeightedNode<I>> {
        private final I node;
        private final double weight;

        public PageRankNode(final I node,
                            final double weight) {
            this.node = node;
            this.weight = weight;
        }

        public I getNode() {
            return node;
        }

        public double getWeight() {
            return weight;
        }

        public int compareTo(WeightedNode<I> other) {
            return weight > other.getWeight()
                    ? -1 : weight < other.getWeight()
                    ? 1 : 0;
        }
    }
}
