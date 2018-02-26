/**
 * Interfaces to simulates a stack
 */
public interface Stack<T> {

  /**
   * Removes the last element put into stack
   */
  T pop();

  /**
   * Gets the last element put into stack
   */
  T peek();

  /**
   * Puts the element into at the top of the stack
   * @param element
   */
  void push(T element);

  /**
   * Checks if the Stack is empty
   */
  boolean isEmpty();
}
