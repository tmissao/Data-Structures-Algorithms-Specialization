import java.util.LinkedList;
import java.util.Random;

/**
 * This Hash Structure just works with positive Integer values and the number of
 * space (buckets) must be lower than Integer.MAX_VALUE
 * */
public class HashInteger {

    /**
     * Biggest integer prime
     */
    private final static int PRIME = Integer.MAX_VALUE;

    /**
     * Not found value
     */
    public final static long NOT_FOUND = -1;

    /**
     * Random integer used to generated the hash value
     */
    private final int SEED_A;

    /**
     * Random integer used to generate the hash value
     */
    private final int SEED_B;

    /**
     * Number the element in Hash
     */
    private int size;

    /**
     * Number of Hash buckets
     */
    private int space;

    /**
     * Generates random values
     */
    private final Random random;

    /**
     * Hash Array
     */
    private LinkedList<Long>[] array;

    public HashInteger(int space) {
        this.space = space;
        this.array = new LinkedList[space];
        this.random = new Random();
        this.SEED_A = randomInteger(1, PRIME - 1);
        this.SEED_B = randomInteger(0, PRIME - 1);

        for(int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<>();
        }
    }

    /**
     * Generates randomInteger between a [min] - [max] range
     */
    public int randomInteger(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Universal Hash Function to avoid collision
     */
    public int universalHash(long element, int a, int b) {
        return (int)(((element * a) + b) % PRIME) % space ;
    }

    /**
     * Gets HashInteger LoadFactor
     */
    public double getLoadFactor() {
        return ((double)size)/space;
    }

    /**
     * Get the number os elements in Hash
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Adds an element in the Hash Array
     * Complexity: O(1 + loadFactor)
     */
    public void put(long element) {
        int key = universalHash(element, SEED_A, SEED_B);
        LinkedList<Long> list = array[key];

        if(!list.contains(element)) {
            list.add(element);
            size++;
        }
    }

    /**
     * Gets a Hash element if it exists in hash, otherwise returns -1
     * Complexity: O(1 + loadFactor)
     */
    public long get(long element) {
        int key = universalHash(element, SEED_A, SEED_B);
        LinkedList<Long> list = array[key];

        if(!list.contains(element)) {
            return NOT_FOUND;
        }

        return element;
    }

    /**
     * Removes a Hash element if it exists in hash and return it, otherwise returns -1
     * Complexity: O(1 + loadFactor)
     */
    public long remove(long element) {
        int key = universalHash(element, SEED_A, SEED_B);
        LinkedList<Long> list = array[key];

        if(!list.contains(element)) {
            return NOT_FOUND;
        }

        list.remove(element);
        size--;

        return element;
    }
}
