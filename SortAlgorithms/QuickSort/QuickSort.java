import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {

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
     * Sorts the array using the quickSort algorithm which is O(nlog(n)) in the average case
     */
    private static void sort(long[] numbers, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = partition(numbers, start, end);

        sort(numbers, start, pivot - 1);
        sort(numbers, pivot + 1, end);
    }

    /**
     * Divides the array in two subarrays which the left part is lower than the pivot value and
     * the right part is greater or equals than the pivot
     */
    private static int partition(long[] numbers, int start, int end){
        int pivot = start + (int)(Math.random() * ((end - start) + 1));
        long value = numbers[pivot];

        int key = start;

        swap(numbers, pivot, end);

        for (int i = start; i < end; i++) {
            if (numbers[i] < value) {
                swap(numbers, key++, i);
            }
        }

        swap(numbers, key, end);

        return key;
    }

    /*private static int partition(long[] numbers, int start, int end){
        int pivot = (start + end) / 2;
        long value = numbers[pivot];

        int leftIndex = start;
        int rightIndex = end -1;

        swap(numbers, pivot, end);

        while(leftIndex < rightIndex) {
            while(numbers[leftIndex] < value) leftIndex++;
            while(numbers[rightIndex] > value) rightIndex--;
            if (leftIndex < rightIndex) swap(numbers, leftIndex++, rightIndex--);
        }

        swap(numbers, leftIndex, end);

        return leftIndex;
    }*/

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
