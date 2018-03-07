import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T extends Comparable> {

  /**
   * Tree`s root element
   */
  private Node<T> root;

  private Node<T> insertLeaf(Node<T> node, T element) {
    if (node == null) {
      return new Node<>(element);
    }

    boolean isLess = node.getKey().compareTo(element) == 1;

    if (isLess) {
      node.setLeft(insertLeaf(node.getLeft(), element));
    } else {
      node.setRight(insertLeaf(node.getRight(), element));
    }

    return node;
  }

  private void dstInOrder(Node<T> node, StringBuilder sb) {
    if (node == null) {
      return;
    }

    dstInOrder(node.getLeft(), sb);
    sb.append(" ").append(node.getKey());
    dstInOrder(node.getRight(), sb);
  }

  private void dstPreOrder(Node<T> node, StringBuilder sb) {
    if (node == null) {
      return;
    }

    sb.append(" ").append(node.getKey());
    dstPreOrder(node.getLeft(), sb);
    dstPreOrder(node.getRight(), sb);
  }

  private void dstPostOrder(Node<T> node, StringBuilder sb) {
    if (node == null) {
      return;
    }

    dstPostOrder(node.getLeft(), sb);
    dstPostOrder(node.getRight(), sb);
    sb.append(" ").append(node.getKey());
  }

  private long getHeight(Node<T> node, long height) {
    if (node == null) { return 0L; }
    return 1 + Math.max(getHeight(node.getLeft(), height), getHeight(node.getRight(), height));
  }

  private long getSize(Node<T> node) {
    if (node == null) { return 0L; }
    return 1 + getSize(node.getLeft()) + getSize(node.getRight());
  }

  private Node<T> buildMirror(Node<T> node) {
    if (node == null) {
      return null;
    }

    Node<T> mirror = new Node<>(node.getKey());
    mirror.setLeft(buildMirror(node.getRight()));
    mirror.setRight(buildMirror(node.getLeft()));
    return mirror;
  }

  /**
   * Inserts an element into tree
   * Complexity: O(log(n)) if tree is balanced, otherwise O(n)
   */
  void insert(T element) {
   root = insertLeaf(root, element);
  }

  /**
   * Gets the string representation of the tree in order (Left, Node, Right)
   * Complexity: O(n)
   */
  public String dstInOrder() {
    StringBuilder sb = new StringBuilder();
    dstInOrder(root, sb);
    return sb.length() == 0 ? sb.toString() : sb.deleteCharAt(0).toString();
  }

  /**
   * Gets the string representation of the tree in pre order (Node, Left, Right)
   * Complexity: O(n)
   */
  public String dstPreOrder() {
    StringBuilder sb = new StringBuilder();
    dstPreOrder(root, sb);
    return sb.length() == 0 ? sb.toString() : sb.deleteCharAt(0).toString();
  }

  /**
   * Gets the string representation of the tree in post order (Left, Right, Node)
   * Complexity: O(n)
   */
  public String dstPostOrder() {
    StringBuilder sb = new StringBuilder();
    dstPostOrder(root, sb);
    return sb.length() == 0 ? sb.toString() : sb.deleteCharAt(0).toString();
  }

  /**
   * Gets the string representation by height
   * Complexity: O(n)
   */
  public String bfs() {
    StringBuilder sb = new StringBuilder();

    if (root == null) {
      return sb.toString();
    }

    Queue<Node<T>> queue = new LinkedList<>();
    queue.add(root);

    while(!queue.isEmpty()) {
      Node<T> node = queue.remove();

      if (node.getLeft() != null) {
        queue.add(node.getLeft());
      }

      if (node.getRight() != null) {
        queue.add(node.getRight());
      }

      sb.append(" ").append(node.getKey());
    }

    return sb.deleteCharAt(0).toString();
  }

  /**
   * Get the tree`s height
   * Complexity: (n)
   */
  public Long getHeight() {
    return getHeight(root, 0);
  }

  /**
   * Get the number of elements in the tree
   * Complexity: (n)
   */
  public Long getSize() {
    return getSize(root);
  }

  /**
   * Builds a mirror representation of the tree
   * Complexity: (n)
   */
  public BinaryTree<T> getMirror() {
    BinaryTree<T> mirror = new BinaryTree<>();
    mirror.root = mirror.buildMirror(root);
    return mirror;
  }
}

class Node<T extends Comparable>  {

  public Node(T key) {
    this.key = key;
  }

  private final T key;
  private Node<T> right;
  private Node<T> left;

  public T getKey() {
    return key;
  }

  public Node<T> getRight() {
    return right;
  }

  public void setRight(Node<T> right) {
    this.right = right;
  }

  public Node<T> getLeft() {
    return left;
  }

  public void setLeft(Node<T> left) {
    this.left = left;
  }
}
