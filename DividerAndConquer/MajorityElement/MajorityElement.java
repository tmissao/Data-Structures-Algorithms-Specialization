import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MajorityElement {

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

        System.out.println(searchByMajority(numbers, 0, numbers.length - 1, numbers.length/2));
    }

    /**
     * Searches by a majority of a value in the array
     * This algorithm is an improved version of quickSort, thus its average complexity is O(n)
     * Since if the array is divided in the half the algorithm return in the first recursion.
     */
    private static int searchByMajority(long[] numbers, int start, int end, int majority) {
        if (start > end || end - start + 1 <= majority){
            return  0;
        }

        Pivot pivot = partition(numbers, start, end);

        if (pivot.getDuplicates() > majority) {
            return 1;
        }

        int leftResult = searchByMajority(numbers, start, pivot.getLeftIndex(), majority);
        int rightResult = searchByMajority(numbers, pivot.getRightIndex(), end, majority);

        return leftResult > rightResult ? leftResult : rightResult;
    }

    /**
     * Divides the array in 3 parts, the first values lower than pivot, second values equals to pivot
     * and the third one values greater than pivot
     * Complexity: O(n)
     */
    private static Pivot partition(long[] numbers, int start, int end) {

        int pivotIndex = start + (int)(Math.random() * ((end - start) + 1));
        long pivot = numbers[pivotIndex];

        int duplicates = 0;
        int key = start;

        swap(numbers, pivotIndex, end);

        for (int i = start; i < end - duplicates; i++) {
            if (numbers[i] < pivot) {
                swap(numbers, key++, i);
                continue;
            }

            if (numbers[i] == pivot) {
                swap(numbers, i, end - duplicates - 1);
                duplicates++;
                i--;
            }
        }

        swap(numbers, key, end);

        for (int i = 1; i <= duplicates; i++) {
            swap(numbers, key + i, end - i);
        }

        return new Pivot(key - 1, key + duplicates + 1, duplicates + 1);
    }

    /**
     * Swaps two elements in the array
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
 * QuickSort Pivot Information
 */
class Pivot {

    private int leftIndex;
    private int rightIndex;
    private int duplicates;

    Pivot(int leftIndex, int rightIndex, int duplicates) {
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.duplicates = duplicates;
    }

    public int getLeftIndex() {
        return leftIndex;
    }

    public int getRightIndex() {
        return rightIndex;
    }

    public int getDuplicates() {
        return duplicates;
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