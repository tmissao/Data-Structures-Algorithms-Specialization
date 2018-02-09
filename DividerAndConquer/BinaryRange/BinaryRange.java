import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinaryRange {

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

        long value = scanner.nextLong();
        int[] range = binaryRange(numbers, value);

        System.out.println(range[0] + ", " + range[1]);
    }

    /**
     * Searches by lowerbound and upperbound of a value in the array
     * return -1 if value is not in the sequence.
     */
    private static int[] binaryRange(long[] numbers, long value) {
        if (value > numbers[numbers.length - 1] || value < numbers[0]) {
            return new int[] {-1, -1};
        }

        int lowerbound = search(numbers, 0, numbers.length, value, true);
        int upperbound = lowerbound == -1 ? -1 : search(numbers, lowerbound, numbers.length - 1, value, false);

        return new int[] {lowerbound, upperbound};
    }

    /**
     * Searches by value bounds using binarySearch
     */
    private static int search(long[] numbers, int start, int end, long value, boolean lowerbound) {
        // base case array is over
        if (start > end) {
            return -1;
        }

        int middle = (start + end) / 2;

        // found the number, so it is necessary to check if it is unique or there are duplicates
        if (numbers[middle] == value) {
            // if is searching for lowerBound is necessary go down, otherwise up
            int index = lowerbound ? search(numbers, start, middle - 1, value, lowerbound)
                                   : search(numbers, middle + 1, end, value, lowerbound);

            // No duplicate in the array
            if (index == -1) {
                return middle;
            }

            // Checks if the index returned is from a duplicate, if it is return index,
            // otherwise return the current index (middle)
            return numbers[index] == value ? index : middle;
        }

        // Splits the array in the half
        int index = numbers[middle] > value ? search(numbers, start, middle - 1, value, lowerbound)
                                            : search(numbers, middle + 1, end, value, lowerbound);

        // If index != -1 a lowerbound/upperbound was found
        if (index != -1) {
            return index;
        }

        // If was looking for a lowerbound checks if the current index(middle) is lower than value, if it is return current index
        // otherwise the lowerbound was not found yet
        if (lowerbound) {
            return numbers[middle] < value ? middle : -1;
        }

        // If was looking for a upperbound checks if the current index(middle) is greater than value, if it is return current index
        // otherwise the upperbound was not found yet
        return numbers[middle] > value ? middle : - 1;
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

