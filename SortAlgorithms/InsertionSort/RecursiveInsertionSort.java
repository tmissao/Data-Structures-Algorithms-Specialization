import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RecursiveInsertionSort {

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

        sort(numbers, 0);

        for(long n : numbers) {
            System.out.print(n + " ");
        }
    }

    /**
     * Sorts the array using the InsertionSort algorithm which is O(n^2) in the worst case
     */
    private static void sort(long[] numbers, int start) {
        if (start + 1 >= numbers.length) {
            return;
        }

        long aux = numbers[start + 1];
        int index = recursiveSwap(numbers, start, aux);
        numbers[index + 1] = aux;

        sort(numbers, start + 1);
    }

    private static int recursiveSwap(long[] numbers, int index, long value) {
        if (index < 0 || numbers[index] < value) {
            return index;
        }

        numbers[index + 1] = numbers[index];
        return recursiveSwap(numbers, index - 1, value);
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
