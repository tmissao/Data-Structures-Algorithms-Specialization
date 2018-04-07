import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T extends Comparable> {

    /**
     * Tree`s root element
     */
    private Node<T> root;

    private void dstInOrder(Node<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        dstInOrder(node.getLeft(), sb);
        sb.append(" ").append(node.getKey());
        dstInOrder(node.getRight(), sb);
    }

    private void dstPreOrder(Node<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append(" ").append(node.getKey());
        dstPreOrder(node.getLeft(), sb);
        dstPreOrder(node.getRight(), sb);
    }

    private void dstPostOrder(Node<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        dstPostOrder(node.getLeft(), sb);
        dstPostOrder(node.getRight(), sb);
        sb.append(" ").append(node.getKey());
    }

    private long getHeight(Node<T> node, long height) {
        if (node == null) {
            return 0L;
        }
        return 1 + Math.max(getHeight(node.getLeft(), height), getHeight(node.getRight(), height));
    }

    private long getSize(Node<T> node) {
        if (node == null) {
            return 0L;
        }
        return 1 + getSize(node.getLeft()) + getSize(node.getRight());
    }

    private Node<T> buildMirror(Node<T> node) {
        if (node == null) {
            return null;
        }

        Node<T> mirror = new Node<>(node.getKey());
        mirror.setLeft(buildMirror(node.getRight()));
        mirror.setRight(buildMirror(node.getLeft()));
        return mirror;
    }

    /**
     * Searches by an element in the tree.
     * If this element exists in the tree returns its node
     * If this element not exists in the tree returns who should be its parent node
     * If null is returned it means this element should be tree's root
     * <p>
     * Complexity: O(log(n)) if tree is balanced, otherwise O(n)
     */
    private Node<T> search(Node<T> node, T element) {
        if (node == null) return null;

        if (node.getKey().equals(element)) {
            return node;
        }

        boolean isLess = node.getKey().compareTo(element) == 1;

        Node<T> result = search(isLess ? node.getLeft() : node.getRight(), element);

        // If the element is not in the tree returns who should be its parent
        return result == null ? node : result;
    }

    /**
     * Searches by the next Right Ancestor of a node
     * Complexity: O(log(n)) if tree is balanced, otherwise O(n)
     */
    private Node<T> getRightAncestor(Node<T> node) {
        if (node.getRight() == null) return null;

        Node ancestor = node.getRight();

        while (ancestor.getLeft() != null) {
            ancestor = ancestor.getLeft();
        }

        return ancestor;
    }

    /**
     * Searches by the next Left Descendent of a node
     * Complexity: O(log(n)) if tree is balanced, otherwise O(n)
     */
    private Node<T> getLeftDescendant(Node<T> node) {
        if (node == null) return null;

        Node descendant = node.getParent();

        while (descendant != null && node.getKey().compareTo(descendant.getKey()) == 1) {
            descendant = descendant.getParent();
        }

        return descendant;
    }

    /**
     * Searches by the next element of a currently element
     * If there is not a next element null is returned
     * <p>
     * Complexity: O(log(n)) if tree is balanced, otherwise O(n)
     */
    private Node<T> next(Node<T> node) {
        if (node == null) return null;

        // If the node has a right child the next element is the first node on the right
        // that does not have a left child
        if (node.getRight() != null) {
            return getRightAncestor(node);
        }

        // If the node does not have a right child the next element descendant (parent) that is
        // greater than its value
        return getLeftDescendant(node);
    }

    /**
     * Inserts an element into tree
     * Complexity: O(log(n)) if tree is balanced, otherwise O(n)
     */
    public void insert(T element) {
        Node<T> node = new Node<>(element);
        Node<T> parent = search(root, element);

        if (parent == null) {
            root = node;
            return;
        }

        node.setParent(parent);

        boolean isLess = parent.getKey().compareTo(element) == 1;

        if (isLess) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
    }

    /**
     * Deletes an element in tree
     * Complexity: O(log(n)) if tree is balanced, otherwise O(n)
     */
    public void delete(T element) {
        Node<T> result = search(root, element);

        if (result == null || result.getKey().compareTo(element) != 0) {
            return;
        }


        if (result.getRight() == null) {
            // indicates if the deleted node is left or right child of its parent
            boolean isLeft = result.getParent().getLeft().equals(result);

            if (isLeft) {
                result.getParent().setLeft(result.getLeft());
            } else {
                result.getParent().setRight(result.getLeft());
            }

            if (result.getLeft() != null) {
                result.getLeft().setParent(result.getParent());
            }
        } else {
            Node<T> next = next(result);
            result.setKey(next.getKey());

            // indicates if the deleted node is left or right child of its parent
            boolean isLeft = next.getParent().getLeft().equals(next);
            if (isLeft) {
                next.getParent().setLeft(next.getRight());
            } else {
                next.getParent().setRight(next.getRight());
            }

            if (next.getRight() != null) {
                next.getRight().setParent(next.getParent());
            }
        }

    }

    /**
     * Gets the string representation of the tree in order (Left, Node, Right)
     * Complexity: O(n)
     */
    public String dstInOrder() {
        StringBuilder sb = new StringBuilder();
        dstInOrder(root, sb);
        return sb.length() == 0 ? sb.toString() : sb.deleteCharAt(0).toString();
    }

    /**
     * Gets the string representation of the tree in pre order (Node, Left, Right)
     * Complexity: O(n)
     */
    public String dstPreOrder() {
        StringBuilder sb = new StringBuilder();
        dstPreOrder(root, sb);
        return sb.length() == 0 ? sb.toString() : sb.deleteCharAt(0).toString();
    }

    /**
     * Gets the string representation of the tree in post order (Left, Right, Node)
     * Complexity: O(n)
     */
    public String dstPostOrder() {
        StringBuilder sb = new StringBuilder();
        dstPostOrder(root, sb);
        return sb.length() == 0 ? sb.toString() : sb.deleteCharAt(0).toString();
    }

    /**
     * Gets the string representation by height
     * Complexity: O(n)
     */
    public String bfs() {
        StringBuilder sb = new StringBuilder();

        if (root == null) {
            return sb.toString();
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }

            sb.append(" ").append(node.getKey());
        }

        return sb.deleteCharAt(0).toString();
    }

    /**
     * Search by a specific element in the tree
     * <p>
     * Complexity: O(log(n)) if tree is balanced, otherwise O(n)
     */
    public T search(T element) {
        Node<T> result = search(root, element);

        // Since search method returns null it tree is empty or who should be parent of an element
        // in case that is does not exists in the tree, it is necessary to check if the result is the
        // element that is searching by.
        return result == null || result.getKey().compareTo(element) != 0 ? null : result.getKey();
    }

    public String rangeSearch(T start, T end) {
        StringBuffer sb = new StringBuffer("");

        if (start.compareTo(end) == 1) {
            return sb.toString();
        }

        Node<T> node = search(root, start);

        while (node != null && node.getKey().compareTo(end) != 1) {
            if (start.compareTo(node.getKey()) != 1) {
                sb.append(" ").append(node.getKey());
            }
            node = next(node);
        }

        return sb.length() != 0 ? sb.deleteCharAt(0).toString() : sb.toString();
    }

    /**
     * Get the tree`s height
     * Complexity: (n)
     */
    public Long getHeight() {
        return getHeight(root, 0);
    }

    /**
     * Get the number of elements in the tree
     * Complexity: (n)
     */
    public Long getSize() {
        return getSize(root);
    }

    /**
     * Builds a mirror representation of the tree
     * Complexity: (n)
     */
    public BinaryTree<T> getMirror() {
        BinaryTree<T> mirror = new BinaryTree<T>();
        mirror.root = mirror.buildMirror(root);
        return mirror;
    }
}

class Node<T extends Comparable> {

    private T key;
    private Node<T> parent;
    private Node<T> right;
    private Node<T> left;

    public Node(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}
