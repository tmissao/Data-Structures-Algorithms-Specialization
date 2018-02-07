import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HeapSort {

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
     * Sorts the array using the heapSort algorithm which is O(nlog(n)) in the worst case
     */
    private static void sort(long[] numbers) {

        for(int i = numbers.length/2 - 1; i >= 0; i--) {
            buildHeap(numbers, numbers.length, i);
        }

        for(int i = numbers.length -1; i >= 0; i--) {
            swap(numbers, 0, i);
            buildHeap(numbers, i, 0);
        }
    }

    private static void buildHeap(long[] numbers, int size, int index) {
        int largest = index;
        int left = 2*index + 1;
        int right = 2*index + 2;

        if (left < size && numbers[left] > numbers[largest]) {
            largest = left;
        }

        if (right < size && numbers[right] > numbers[largest]) {
            largest = right;
        }

        if (index != largest) {
            swap(numbers, index, largest);
            buildHeap(numbers, size, largest);
        }
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
