import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnapsackWithRepetitions {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int weight = scanner.nextInt();
        int numberOfItems = scanner.nextInt();

        Item[] items = new Item[numberOfItems];
        long[] DP = new long[weight + 1];

        for(int i = 0; i < items.length; i++) {
            items[i] = new Item(scanner.nextInt(), scanner.nextInt());
        }

        System.out.println(calculateBestPickWithRepetition(weight, items, DP));
        System.out.println(buildSolution(weight, items, DP));
    }

    /**
     * Calculates the best items with repetition to put into a knapsack given a weight.
     * This algorithm uses the dynamic programming strategy, which calculates the best pick
     * for all previous weight. Otherwise a memory heap error can occur
     *
     * Complexity: O( w * k ) where w is the weight and k the number of items
     */
    private static long calculateBestPickWithRepetition(int weight, Item[] items, long[] DP) {

        for(int w = 0; w <= weight; w++) {
            bestPickWithRepetition(weight, items, DP);
        }

        return DP[weight];
    }

    /**
     * Calculates the best items with repetition to put into a knapsack given a weight
     */
    private static long bestPickWithRepetition(int weight, Item[] items, long[] DP) {
        if (weight == 0) {
            return 0;
        }

        if (DP[weight] != 0) {
            return DP[weight];
        }

        long max = Long.MIN_VALUE;

        for(Item item : items) {
            if (item.getWeight() <= weight) {
                long value = bestPickWithRepetition(weight - item.getWeight(), items, DP) + item.getValue();
                max = Math.max(max, value);
            }
        }

        DP[weight] = max;
        return max;
    }

    /**
     * Builds a string with the steps to pick the items to put into the knapsack
     */
    private static StringBuilder buildSolution(int weight, Item[] items, long[] DP) {

        if (weight == 0) {
            return new StringBuilder();
        }

        for(int i = 0; i<items.length; i++) {
            Item item = items[i];

            if (item.getWeight() <= weight && DP[weight] == DP[weight - item.getWeight()] + item.getValue()) {
                return buildSolution(weight - item.getWeight(), items, DP).append((i+1) + " ");
            }
        }

        // This case should not be reached, it is just to silence the compiler
        return new StringBuilder("ERROR");
    }


    public static void main(String[] args) {
        run();
    }
}

class Item {

    private final int weight;
    private final int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
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

    Integer nextInt() {
        return Integer.parseInt(next());
    }

    Long nextLong() {
        return Long.parseLong(next());
    }
}
