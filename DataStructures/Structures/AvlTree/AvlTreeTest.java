import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class AvlTreeTest {

    private AvlTree<Long> tree;
    private long[] inputs = {1,2,3,4,5,6,7,8};

    private void insertTest() {
        for(long n : inputs) tree.insert(n);
    }

    @Before
    public void setUp() throws Exception {
        this.tree = new AvlTree<>();
        insertTest();
    }


    @Test
    public void dstInOrder() throws Exception {
        assertThat(tree.dstInOrder()).isEqualTo("1 2 3 4 5 6 7 8");
    }

    @Test
    public void dstInOrderEmpty() throws Exception {
        assertThat(new AvlTree<Long>().dstInOrder()).isEqualTo("");
    }

    @Test
    public void dstPreOrder() throws Exception {
        assertThat(tree.dstPreOrder()).isEqualTo("4 2 1 3 6 5 7 8");
    }

    @Test
    public void dstPreOrderEmpty() throws Exception {
        assertThat(new AvlTree<Long>().dstPreOrder()).isEqualTo("");
    }

    @Test
    public void dstPostOrder() throws Exception {
        assertThat(tree.dstPostOrder()).isEqualTo("1 3 2 5 8 7 6 4");
    }

    @Test
    public void dstPostOrderEmpty() throws Exception {
        assertThat(new AvlTree<Long>().dstPostOrder()).isEqualTo("");
    }

    @Test
    public void bfs() throws Exception {
        assertThat(tree.bfs()).isEqualTo("4 2 6 1 3 5 7 8");
    }

    @Test
    public void bfsEmpty() throws Exception {
        assertThat(new AvlTree<Long>().bfs()).isEqualTo("");
    }

    @Test
    public void getHeight() throws Exception {
        assertThat(tree.getHeight()).isEqualTo(4);
    }

    @Test
    public void getHeightEmpty() throws Exception {
        assertThat(new AvlTree<Long>().getHeight()).isEqualTo(0);
    }

    @Test
    public void getSize() throws Exception {
        assertThat(tree.getSize()).isEqualTo(inputs.length);
    }

    @Test
    public void getSizeEmpty() throws Exception {
        assertThat(new AvlTree<Long>().getSize()).isEqualTo(0);
    }

    @Test
    public void getMirror() throws Exception {
        assertThat(tree.getMirror().dstInOrder()).isEqualTo("8 7 6 5 4 3 2 1");
    }

    @Test
    public void getMirrorEmpty() throws Exception {
        assertThat(new AvlTree<Long>().getMirror().dstInOrder()).isEqualTo("");
    }

    @Test
    public void search() throws Exception {
        assertThat(tree.search(8L)).isEqualTo(8L);
        assertThat(tree.search(5L)).isEqualTo(5L);
        assertThat(tree.search(1l)).isEqualTo(1L);
        assertThat(tree.search(51L)).isNull();
        assertThat(tree.search(18L)).isNull();
        assertThat(tree.search(3L)).isEqualTo(3L);
        assertThat(tree.search(-1L)).isNull();
    }

    @Test
    public void rangeSearch() throws Exception {
        assertThat(tree.rangeSearch(20L, 20L)).isEqualTo("");
        assertThat(tree.rangeSearch(3L, 7L)).isEqualTo("3 4 5 6 7");
        assertThat(tree.rangeSearch(-4L, -1L)).isEqualTo("");
        assertThat(tree.rangeSearch(-4L, 1L)).isEqualTo("1");
        assertThat(tree.rangeSearch(4L, 1L)).isEqualTo("");
        assertThat(tree.rangeSearch(1L, 4L)).isEqualTo("1 2 3 4");
    }

    @Test
    public void delete() throws Exception {
        tree.delete(3L);
        assertThat(tree.bfs()).isEqualTo("4 2 6 1 5 7 8");
        assertThat(tree.getHeight()).isEqualTo(4);

        tree.delete(1L);
        assertThat(tree.bfs()).isEqualTo("6 4 7 2 5 8");
        assertThat(tree.getHeight()).isEqualTo(3);

        tree.delete(6L);
        assertThat(tree.bfs()).isEqualTo("7 4 8 2 5");
        assertThat(tree.getHeight()).isEqualTo(3);
    }
}