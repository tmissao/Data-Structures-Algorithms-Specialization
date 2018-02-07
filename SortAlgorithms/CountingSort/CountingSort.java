import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountingSort {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int quantity = scanner.nextInt();
        int[] numbers = new int[quantity];
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < quantity; i++) {
            numbers[i] = scanner.nextInt();
            if (max < numbers[i]) {
                max = numbers[i];
            }
        }

        sort(numbers, max);

        for(long n : numbers) {
            System.out.print(n + " ");
        }
    }

    /**
     * Sorts the array using the countingSort algorithm which is O(n), when maxValue < nlog(n)
     */
    private static void sort(int[] numbers, int maxValue) {
        int[] counting = new int[maxValue+1];

        for(int n : numbers) counting[n]++;

        int index = 0;
        for(int num = 0; num < counting.length; num++) {
            for(int i = 0; i < counting[num]; i++) {
                numbers[index++] = num;
            }
        }
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
