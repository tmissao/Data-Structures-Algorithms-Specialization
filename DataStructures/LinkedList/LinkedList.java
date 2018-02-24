public class LinkedList<T> {

  /**
   * First Element Address
   */
  private Node<T> head;

  /**
   * Last Element Address
   */
  private Node<T> tail;

  /**
   * Checks if the list is empty
   * Complexity: O(1)
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Adds an element at the first position in the list
   * Complexity: O(1)
   */
  public void addFirst(T key) {
    Node<T> node = new Node<>(key);

    if (head == null) {
      head = node;
      tail = node;
      return;
    }

    node.setNext(head);
    head = node;
  }

  /**
   * Adds an element at the last position in the list
   * Complexity: O(1)
   */
  public void addLast(T key) {
    Node node = new Node<>(key);

    if (tail == null) {
      head = node;
      tail = node;
      return;
    }

    tail.setNext(node);
    tail = node;
  }

  /**
   * Gets the first element in the list
   * Complexity: O(1)
   */
  public T getFirst() {
    return head != null ? head.getKey() : null;
  }

  /**
   * Gets the last element in the list
   * Complexity: O(1)
   */
  public T getLast() {
    return tail != null ? tail.getKey() : null;
  }

  /**
   * Searches for a specific element in the list
   * Complexity: O(n)
   */
  public Node search(long key) {
    Node node = head;

    while(node != null) {
      if (node.getKey().equals(key)) {
        return node;
      }
      node = node.getNext();
    }

    return null;
  }

  /**
   * Searches for the previous node of an specific element in the list
   * Complexity: O(n)
   */
  private Node<T> searchPrevious(T key) {
    Node<T> node = head;
    Node<T> previous = null;

    while(node != null) {

      if (node.getKey().equals(key)) {
        return previous;
      }

      previous = node;
      node = node.getNext();
    }

    return null;
  }

  /**
   * Removes the first element in the list
   * Complexity: O(1)
   */
  public T removeFirst() {
    if (head == null) { return null; }

    Node<T> node = head;

    if (head == tail) {
      tail = node.getNext();
    }

    head = node.getNext();

    return node.getKey();
  }

  /**
   * Removes the last element in the list
   * Complexity: O(1)
   */
  public T removeLast() {
    if (tail == null) { return null; }

    Node<T> node = tail;
    Node<T> previous = null;

    if (head == tail) {
      head = node.getNext();
      tail = node.getNext();
    } else {
      previous = searchPrevious(node.getKey());
      if(previous != null) {
        previous.setNext(node.getNext());
      }
      tail = previous;
    }
    return node.getKey();
  }

  /**
   * Remove a specific element in the list
   * Complexity: O(n)
   */
  public T remove(T key) {
    Node<T> node = head;
    Node<T> previous = null;

    while(node != null) {
      if (node.getKey().equals(key)) {
        if (node == head) {
          head = node.getNext();
        }

        if (node == tail) {
          tail = previous;
        }

        if (previous != null) {
          previous.setNext(node.getNext());
        }

        return node.getKey();
      }
      previous = node;
      node = node.getNext();
    }

    return null;
  }

  /**
   * Reverses get next node of a list without use an extra variable
   */
  private Node reverseR(Node node) {
    if (node.getNext() == null) {
      return node;
    }

    reverseR(node.getNext()).setNext(node);
    return node;
  }

  /**
   * Reverses a list
   * Complexity: O(n)
   */
  public void reverse() {
    if (head == tail) {
      return;
    }

    Node node = head;

    reverseR(node);

    head = tail;
    tail = node;
  }

  /**
   * Prints a list
   */
  public void print() {
    Node node = head;
    StringBuilder sb = new StringBuilder();

    while(node != null) {
      sb.append(node.getKey()).append(" ");
      node = node.getNext();
    }

    System.out.println(sb);
  }
}

/**
 * Auxiliary class used to keep the reference of the value and the next element
 */
class Node<T> {

  private final T key;
  private Node next;

  public Node(T key) {
    this.key = key;
  }

  public T getKey() {
    return key;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Node{");
    sb.append("key=").append(key);
    sb.append('}');
    return sb.toString();
  }
}
