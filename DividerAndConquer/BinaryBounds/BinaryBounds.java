import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinaryBounds {

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

        long number = scanner.nextLong();
        int[] range = binaryRange(numbers, number);

        System.out.println(range[0] + ", " + range[1]);
    }

    private static int[] binaryRange(long[] numbers, long value) {

        int lowerbound = search(numbers, 0, numbers.length, value, true);
        int upperbound = lowerbound == -1 ? -1 : search(numbers, lowerbound, numbers.length - 1, value, false);

        return new int[] {lowerbound, upperbound};
    }

    private static int search(long[] numbers, int start, int end, long value, boolean lowerbound) {
        if (start > end) {
            return -1;
        }

        if (start == end) {
            return numbers[start] == value ? start : -1;
        }

        int middle = (start + end) / 2;

        if (numbers[middle] == value) {
            int index = lowerbound ? search(numbers, start, middle - 1, value, lowerbound)
                                   : search(numbers, middle + 1, end, value, lowerbound);

            return index == -1 ? middle : index;
        }

        if (numbers[middle] > value) {
            return search(numbers, start, middle - 1, value, lowerbound);
        }

        return search(numbers, middle + 1, end, value, lowerbound);
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
