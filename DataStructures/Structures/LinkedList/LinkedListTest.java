import static org.assertj.core.api.Assertions.*;
import java.util.Random;

public class LinkedListTest {

  private LinkedList<Long> customList;
  java.util.LinkedList<Long> list;

  private Random random;

  @org.junit.Before
  public void setUp() throws Exception {
    this.customList = new LinkedList<>();
    list = new java.util.LinkedList<>();
    this.random = new Random();
  }

  @org.junit.Test
  public void isEmpty() throws Exception {
    assertThat(customList.isEmpty()).isEqualTo(true);
  }

  @org.junit.Test
  public void isNotEmpty() throws Exception {
    Long value = random.nextLong();
    customList.addFirst(value);
    assertThat(customList.isEmpty()).isEqualTo(false);
  }

  @org.junit.Test
  public void addFront() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value = random.nextLong();
      customList.addFirst(value);
      assertThat(value).isEqualTo(customList.getFirst());
    }
  }

  @org.junit.Test
  public void addBack() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value = random.nextLong();
      customList.addLast(value);

      assertThat(value).isEqualTo(customList.getLast());
    }
  }

  @org.junit.Test
  public void getFirst() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value = random.nextLong();
      customList.addFirst(value);
      list.addFirst(value);

      assertThat(list.getFirst()).isEqualTo(customList.getFirst());
    }

    for(int i = 0; i< 50; i++) {
      assertThat(list.removeFirst()).isEqualTo(customList.removeFirst());
    }
  }

  @org.junit.Test
  public void getFirstEmpty() throws Exception {
      assertThat(customList.getFirst()).isEqualTo(null);
  }

  @org.junit.Test
  public void getLast() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value = random.nextLong();
      customList.addLast(value);
      list.addLast(value);

      assertThat(list.getLast()).isEqualTo(customList.getLast());
    }

    for(int i = 0; i< 50; i++) {
      assertThat(list.removeFirst()).isEqualTo(customList.removeFirst());
    }
  }

  @org.junit.Test
  public void getLastEmpty() throws Exception {
      assertThat(customList.getLast()).isEqualTo(null);
  }

  @org.junit.Test
  public void searchEmpty() throws Exception {
      assertThat(customList.search(random.nextLong())).isEqualTo(null);
  }

  @org.junit.Test
  public void search() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value = random.nextLong();
      customList.addLast(value);

      assertThat(customList.search(value).getKey()).isEqualTo(value);
    }
  }

  @org.junit.Test
  public void searchNotIn() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value = (long) i;
      customList.addLast(value);

      assertThat(customList.search(50 + value)).isEqualTo(null);
    }
  }

  @org.junit.Test
  public void removeFirstEmpty() throws Exception {
    assertThat(customList.removeFirst()).isEqualTo(null);
  }

  @org.junit.Test
  public void removeFirst() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value = random.nextLong();
      customList.addLast(value);
      list.addLast(value);

      assertThat(list.getLast()).isEqualTo(customList.getLast());
    }

    for(int i = 0; i< 50; i++) {
      assertThat(list.removeFirst()).isEqualTo(customList.removeFirst());
    }
  }

  @org.junit.Test
  public void removeLastEmpty() throws Exception {
    assertThat(customList.removeLast()).isEqualTo(null);
  }

  @org.junit.Test
  public void removeLast() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value =  random.nextLong();
      customList.addFirst(value);
      list.addFirst(value);

      assertThat(list.getLast()).isEqualTo(customList.getLast());
    }

    for(int i = 0; i< 50; i++) {
      assertThat(list.removeLast()).isEqualTo(customList.removeLast());
    }
  }

  @org.junit.Test
  public void removeHeadTail() throws Exception {
    customList.addFirst(10L);
    assertThat(customList.remove(10L)).isEqualTo(10L);
    assertThat(customList.getFirst()).isEqualTo(null);
    assertThat(customList.getLast()).isEqualTo(null);
  }

  @org.junit.Test
  public void removeHead() throws Exception {
    customList.addFirst(10L);
    customList.addLast(11L);
    assertThat(customList.remove(10L)).isEqualTo(10L);
    assertThat(customList.getFirst()).isEqualTo(11L);
    assertThat(customList.getLast()).isEqualTo(11L);
  }

  @org.junit.Test
  public void removeTail() throws Exception {
    customList.addFirst(10L);
    customList.addLast(11L);
    assertThat(customList.remove(11L)).isEqualTo(11L);
    assertThat(customList.getFirst()).isEqualTo(10L);
    assertThat(customList.getLast()).isEqualTo(10L);

  }

  @org.junit.Test
  public void removeMiddle() throws Exception {
    customList.addFirst(10L);
    customList.addLast(11L);
    customList.addLast(12L);
    assertThat(customList.remove(11L)).isEqualTo(11L);
    assertThat(customList.getFirst()).isEqualTo(10L);
    assertThat(customList.getLast()).isEqualTo(12L);

    assertThat(customList.removeFirst()).isEqualTo(10L);
    assertThat(customList.removeFirst()).isEqualTo(12L);
    assertThat(customList.isEmpty()).isEqualTo(true);
  }

  @org.junit.Test
  public void removeEmpty() throws Exception {
    assertThat(customList.remove(10L)).isEqualTo(null);
  }

  @org.junit.Test
  public void reverseEmpty() throws Exception {
    assertThat(customList.getLast()).isEqualTo(null);
    assertThat(customList.getFirst()).isEqualTo(null);
  }

  @org.junit.Test
  public void reverseOne() throws Exception {
    customList.addFirst(10L);
    assertThat(customList.getLast()).isEqualTo(10L);
    assertThat(customList.getFirst()).isEqualTo(10L);
  }

  @org.junit.Test
  public void reverse() throws Exception {
    for(int i = 0; i<50; i++) {
      Long value =  random.nextLong();
      customList.addFirst(value);
      list.addLast(value);
    }

    customList.reverse();

    for(int i = 0; i< 50; i++) {
      assertThat(list.removeLast()).isEqualTo(customList.removeLast());
    }
  }
}