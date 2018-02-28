import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ComputeTreeHeight {

    /**
     * Handles program inputs
     */
    private static void run() {
      FasterScanner scanner = new FasterScanner(System.in);
      int numberOfElements = scanner.nextInt();
      Node[] elements = new Node[numberOfElements];
      Node root = null;

      // initializes nodes
      for(int i = 0; i < elements.length; i++) {
        elements[i] = new Node();
      }

      // builds tree
      for(int i = 0; i < elements.length; i++) {
        int relationship = scanner.nextInt();

        if (relationship == -1) {
          root = elements[i];
          continue;
        }

        elements[relationship].addChildren(elements[i]);
      }

      System.out.println(calculateHeight(root));
    }

  /**
   *  Calculates the height of a tree, since the three could be extremely deep
   *  An iteration algorithm for traversal a tree was choose to avoid memory heap
   *  Complexity: O(n)
   */
    private static long calculateHeight(Node root) {
      Queue<Node> queue = new LinkedList<>();
      long maxHeight = Long.MIN_VALUE;

      // First Level, the height is 1
      queue.add(root);

      while(!queue.isEmpty()) {
        Node node = queue.remove();
        long height = node.getHeight();

        for(Node child : node.getChildren()) {
          // N-level the height is parent height + 1
          child.setHeight(height + 1);
          queue.add(child);
        }

        maxHeight = maxHeight < height ? height : maxHeight;
      }

      return maxHeight;
    }

    public static void main(String[] args) {
        run();
    }
}

class Node {

    private long height;
    private LinkedList<Node> children;

    public Node() {
      this.height = 1;
      this.children = new LinkedList<>();
    }

  public long getHeight() {
    return height;
  }

  public void setHeight(long height) {
    this.height = height;
  }

  public LinkedList<Node> getChildren() {
    return children;
  }

  public void setChildren(LinkedList<Node> children) {
    this.children = children;
  }

  public void addChildren(Node node) {
      this.children.add(node);
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