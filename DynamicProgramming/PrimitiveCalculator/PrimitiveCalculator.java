import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrimitiveCalculator {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int value = scanner.nextInt();

        int[] multiplications = new int[] {3, 2};
        int[] additions = new int[] {1};
        int[] DP = new int[value + 1];

        calculateMinimumOperations(value, multiplications, additions, DP);

        System.out.println(DP[value]);
        System.out.println(buildValues(value, multiplications, additions, DP));
    }

    /**
     * Calculates the minimum number of operations to build a specific number just using multiplication
     * operation with [mult] numbers and addition operations with [add] numbers. This algorithm uses the dynamic
     * programming strategy, which calculates the operation necessary to build all previous number until [value], then
     * calculates the [value]. Otherwise a memory heap error can occur if n is high.
     *
     *
     * Complexity: O(n + k + l) => O(n) where n is the value desired, k is the numbers in multiplication array and
     * l the numbers in the add array
     */
    private static int calculateMinimumOperations(int value, int[] mult, int[] add, int[] DP) {
        for(int i = 0; i <= value; i++) {
            minimumOperations(i, mult, add, DP);
        }

        return DP[value];
    }

    /**
     * Calculates the minimum number of operations to build a specific number just using multiplication
     * operation with [mult] numbers and addition operations with [add] numbers.
     */
    private static int minimumOperations(int value, int[] mult, int[] add, int[] DP) {
        if (value == 0 || value == 1) {
            DP[value] = 0;
            return 0;
        }

        if (DP[value] != 0) {
            return DP[value];
        }

        int minimum = Integer.MAX_VALUE;

        for(int divisor : mult) {
            if (value >= divisor && value % divisor == 0) {
                int newValue = value / divisor;
                int result = minimumOperations(newValue, mult, add, DP) + 1;
                if (minimum > result) {
                    minimum = result;
                }
            }
        }

        for (int addiction : add) {
            if (value >= addiction) {
                int newValue = value - addiction;
                int result = minimumOperations(newValue, mult, add, DP) + 1;
                if (minimum > result) {
                    minimum = result;
                }
            }
        }

        DP[value] = minimum;
        return minimum;
    }

    /**
     * Builds a string with the steps to build a specific number, this method uses the array [DP]
     * built in the process to calculate the minimum number of operations to build a number using
     * addition [add] and multiplication [mult] operations.
     */
    private static StringBuilder buildValues(int value, int[] mult, int[] add, int[] DP) {
        if( value == 0 || value == 1) {
            return new StringBuilder(String.valueOf(value));
        }

        for(int divisor : mult) {
            int newValue = value / divisor;
            if (value % divisor == 0 && DP[newValue] + 1 == DP[value]) {
                return buildValues(newValue, mult, add, DP).append(" ").append(value);
            }
        }

        for (int addiction : add) {
            int newValue = value - addiction;
            if (value >= addiction && DP[newValue] + 1 == DP[value]) {
                return buildValues(newValue, mult, add, DP).append(" ").append(value);
            }
        }

        // This case should not be reached, it is just to silence the compiler
        return new StringBuilder("ERROR");
    }

    public static void main(String[] args) {
        run();
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