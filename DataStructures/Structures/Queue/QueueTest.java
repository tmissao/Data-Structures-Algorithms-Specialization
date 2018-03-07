import java.util.Random;

import static org.assertj.core.api.Assertions.*;

/**
 * Stack Test
 */
public class QueueTest {

  private Queue<Long> q1;
  private java.util.Queue<Long> queue;
  private Random random;

  @org.junit.Before
  public void setUp() throws Exception {
    this.q1 = new DoubledLinkedList<>();
    this.queue = new java.util.LinkedList<>();
    this.random = new Random();
  }

  @org.junit.Test
  public void dequeue() throws Exception {
    for(int i = 0; i < 50; i++) {
      long value = random.nextLong();
      queue.offer(value);
      q1.enqueue(value);

      assertThat(queue.peek()).isEqualTo(q1.peek());
    }

    while(!queue.isEmpty() && !q1.isEmpty()) {
      assertThat(queue.poll()).isEqualTo(q1.dequeue());
    }

    assertThat(queue.isEmpty()).isEqualTo(q1.isEmpty());
  }

  @org.junit.Test
  public void peek() throws Exception {
    for(int i = 0; i < 50; i++) {
      long value = random.nextLong();
      queue.offer(value);
      q1.enqueue(value);

      assertThat(queue.peek()).isEqualTo(q1.peek());
    }
  }

  @org.junit.Test
  public void enqueue() throws Exception {
    for(int i = 0; i < 50; i++) {
      long value = random.nextLong();
      queue.offer(value);
      q1.enqueue(value);

      assertThat(queue.peek()).isEqualTo(q1.peek());
    }
  }

  @org.junit.Test
  public void isEmpty() throws Exception {
    assertThat(q1.isEmpty()).isTrue();
  }

  @org.junit.Test
  public void isNotEmpty() throws Exception {
    q1.enqueue(random.nextLong());
    assertThat(q1.isEmpty()).isFalse();
  }

}