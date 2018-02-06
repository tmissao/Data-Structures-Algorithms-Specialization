import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SelectionSort {

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

        sort(numbers);

        for(long n : numbers) {
            System.out.print(n + " ");
        }
    }

    /**
     * Sorts the array using the SelectionSort algorithm which is O(n^2) in the worst case
     */
    private static void sort(long[] numbers) {
        for(int i = 0; i < numbers.length; i++) {
            int minIndex = selectMinIndex(numbers, i);
            if (i != minIndex) {
                long aux = numbers[i];
                numbers[i] = numbers[minIndex];
                numbers[minIndex] = aux;
            }
        }
    }

    /**
     * Selects the index of the minimum value between numbers[start ... n]
     */
    private static int selectMinIndex(long[] numbers, int start) {
        int min = start;
        for (int i = start; i < numbers.length; i++) {
            if (numbers[min] > numbers[i]) {
                min = i;
            }
        }

        return min;
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
