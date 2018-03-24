import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MergingTables {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int numberOfTables = scanner.nextInt();
        int numberOfQueries = scanner.nextInt();
        DisjointSet disjointSet = new DisjointSet();
        Queue<Merge> merges = new LinkedList<>();

        for(int i = 1; i <= numberOfTables; i++) {
            disjointSet.buildSet(i, scanner.nextLong());
        }

        for(int i = 0; i < numberOfQueries; i++) {
            merges.offer(new Merge(scanner.nextInt(), scanner.nextInt()));
        }

        System.out.print(mergeTable(merges, disjointSet));

    }

    /**
     * Executes the merges between tables, and return the max number of rows after each operation
     * Complexity: O(n*log(n))
     */
    private static String mergeTable(Queue<Merge> merges, DisjointSet disjointSet) {
        StringBuilder sb = new StringBuilder();

        while (!merges.isEmpty()) {
            Merge merge = merges.poll();
            sb.append(disjointSet.union(merge.getT1(), merge.getT2())).append("\n");
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        run();
    }
}

/**
 * Merge Operations
 */
class Merge {

    /**
     * First Table to be Merge
     */
    private final long t1;

    /**
     * Second Table to be Merge
     */
    private final long t2;

    public Merge(long t1, long t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public long getT1() {
        return t1;
    }

    public long getT2() {
        return t2;
    }
}

/**
 * Disjoint Set
 */
class DisjointSet {

    private HashMap<Long, Node> hash;
    private long maxRows;

    public DisjointSet() {
        this.hash = new HashMap<>();
        this.maxRows = Long.MIN_VALUE;
    }

    /**
     * Builds Isolated sets
     * Complexity: O(1)
     */
    public Node buildSet(long key, long rows) {
        Node node = new Node(key, rows);
        this.hash.put(key, node);
        this.maxRows = maxRows < rows ? rows : maxRows;
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
    public long union(long i, long j) {
        Node n1 = find(i);
        Node n2 = find(j);

        if (n1.equals(n2)) {
            return this.maxRows ;
        }

        long rows = n1.getRows() + n2.getRows();


        if(n1.getRank() > n2.getRank()) {
            n2.setRoot(n1);
            n1.setRows(rows);

        } else {
            n1.setRoot(n2);
            n2.setRows(rows);


            if (n1.getRank() == n2.getRank()) {
                n2.setRank(n2.getRank() + 1);
            }
        }

        this.maxRows = maxRows < rows ? rows : maxRows;
        return maxRows;
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
     * Number of rows
     */
    private long rows;

    /**
     * Node identifier
     */
    private final long key;

    public Node(long key, long rows) {
        this.key = key;
        this.rows = rows;
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

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }
}

/**
 * A faster scanner implementation
 */
class FasterScanner {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private String delimiter;

    FasterScanner(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        delimiter = " \t\n\r\f";
    }

    FasterScanner(InputStream stream, String delimiter) {
        this(stream);
        this.delimiter = delimiter;
    }

    String next() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine(), delimiter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tokenizer.nextToken();
    }

    Integer nextInt() {
        return Integer.parseInt(next());
    }

    Long nextLong() {
        return Long.parseLong(next());
    }
}
