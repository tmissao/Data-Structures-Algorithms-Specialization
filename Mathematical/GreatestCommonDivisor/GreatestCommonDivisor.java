import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreatestCommonDivisor {

    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int number1 = scanner.nextInt();
        int number2 = scanner.nextInt();
        System.out.println(maxCommonDivisor(number1, number2));
    }

    /**
     * Calculates the greatest common divisor between two numbers, using euclidean algorithm
     * Complexity = Since in each step the input is divided almost by two the complexity of this algorithm
     * is log(a) + log(b) => log(ab) where a and b are the inputs
     */
    private static int maxCommonDivisor(int a, int b) {
        if (b == 0) { return a; }
        return maxCommonDivisor(b, a%b);
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
}


