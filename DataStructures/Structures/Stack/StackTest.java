import java.util.Random;

import static org.assertj.core.api.Assertions.*;

/**
 * Stack Test
 */
public class StackTest {

  private Stack<Long> s1;
  private java.util.Stack<Long> stack;
  private Random random;

  @org.junit.Before
  public void setUp() throws Exception {
    this.s1 = new DoubledLinkedList<>();
    this.stack = new java.util.Stack<>();
    this.random = new Random();
  }

  @org.junit.Test
  public void pop() throws Exception {
    for(int i = 0; i < 50; i++) {
      long value = random.nextLong();
      stack.push(value);
      s1.push(value);

      assertThat(stack.peek()).isEqualTo(s1.peek());
    }

    while(!stack.isEmpty() && !s1.isEmpty()) {
      assertThat(stack.pop()).isEqualTo(s1.pop());
    }

    assertThat(stack.isEmpty()).isEqualTo(s1.isEmpty());
  }

  @org.junit.Test
  public void peek() throws Exception {
    for(int i = 0; i < 50; i++) {
      long value = random.nextLong();
      stack.push(value);
      s1.push(value);

      assertThat(stack.peek()).isEqualTo(s1.peek());
    }
  }

  @org.junit.Test
  public void push() throws Exception {
    for(int i = 0; i < 50; i++) {
      long value = random.nextLong();
      stack.push(value);
      s1.push(value);

      assertThat(stack.peek()).isEqualTo(s1.peek());
    }
  }

  @org.junit.Test
  public void isEmpty() throws Exception {
    assertThat(s1.isEmpty()).isTrue();
  }

  @org.junit.Test
  public void isNotEmpty() throws Exception {
    s1.push(random.nextLong());
    assertThat(s1.isEmpty()).isFalse();
  }

}