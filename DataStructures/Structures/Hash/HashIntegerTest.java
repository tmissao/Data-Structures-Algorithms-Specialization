import static org.assertj.core.api.Assertions.*;

/**
 * Tests for HashInteger
 */
public class HashIntegerTest {

    private HashInteger hash;
    private int buckets;

    @org.junit.Before
    public void setUp() throws Exception {
        this.buckets = 100;
        this.hash = new HashInteger(buckets);
    }

    @org.junit.Test
    public void getLoadFactor() throws Exception {
        for(int i = 0; i < 100; i++) {
            hash.put(i);
            assertThat(hash.getLoadFactor()).isEqualTo((i+1.0)/buckets);
        }

        for(int i = 0; i < 100; i++) {
            hash.remove(i);
            assertThat(hash.getLoadFactor()).isEqualTo((99.0 - i) / buckets);
        }
    }

    @org.junit.Test
    public void getSize() throws Exception {
        for(int i = 0; i < 100; i++) {
            assertThat(i).isEqualTo(hash.getSize());
            hash.put(i);
        }

        assertThat(100).isEqualTo(hash.getSize());

        for(int i = 0; i < 100; i++) {
            hash.remove(i);
            assertThat(99 - i).isEqualTo(hash.getSize());
        }

        assertThat(0).isEqualTo(hash.getSize());
    }

    @org.junit.Test
    public void put() throws Exception {
        for(long i = 0; i < 100; i++) {
            hash.put(i);
            assertThat(i).isEqualTo(hash.get(i));
        }
    }

    @org.junit.Test
    public void get() throws Exception {
        for(long i = 0; i < 100; i++) {
            hash.put(i);
            assertThat(i).isEqualTo(hash.get(i));
        }
    }

    @org.junit.Test
    public void getNotExists() throws Exception {
        assertThat(-1L).isEqualTo(hash.get(1));
    }

    @org.junit.Test
    public void remove() throws Exception {
        for(int i = 0; i < 100; i++) {
            hash.put(i);
        }

        assertThat(100).isEqualTo(hash.getSize());

        for(int i = 0; i < 100; i++) {
            assertThat(hash.get(i)).isEqualTo(i);
            hash.remove(i);
            assertThat(99 - i).isEqualTo(hash.getSize());
            assertThat(hash.get(i)).isEqualTo(HashInteger.NOT_FOUND);
        }

        assertThat(0).isEqualTo(hash.getSize());
    }

    @org.junit.Test
    public void removeNotExists() throws Exception {
        assertThat(HashInteger.NOT_FOUND).isEqualTo(hash.remove(1));
    }
}