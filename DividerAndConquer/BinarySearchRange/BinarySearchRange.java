import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearchRange {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int quantity = scanner.nextInt();
        long[] numbers = new long[quantity];

        for(int i = 0; i < quantity; i++) {
            numbers[i] = scanner.nextLong();
        }

       int value = scanner.nextInt();
        int[] range = searchRange(numbers, 0, numbers.length -1, value);
        System.out.println("[" + range[0] + " - " + range[1] + "]");
    }


    /**
     * Searches in the array using Binary Search to get the lower bound and the upper bound of a value
     * If value is not in the array the value returned is {-1, -1}
     */
    private static int[] searchRange(long[] numbers, int start, int end, long value) {

        // Value is not in the array
        if (numbers[0] > value || numbers[numbers.length - 1] < value) {
            return new int[] {-1, -1};
        }

        int lowerBound = searchRangeBound(numbers, start, end, value, false);
        int upperBound = searchRangeBound(numbers, start, end, value, true);

        System.out.println(lowerBound + " : " + upperBound);

        // Value is not in the array
        if (lowerBound > upperBound) {
            return new int[] {-1, -1};
        }


        return new int[] {lowerBound, upperBound};
    }

    /**
     * Searches in the array using Binary Search the get the bounds of a value
     * Complexity: O(log(n))
     */
    private static int searchRangeBound(long[] numbers, int start, int end, long value, boolean upper) {

        if (end - start <= 1) {
            if(numbers[ upper ? end : start ] == value) {
                return upper ? end : start;
            }

            return upper ? Math.max(0, end - 1) : Math.min(start + 1, numbers.length - 1);
        }

        int middle = (start + end) / 2;

        if (numbers[middle] == value) {
            if (upper) {
                return searchRangeBound(numbers, middle + 1, end, value, upper);
            }

            return searchRangeBound(numbers, start, middle - 1, value, upper);
        }

        if (numbers[middle] > value) {
            return searchRangeBound(numbers, start, middle -1, value, upper);
        }

        return searchRangeBound(numbers, middle + 1, end, value, upper);
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
