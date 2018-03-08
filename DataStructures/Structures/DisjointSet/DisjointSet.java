import java.util.HashMap;

public class DisjointSet {

    private HashMap<Long, Node> hash;

    public DisjointSet() {
        this.hash = new HashMap<>();
    }

    /**
     * Builds Isolated sets
     * Complexity: O(1)
     */
    public Node buildSet(long key) {
        Node node = new Node(key);
        this.hash.put(key, node);
        return node;
    }

    /**
     * Finds the root element and also compress the tree path
     * Complexity: O(log* n) which is almost a constant time O(1)
     */
    public Node find(long key) {
        Node node = this.hash.get(key);

        if (node != null && !node.getRoot().equals(node)) {
            node.setRoot(find(node.getRoot().getKey()));
        }

        return node.getRoot();
    }

    /**
     * Makes the union of two sets
     * Complexity: O(2log* n) which is almost a constant time O(1)
     */
    public void union(long i, long j) {
        Node n1 = find(i);
        Node n2 = find(j);

        if (n1.equals(n2)) {
            return;
        }

        if(n1.getRank() > n2.getRank()) {
            n2.setRoot(n1);
            return;
        }

        n1.setRoot(n2);

        if (n1.getRank() == n2.getRank()) {
            n2.setRank(n2.getRank() + 1);
        }

    }

    /**
     * Checks if Disjoint set is empty
     */
    public boolean isEmpty() {
        return this.hash.isEmpty();
    }

    /**
     * Gets the Disjoint set size
     */
    public int getSize() {
        return this.hash.size();
    }
}

/**
 * Auxiliary Class to Build the disjoint Set
 */
class Node {

    /**
     * Parent
     */
    private Node root;

    /**
     * Number of children
     */
    private long rank;

    /**
     * Node identifier
     */
    private final long key;

    public Node(long key) {
        this.key = key;
        rank = 0;
        this.root = this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return key == node.key;
    }

    @Override
    public int hashCode() {
        return (int) (key ^ (key >>> 32));
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getKey() {
        return key;
    }
}
