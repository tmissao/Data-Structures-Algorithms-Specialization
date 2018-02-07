import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {

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

        sort(numbers, 0, numbers.length - 1);

        for(long n : numbers) {
            System.out.print(n + " ");
        }
    }

    /**
     * Sorts the array using the mergeSort algorithm which is O(nlog(n)) in the worst case
     */
    private static void sort(long[] numbers, int start, int end) {
        if (start >= end) {
            return;
        }

        int middle = (end + start) / 2;

        sort(numbers, start, middle);
        sort(numbers, middle + 1, end);
        merge(numbers,start, middle, end);
    }

    /**
     * Merges the two sorted partial parts of the arrays
     */
    private static void merge(long[] numbers, int start, int middle, int end){
        long[] left = new long[middle - start + 1];
        long[] right = new long[end - middle];

        for(int i = 0; i<left.length; i++) {
            left[i] = numbers[start + i];
        }

        for(int i = 0; i<right.length; i++) {
            right[i] = numbers[middle + 1 + i];
        }

        int leftIndex = 0;
        int rightIndex = 0;
        int index = start;

        while(leftIndex < left.length && rightIndex < right.length) {
            numbers[index++] = left[leftIndex] < right[rightIndex] ? left[leftIndex++] : right[rightIndex++];
        }

        while(leftIndex < left.length) {
            numbers[index++] = left[leftIndex++];
        }

        while(rightIndex < right.length) {
            numbers[index++] = right[rightIndex++];
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
