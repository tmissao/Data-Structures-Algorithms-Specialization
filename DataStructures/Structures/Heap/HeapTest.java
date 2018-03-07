import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

/**
 * Heap Test
 */
public class HeapTest {

    private Heap heap;
    private Random random;
    private long[] input;

    @Before
    public void setUp() throws Exception {
        this.heap = new Heap(10);
        this.random = new Random();
        this.input = new long[]{1, 10, 9, 2, 4, 5, 6, 3, 8, 7};
    }

    @Test
    public void isEmpty() throws Exception {
        assertThat(heap.isEmpty()).isTrue();
    }

    @Test
    public void isNotEmpty() throws Exception {
        heap.insert(random.nextLong());
        assertThat(heap.isEmpty()).isFalse();
    }

    @Test
    public void insert() throws Exception {
        for(long n : input) heap.insert(n);
        assertThat(heap.getSize()).isEqualTo(input.length);
    }

    @Test( expected = RuntimeException.class)
    public void insertError() throws Exception {
        for(long n : input) heap.insert(n);
        heap.insert(random.nextLong());
    }

    @Test
    public void getMax() throws Exception {
        long max = Long.MIN_VALUE;
        for(int i = 0; i < input.length; i++) {
            long e = input[i];
            max = max < e ? e : max;
            heap.insert(e);
            assertThat(heap.getMax()).isEqualTo(max);
        }
    }

    @Test(expected = RuntimeException.class)
    public void getMaxEmpty() throws Exception {
        heap.getMax();
    }

    @Test
    public void extractMax() throws Exception {
        for(long n : input) heap.insert(n);
        for(int i = 10; i > 0; i--) {
            assertThat(heap.extractMax()).isEqualTo(i);
            assertThat(heap.getSize()).isEqualTo(i-1);

            if(i != 1) {
                assertThat(heap.getMax()).isEqualTo(i-1);
            }
        }
    }

    @Test(expected = RuntimeException.class)
    public void extractMaxEmpty() throws Exception {
        heap.extractMax();
    }

    @Test
    public void remove() throws Exception {
        for(long n : input) heap.insert(n);
        heap.remove(7);
        assertThat(heap.getSize()).isEqualTo(input.length - 1);

        for(int i = 10; i > 1; i--) {
            assertThat(heap.extractMax()).isEqualTo(i);
        }
    }

    @Test(expected = RuntimeException.class)
    public void removeOutOfRangeMax() throws Exception {
        for(long n : input) heap.insert(n);
        heap.remove(10);
    }

    @Test(expected = RuntimeException.class)
    public void removeOutOfRangeMin() throws Exception {
        for(long n : input) heap.insert(n);
        heap.remove(-1);
    }

    @Test
    public void changePriority() throws Exception {
        for(long n : input) heap.insert(n);
        heap.changePriority(7, 11);
        for(int i = 11; i > 1; i--) {
            assertThat(heap.extractMax()).isEqualTo(i);
        }
    }

    @Test(expected = RuntimeException.class)
    public void changePriorityOutOfRangeMax() throws Exception {
        for(long n : input) heap.insert(n);
        heap.changePriority(10, 11);
    }

    @Test(expected = RuntimeException.class)
    public void changePriorityOutOfRangeMin() throws Exception {
        for(long n : input) heap.insert(n);
        heap.changePriority(-1, 11);
    }

}