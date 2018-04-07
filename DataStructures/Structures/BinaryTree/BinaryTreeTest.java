import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class BinaryTreeTest {

    private BinaryTree<Long> tree;
    private long[] inputs = {10,5,2,0,3,4,6,8,7,9,13,20,17,15,11, 16};

    private void insertTest() {
        for(long n : inputs) tree.insert(n);
    }

    @Before
    public void setUp() throws Exception {
        this.tree = new BinaryTree<>();
        insertTest();
    }


    @Test
    public void dstInOrder() throws Exception {
        assertThat(tree.dstInOrder()).isEqualTo("0 2 3 4 5 6 7 8 9 10 11 13 15 16 17 20");
    }

    @Test
    public void dstInOrderEmpty() throws Exception {
        assertThat(new BinaryTree<Long>().dstInOrder()).isEqualTo("");
    }

    @Test
    public void dstPreOrder() throws Exception {
        assertThat(tree.dstPreOrder()).isEqualTo("10 5 2 0 3 4 6 8 7 9 13 11 20 17 15 16");
    }

    @Test
    public void dstPreOrderEmpty() throws Exception {
        assertThat(new BinaryTree<Long>().dstPreOrder()).isEqualTo("");
    }

    @Test
    public void dstPostOrder() throws Exception {
        assertThat(tree.dstPostOrder()).isEqualTo("0 4 3 2 7 9 8 6 5 11 16 15 17 20 13 10");
    }

    @Test
    public void dstPostOrderEmpty() throws Exception {
        assertThat(new BinaryTree<Long>().dstPostOrder()).isEqualTo("");
    }

    @Test
    public void bfs() throws Exception {
        assertThat(tree.bfs()).isEqualTo("10 5 13 2 6 11 20 0 3 8 17 4 7 9 15 16");
    }

    @Test
    public void bfsEmpty() throws Exception {
        assertThat(new BinaryTree<Long>().bfs()).isEqualTo("");
    }

    @Test
    public void getHeight() throws Exception {
        assertThat(tree.getHeight()).isEqualTo(6);
    }

    @Test
    public void getHeightEmpty() throws Exception {
        assertThat(new BinaryTree<Long>().getHeight()).isEqualTo(0);
    }

    @Test
    public void getSize() throws Exception {
        assertThat(tree.getSize()).isEqualTo(inputs.length);
    }

    @Test
    public void getSizeEmpty() throws Exception {
        assertThat(new BinaryTree<Long>().getSize()).isEqualTo(0);
    }

    @Test
    public void getMirror() throws Exception {
        assertThat(tree.getMirror().dstInOrder()).isEqualTo("20 17 16 15 13 11 10 9 8 7 6 5 4 3 2 0");
    }

    @Test
    public void search() throws Exception {
        assertThat(tree.search(20L)).isEqualTo(20L);
        assertThat(tree.search(11L)).isEqualTo(11L);
        assertThat(tree.search(4l)).isEqualTo(4L);
        assertThat(tree.search(51L)).isNull();
        assertThat(tree.search(18L)).isNull();
        assertThat(tree.search(16L)).isEqualTo(16L);
        assertThat(tree.search(0L)).isEqualTo(0L);
        assertThat(tree.search(-1L)).isNull();
    }

    @Test
    public void rangeSearch() throws Exception {
        assertThat(tree.rangeSearch(20L, 20L)).isEqualTo("20");
        assertThat(tree.rangeSearch(4L, 15L)).isEqualTo("4 5 6 7 8 9 10 11 13 15");
        assertThat(tree.rangeSearch(-4L, -1L)).isEqualTo("");
        assertThat(tree.rangeSearch(-4L, 1L)).isEqualTo("0");
        assertThat(tree.rangeSearch(4L, 1L)).isEqualTo("");
        assertThat(tree.rangeSearch(1L, 4L)).isEqualTo("2 3 4");
    }

    @Test
    public void deleteNoRightChild() throws Exception {
        assertThat(tree.getSize()).isEqualTo(inputs.length);
        assertThat(tree.bfs()).isEqualTo("10 5 13 2 6 11 20 0 3 8 17 4 7 9 15 16");
        assertThat(tree.rangeSearch(12L, 20L)).isEqualTo("13 15 16 17 20");
        assertThat(tree.getHeight()).isEqualTo(6);

        tree.delete(20L);

        assertThat(tree.getSize()).isEqualTo(inputs.length - 1);
        assertThat(tree.bfs()).isEqualTo("10 5 13 2 6 11 17 0 3 8 15 4 7 9 16");
        assertThat(tree.rangeSearch(12L, 20L)).isEqualTo("13 15 16 17");
        assertThat(tree.getHeight()).isEqualTo(5);
    }

    @Test
    public void deleteWithRightChild() throws Exception {
        assertThat(tree.getSize()).isEqualTo(inputs.length);
        assertThat(tree.bfs()).isEqualTo("10 5 13 2 6 11 20 0 3 8 17 4 7 9 15 16");
        assertThat(tree.rangeSearch(12L, 20L)).isEqualTo("13 15 16 17 20");
        assertThat(tree.getHeight()).isEqualTo(6);

        tree.delete(13L);

        assertThat(tree.getSize()).isEqualTo(inputs.length - 1);
        assertThat(tree.bfs()).isEqualTo("10 5 15 2 6 11 20 0 3 8 17 4 7 9 16");
        assertThat(tree.rangeSearch(12L, 20L)).isEqualTo("15 16 17 20");
        assertThat(tree.getHeight()).isEqualTo(5);
    }

    @Test
    public void getMirrorEmpty() throws Exception {
        assertThat(new BinaryTree<Long>().getMirror().dstInOrder()).isEqualTo("");
    }
}