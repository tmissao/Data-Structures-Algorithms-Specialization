import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ConvertArrayIntoHeap {

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

        System.out.print(convertArrayToHeap(numbers));

    }

    /**
     * Transforms an array in a heap
     */
    private static String convertArrayToHeap(long[] numbers) {

        long swapCount = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = numbers.length/2 - 1; i >= 0; i--) {
            swapCount += buildHeap(numbers, numbers.length, i, sb);
        }

        return swapCount + "\n" + sb.toString();
    }

    /**
     * Executes the algorithm siftDown in a heap
     */
    private static long buildHeap(long[] numbers, int size, int index, StringBuilder sb) {
        int current = index;
        long swapCount = 0;

        while(true) {
            int minIndex = current;
            int leftChild = 2*current + 1;
            int rightChild = 2*current + 2;

            if (leftChild < size && numbers[leftChild] < numbers[minIndex]) {
                minIndex = leftChild;
            }

            if (rightChild < size && numbers[rightChild] < numbers[minIndex]) {
                minIndex = rightChild;
            }

            if(minIndex == current) {
                break;
            }

            swapCount++;
            sb.append(current).append(" ").append(minIndex).append("\n");
            swap(numbers, current, minIndex);
            current = minIndex;
        }

        return swapCount;
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
