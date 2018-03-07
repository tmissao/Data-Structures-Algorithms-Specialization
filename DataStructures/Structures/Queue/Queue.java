/**
 * Interfaces to simulates a stack
 */
public interface Queue<T> {

  /**
   * Puts the element at the last position in the queue
   */
  void enqueue(T element);

  /**
   * Removes the first element of the queue
   */
  T dequeue();

  /**
   * Gets the first element of the queue
   */
  T peek();

  /**
   * Checks if the Queue is empty
   */
  boolean isEmpty();
}
