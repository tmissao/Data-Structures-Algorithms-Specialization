import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort3 {

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
     * A Better implementation of quickSort algorithm to work with an array where its values are not distinct.
     * Sorts the array using the quickSort algorithm which is O(nlog(n)) in the average case
     */
    private static void sort(long[] numbers, int start, int end) {
        if (start >= end) {
            return;
        }

        Pivot pivot = partition(numbers, start, end);

        sort(numbers, start, pivot.getLeftIndex());
        sort(numbers, pivot.getRightIndex(), end);
    }

    /**
     * Divides the array in three sub-arrays which the first part is lower than the pivot value,
     * the second is equals to the pivot value, and the thrid part is greater than the pivot value.
     */
    private static Pivot partition(long[] numbers, int start, int end){
        int pivot = start + (int)(Math.random() * ((end - start) + 1));
        int pivotDuplicates = 0;
        long value = numbers[pivot];

        int key = start;

        swap(numbers, pivot, end);

        for (int i = start; i < end - pivotDuplicates; i++) {
            if (numbers[i] < value) {
                swap(numbers, key++, i);
            }

            // When the value is equal to the pivot this value is swap with the
            // last array value that is not equals to the pivot
            // Since the element i was swapped it needs to be verified again
            if (numbers[i] == value) {
                swap(numbers, i, end - pivotDuplicates - 1);
                pivotDuplicates++;
                i--;
            }
        }

        swap(numbers, key, end);

        for (int i = 1; i <= pivotDuplicates; i++) {
            swap(numbers, key + i, end - i);
        }

        return new Pivot(key - 1, key + pivotDuplicates + 1);
    }

    /**
     * Swaps the value between two index in the array
     */
    private static void swap(long[] numbers, int a, int b) {
        long aux = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = aux;
    }

    public static void main(String[] args) {
        run();
    }
}

/**
 * Contains information about the pivot in quicksort
 */
class Pivot {

    private int leftIndex;
    private int rightIndex;

    Pivot(int leftIndex, int rightIndex) {
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
    }

    public int getLeftIndex() {
        return leftIndex;
    }

    public int getRightIndex() {
        return rightIndex;
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
