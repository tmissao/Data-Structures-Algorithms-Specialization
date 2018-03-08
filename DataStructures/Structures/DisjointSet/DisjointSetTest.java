import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


/**
 * DisjointSet Test
 */
public class DisjointSetTest {

    DisjointSet set;

    @Before
    public void setUp() throws Exception {
        this.set = new DisjointSet();
    }

    @Test
    public void buildSet() throws Exception {
        for(int i = 0; i < 10; i++) {
            Node node = set.buildSet(i);
            assertThat(node.getKey()).isEqualTo(i);
            assertThat(node.getRank()).isEqualTo(0);
            assertThat(node.getRoot()).isEqualTo(node);
        }
    }

    @Test
    public void find() throws Exception {
        for(int i = 0; i < 10; i++) {
            Node node = set.buildSet(i);
            assertThat(node.getRoot()).isEqualTo(node);
        }
    }

    @Test
    public void union() throws Exception {
        for(int i = 0; i < 10; i++) {
            Node node = set.buildSet(i);
            assertThat(node.getRoot()).isEqualTo(node);
        }

        set.union(2, 4);
        set.union(4, 8);
        set.union(8, 9);
        set.union(0, 1);

        assertThat(set.find(2)).isEqualTo(set.find(9));
        assertThat(set.find(8)).isEqualTo(set.find(9));
        assertThat(set.find(4)).isEqualTo(set.find(8));
        assertThat(set.find(2)).isEqualTo(set.find(4));
        assertThat(set.find(9)).isEqualTo(set.find(4));
        assertThat(set.find(0)).isEqualTo(set.find(1));
        assertThat(set.find(1)).isNotEqualTo(set.find(4));

        set.union(0, 8);

        assertThat(set.find(1)).isEqualTo(set.find(4));
    }

    @Test
    public void isEmpty() throws Exception {
        assertThat(set.isEmpty()).isTrue();
    }

    @Test
    public void isNotEmpty() throws Exception {
        set.buildSet(2);
        assertThat(set.isEmpty()).isFalse();
    }

    @Test
    public void getSize() throws Exception {
        for(int i = 0; i < 10; i++) {
            set.buildSet(i);
            assertThat(set.getSize()).isEqualTo(i+1);
        }
    }

}