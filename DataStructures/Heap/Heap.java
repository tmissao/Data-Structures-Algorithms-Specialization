public class Heap {

    /**
     * Currently heap size
     */
    private int size;

    /**
     * Max heap size
     */
    private int maxSize;

    private long[] heap;

    public Heap (int maxSize) {
        this.maxSize = maxSize;
        this.heap = new long[maxSize];
        this.size = 0;
    }

    /**
     * Gets the parent of an index
     */
    private int getParent(int index) {
        return index / 2;
    }

    /**
     * Gets the left child of a node
     */
    private int getLeftChild(int index) {
        return 2 * index;
    }

    /**
     * Gets the right child of a node
     */
    private int getRightChild(int index) {
        return 2 * index + 1;
    }

    /**
     * Swaps two elements in the array
     */
    private void swap(int a, int b) {
        long aux = heap[a];
        heap[a] = heap[b];
        heap[b] = aux;
    }

    /**
     * Rebuilds the heap down to up, moving the child if it is greater than parent
     */
    private void siftUp(int index) {
        int current = index;
        while(current > 0 ) {
            int parent = getParent(current);

            if (heap[parent] >= heap[current]) {
                break;
            }

            swap(parent, current);
            current = parent;
        }
    }

    /**
     * Rebuilds the heap up to down, moving the parent down if it is lower than children
     */
    private void siftDown(int index) {
        int current = index;
        while(true) {

            int maxIndex = current;
            int leftChild = getLeftChild(current);
            int rightChild = getRightChild(current);

            if (leftChild < size && heap[leftChild] > heap[maxIndex]) {
                maxIndex = leftChild;
            }

            if (rightChild < size && heap[rightChild] > heap[maxIndex]) {
                maxIndex = rightChild;
            }

            if(maxIndex == current) {
                break;
            }

            swap(current, maxIndex);
            current = maxIndex;
        }
    }

    /**
     * Checks if heap is empty
     * @return O(1)
     */
    public boolean isEmpty() {
        return size != 0;
    }

    /**
     *  Inserts an element in the heap
     *  Complexity: O(log(n)) where n is the height of the heap
     */
    public void insert(long e) {
        if(size >= maxSize) {
            throw new RuntimeException("Max capacity reached");
        }

        heap[size] = e;
        size++;
        siftUp(size - 1);
    }

    /**
     *  Gets the max value in the heap
     *  Complexity: O(1)
     */
    public long getMax() {
        if (size == 0) {
            throw new RuntimeException("No elements in heap");
        }

        return heap[0];
    }

    /**
     * Extracts the max value of a heap
     * Complexity: O(log(n)) where n is the height of the heap
     */
    public long extractMax() {
        if (size == 0) {
            throw new RuntimeException("No elements in heap");
        }

        long value = heap[0];
        swap(0, size - 1);
        size--;
        siftDown(0);

        return value;
    }

    /**
     * Removes one element from the heap
     * Complexity: O(log(n)) where n is the height of the heap
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Index outrange");
        }

        heap[index] = Long.MAX_VALUE;
        siftUp(index);
        extractMax();
    }

    /**
     * Changes priority of one element in the heap
     * Complexity: O(log(n)) where n is the height of the heap
     */
    public void changePriority(int index, long newValue) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Index outrange");
        }

        long previousValue = heap[index];
        heap[index] = newValue;

        if (newValue > previousValue) {
            siftUp(index);
        } else {
            siftDown(index);
        }
    }
}
