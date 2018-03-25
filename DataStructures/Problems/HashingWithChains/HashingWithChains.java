import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class HashingWithChains {

    private static final String ADD_COMMAND = "add";
    private static final String DEL_COMMAND = "del";
    private static final String FIND_COMMAND = "find";
    private static final String CHECK_COMMAND = "check";

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int buckets = scanner.nextInt();
        int numberOfCommands = scanner.nextInt();

        HashString hash = new HashString(buckets);

        for(int i = 0; i < numberOfCommands; i++) {
            executeCommand(scanner.nextLine(), hash);
        }
    }

    /**
     * Executes the command sent. The possible commands are: [ADD_COMMAND], [DEL_COMMAND] and [FIND_COMMANDS]
     * Complexity: O(1 + loadFactor)
     */
    public static void executeCommand(String command, HashString hash) {
        String[] values = command.split("\\s+");

        switch (values[0]) {
            case ADD_COMMAND: {
                hash.put(values[1]);
                break;
            }
            case DEL_COMMAND: {
                hash.remove(values[1]);
                break;
            }
            case FIND_COMMAND: {
                System.out.println(hash.get(values[1]));
                break;
            }
            default: {
                LinkedList<String> bucket = hash.getBucket(Integer.parseInt(values[1]));
                for(int i = 0; i < bucket.size(); i++) {
                    if (i == 0) { System.out.print(bucket.get(i)); }
                    else System.out.print(" " + bucket.get(i));
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}

class HashString {

    private static final String NOT_FOUND = "no";
    private static final String FOUND = "yes";

    /**
     * Biggest integer prime
     */
    private final static long PRIME = 1000000007;

    /**
     * Random integer used to generated the hash value
     */
    private final int SEED_A;

    /**
     * Number of Hash buckets
     */
    private int space;

    /**
     * Hash Array
     */
    private LinkedList<String>[] array;

    public HashString(int space) {
        this.space = space;
        this.array = new LinkedList[space];
        this.SEED_A = 263;

        for(int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<>();
        }
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

        return (int) value % space;
    }

    /**
     * Adds an element in the Hash Array
     * Complexity: O(1 + loadFactor)
     */
    public void put(String element) {
        int key = polynomialHash(element, SEED_A);
        LinkedList<String> list = array[key];

        if(!list.contains(element)) {
            list.addFirst(element);
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

        return FOUND;
    }

    /**
     * Gets a Hash bucket
     * Complexity: O(1)
     */
    public LinkedList<String> getBucket(int index) {
        return array[index];
    }

    /**
     * Removes a Hash element if it exists in hash and return it, otherwise returns -1
     * Complexity: O(1 + loadFactor)
     */
    public void remove(String element) {
        int key = polynomialHash(element, SEED_A);
        LinkedList<String> list = array[key];

        if(!list.contains(element)) {
            return;
        }

        list.remove(element);
    }
}

/**
 * A faster scanner implementation
 */
class FasterScanner {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private String delimiter;

    FasterScanner(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        delimiter = " \t\n\r\f";
    }

    FasterScanner(InputStream stream, String delimiter) {
        this(stream);
        this.delimiter = delimiter;
    }

    String next() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine(), delimiter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tokenizer.nextToken();
    }

    String nextLine() {
        try {
            return reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    Integer nextInt() {
        return Integer.parseInt(next());
    }

    Long nextLong() {
        return Long.parseLong(next());
    }
}
