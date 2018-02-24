public class LinkedList<T> {

  private Node<T> head;
  private Node<T> tail;

  public boolean isEmpty() {
    return head == null;
  }

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

  public T getFirst() {
    return head != null ? head.getKey() : null;
  }

  public T getLast() {
    return tail != null ? tail.getKey() : null;
  }

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

  public T removeFirst() {
    if (head == null) { return null; }

    Node<T> node = head;

    if (head == tail) {
      tail = node.getNext();
    }

    head = node.getNext();

    return node.getKey();
  }

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

  private Node reverseR(Node node) {
    if (node.getNext() == null) {
      return node;
    }

    reverseR(node.getNext()).setNext(node);
    return node;
  }

  public void reverse() {
    if (head == tail) {
      return;
    }

    Node node = head;

    reverseR(node);

    head = tail;
    tail = node;
  }

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
