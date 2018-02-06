import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RecursiveBubbleSort {

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

        recursiveSort(numbers, 0);

        for(long n : numbers) {
            System.out.print(n + " ");
        }
    }

    /**
     *  Sorts the array using a recursive version of bubblesort algorithm which is O(nˆ2) in the worst case
     */
    private static void recursiveSort(long[] numbers, int start) {
        if (start >= numbers.length) {
            return;
        }
        recursiveSwap(numbers, 0, numbers.length - start - 1);
        recursiveSort(numbers, start + 1);
    }

    /**
     *  Compares and swaps each element if it is greater from index to end
     */
    private static void recursiveSwap(long[] numbers, int index, int end) {
        if (index >= end) {
            return;
        }

        if (numbers[index] > numbers[index + 1]) {
            long aux = numbers[index];
            numbers[index] = numbers[index + 1];
            numbers[index + 1] = aux;
        }

        recursiveSwap(numbers, index + 1, end);
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