import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for HashInteger
 */
public class HashStringTest {

    private HashString hash;
    private int buckets;

    @org.junit.Before
    public void setUp() throws Exception {
        this.buckets = 100;
        this.hash = new HashString(buckets);
    }

    @org.junit.Test
    public void getLoadFactor() throws Exception {
        for(int i = 0; i < 100; i++) {
            hash.put(buildString(i));
            assertThat(hash.getLoadFactor()).isEqualTo((i+1.0)/buckets);
        }

        for(int i = 0; i < 100; i++) {
            hash.remove(buildString(i));
            assertThat(hash.getLoadFactor()).isEqualTo((99.0 - i) / buckets);
        }
    }

    @org.junit.Test
    public void getSize() throws Exception {
        for(int i = 0; i < 100; i++) {
            assertThat(i).isEqualTo(hash.getSize());
            hash.put(buildString(i));
        }

        assertThat(100).isEqualTo(hash.getSize());

        for(int i = 0; i < 100; i++) {
            hash.remove(buildString(i));
            assertThat(99 - i).isEqualTo(hash.getSize());
        }

        assertThat(0).isEqualTo(hash.getSize());
    }

    @org.junit.Test
    public void put() throws Exception {
        for(int i = 0; i < 100; i++) {
            hash.put(buildString(i));
            assertThat(buildString(i)).isEqualTo(hash.get(buildString(i)));
        }
    }

    @org.junit.Test
    public void get() throws Exception {
        for(int i = 0; i < 100; i++) {
            hash.put(buildString(i));
            assertThat(buildString(i)).isEqualTo(hash.get(buildString(i)));
        }
    }

    @org.junit.Test
    public void getNotExists() throws Exception {
        assertThat(HashString.NOT_FOUND).isEqualTo(hash.get(""));
    }

    @org.junit.Test
    public void remove() throws Exception {
        for(int i = 0; i < 100; i++) {
            hash.put(buildString(i));
        }

        assertThat(100).isEqualTo(hash.getSize());

        for(int i = 0; i < 100; i++) {
            String value = buildString(i);
            assertThat(hash.get(value)).isEqualTo(value);
            hash.remove(value);
            assertThat(99 - i).isEqualTo(hash.getSize());
            assertThat(hash.get(value)).isEqualTo(HashString.NOT_FOUND);
        }

        assertThat(0).isEqualTo(hash.getSize());
    }

    @org.junit.Test
    public void removeNotExists() throws Exception {
        assertThat(HashString.NOT_FOUND).isEqualTo(hash.remove(""));
    }

    private String buildString(int k) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < k; i++) {
           sb.append(i);
        }

        return sb.toString();
    }
}