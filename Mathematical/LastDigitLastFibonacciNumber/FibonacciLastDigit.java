import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FibonacciLastDigit {

    /**
     *  Keeps the last digit of a fibonacci number to increase algorithm performance
     */
    private static Long[] fibonacciLastDigitSequence = new Long[10000001];
    private static int higherFibonacciCalculated = -1;

    private static void setup() {
        fibonacciLastDigitSequence[0] = 0L;
        fibonacciLastDigitSequence[1] = 1L;
        fibonacciLastDigitSequence[2] = 1L;
        higherFibonacciCalculated = 2;
    }

    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int number = scanner.nextInt();
        System.out.println(fibonacci(number));
    }

    /**
     * Calculates the last digit of the n[th] fibonacci number
     * Complexity = 2n => O(n)
     */
    private static long fibonacci(int n) {
        if (fibonacciLastDigitSequence[n] != null) { return fibonacciLastDigitSequence[n]; }

        // The last digit of a fibonacci number is the sum of the two previous fibonacci number last digits
        for(int i = higherFibonacciCalculated + 1; i<=n; i++) {
            fibonacciLastDigitSequence[i] = (fibonacciLastDigitSequence[i-1] + fibonacciLastDigitSequence[i-2])%10;
        }

        higherFibonacciCalculated = n;
        return fibonacciLastDigitSequence[n];
    }

    public static void main(String[] args) {
        setup();
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
}


