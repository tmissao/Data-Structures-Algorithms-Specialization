import java.util.LinkedList;
import java.util.Random;

public class HashString {

    /**
     * Biggest integer prime
     */
    private final static int PRIME = Integer.MAX_VALUE;

    /**
     * Not found value
     */
    public final static String NOT_FOUND = "-1";

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
    private LinkedList<String>[] array;

    public HashString(int space) {
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
     *
     * Polinomial Hash Function to avoid collision with strings
     */
    public int polynomialHash(String element, int a) {
        double value = 0;
        for(int i = element.length() - 1; i >= 0; i--) {
            value = (value * a + element.charAt(i)) % PRIME;
        }

        return universalHash((int) value, SEED_A, SEED_B);
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
    public void put(String element) {
        int key = polynomialHash(element, SEED_A);
        LinkedList<String> list = array[key];

        if(!list.contains(element)) {
            list.add(element);
            size++;
        }
    }

    /**
     * Gets a Hash element if it exists in hash, otherwise returns -1
     * Complexity: O(1 + loadFactor)
     */
    public String get(String element) {
        int key = polynomialHash(element, SEED_A);
        LinkedList<String> list = array[key];

        if(!list.contains(element)) {
            return NOT_FOUND;
        }

        return element;
    }

    /**
     * Removes a Hash element if it exists in hash and return it, otherwise returns -1
     * Complexity: O(1 + loadFactor)
     */
    public String remove(String element) {
        int key = polynomialHash(element, SEED_A);
        LinkedList<String> list = array[key];

        if(!list.contains(element)) {
            return NOT_FOUND;
        }

        list.remove(element);
        size--;

        return element;
    }
}
