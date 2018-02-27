import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class BinaryTreeTest {

  private BinaryTree<Long> tree;

  private void insertTest() {
    long[] inputs = {10,5,2,0,3,4,6,8,7,9,13,20,17,15,11,20,20,20};
    for(long n : inputs) tree.insert(n);
  }

  @Before
  public void setUp() throws Exception {
    this.tree = new BinaryTree<>();
    insertTest();
  }


  @Test
  public void dstInOrder() throws Exception {
    assertThat(tree.dstInOrder()).isEqualTo("0 2 3 4 5 6 7 8 9 10 11 13 15 17 20 20 20 20");
  }

  @Test
  public void dstInOrderEmpty() throws Exception {
    assertThat(new BinaryTree<Long>().dstInOrder()).isEqualTo("");
  }

  @Test
  public void dstPreOrder() throws Exception {
    assertThat(tree.dstPreOrder()).isEqualTo("10 5 2 0 3 4 6 8 7 9 13 11 20 17 15 20 20 20");
  }

  @Test
  public void dstPreOrderEmpty() throws Exception {
    assertThat(new BinaryTree<Long>().dstPreOrder()).isEqualTo("");
  }

  @Test
  public void dstPostOrder() throws Exception {
    assertThat(tree.dstPostOrder()).isEqualTo("0 4 3 2 7 9 8 6 5 11 15 17 20 20 20 20 13 10");
  }

  @Test
  public void dstPostOrderEmpty() throws Exception {
    assertThat(new BinaryTree<Long>().dstPostOrder()).isEqualTo("");
  }

  @Test
  public void bfs() throws Exception {
    assertThat(tree.bfs()).isEqualTo("10 5 13 2 6 11 20 0 3 8 17 20 4 7 9 15 20 20");
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
    assertThat(tree.getSize()).isEqualTo(18);
  }

  @Test
  public void getSizeEmpty() throws Exception {
    assertThat(new BinaryTree<Long>().getSize()).isEqualTo(0);
  }

  @Test
  public void getMirror() throws Exception {
    assertThat(tree.getMirror().dstInOrder()).isEqualTo("20 20 20 20 17 15 13 11 10 9 8 7 6 5 4 3 2 0");
  }

  @Test
  public void getMirrorEmpty() throws Exception {
    assertThat(new BinaryTree<Long>().getMirror().dstInOrder()).isEqualTo("");
  }
}