import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnapsackWithoutRepetitions {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int weight = scanner.nextInt();
        int numberOfItems = scanner.nextInt();

        Item[] items = new Item[numberOfItems];
        boolean[] used = new boolean[numberOfItems];
        long[][] DP = new long[weight + 1][items.length + 1];

        for(int i = 0; i < items.length; i++) {
            items[i] = new Item(scanner.nextInt(), scanner.nextInt());
        }

        System.out.println(calculateBestPickWithoutRepetition(weight, items, DP));
        buildSolution(weight, items.length, items, DP, used);

        for(int i = 0; i<used.length; i++) {
            System.out.print( used[i] ? (i + 1) + " " : "");
        }
    }

    /**
     * Calculates the best items without repetition to put into a knapsack given a weight
     * Complexity: O( w * k ) where w is the weight and k the number of items
     */
    private static long calculateBestPickWithoutRepetition(int weight, Item[] items, long[][] DP) {

        for(int w = 1; w <= weight; w++) {
            for(int i = 1; i <= items.length; i++) {
                DP[w][i] = DP[w][i-1];
                Item item = items[i-1];

                if (item.getWeight() <= w) {
                    DP[w][i] = Math.max(DP[w][i], DP[w - item.getWeight() ][i-1] + item.getValue());
                }
            }
        }

        return DP[DP.length -1][DP[0].length - 1];
    }

    /**
     * Indicates which items were put into the knapsack
     * Complexity: O( k ) where k is the number of items
     */
    private static void buildSolution(int weight, int currentItem, Item[] items, long[][] DP, boolean[] used) {

        if (currentItem == 0) {
            return;
        }

        int itemIndex = currentItem - 1;

        if (DP[weight][currentItem] == DP[weight][currentItem - 1]) {
            used[itemIndex] = false;
            buildSolution(weight, currentItem - 1, items, DP, used);
            return;
        }

        used[currentItem - 1] = true;
        buildSolution(weight - items[itemIndex].getWeight(), currentItem - 1, items, DP, used);
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