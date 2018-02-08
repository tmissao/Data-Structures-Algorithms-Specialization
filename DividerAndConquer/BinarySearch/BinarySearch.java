import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch {

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

        quantity = scanner.nextInt();
        long[] searches = new long[quantity];

        for(int i = 0; i < quantity; i++) {
            searches[i] = scanner.nextLong();
        }

        for (int i = 0; i < searches.length; i++) {
            int index = search(numbers, 0, numbers.length - 1, searches[i]);

            System.out.print( i == 0 ? index : " " + index);
        }
    }

    /**
     * Searches in the array using Binary Search which is O(nlog(n)) in the worst case
     */
    private static int search(long[] numbers, int start, int end, long value) {
        if (start > end){
            return  -1;
        }

        int middle = (start + end) / 2;

        if (numbers[middle] == value) {
            return middle;
        }

        if (numbers[middle] > value) {
            return search(numbers, start, middle - 1, value);
        }

        return search(numbers, middle + 1, end, value);
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