import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberOfInversions {

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

        System.out.println(calculateNumberOfInversions(numbers, 0, numbers.length-1));
    }

    /**
     * Calculates the number of inversion in an array using a the mergeSort algorithm
     * Complexity: O(nlog(n))
     */
    private static long calculateNumberOfInversions(long[] numbers, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int middle = (start + end) / 2;
        long inversions = 0;

        inversions += calculateNumberOfInversions(numbers, start, middle);
        inversions += calculateNumberOfInversions(numbers, middle + 1, end);
        inversions += merge(numbers, start, middle, end);

        return inversions;
    }

    /**
     * Merges two sorted arrays in ascending order, and calculates their inversions,
     * An inversion is detected when an element from the right array enters in the merged
     * array before an element from left array.
     */
    private static long merge(long[] numbers, int start, int middle, int end) {
        long[] left = new long[middle - start + 1];
        long[] right = new long[end - middle];
        long inversions = 0;

        for(int i = 0; i < left.length; i++) left[i] = numbers[start + i];
        for(int i = 0; i < right.length; i++) right[i] = numbers[middle + i + 1];

        int leftIndex = 0;
        int rightIndex = 0;
        int index = start;

        while( leftIndex < left.length && rightIndex < right.length) {
            long value;

            if (right[rightIndex] < left[leftIndex] ) {
                value = right[rightIndex++];
                inversions += (left.length - leftIndex) ;
            } else {
                value = left[leftIndex++];
            }

            numbers[index++] = value;
        }

        while(leftIndex < left.length) {
            numbers[index++] = left[leftIndex++];
        }
        while(rightIndex < right.length) numbers[index++] = right[rightIndex++];

        return inversions;
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

