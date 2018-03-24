import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SieveOfEratosthenes {

    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int number = scanner.nextInt();
        for(int p : getPrimes(number)) {
            System.out.print( p +  " ");
        }
    }

    /**
     * Calculates all primes up to n
     * Complexity: O(n)
     */
    private static List<Integer> getPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] numbers = new boolean[n+1];

        int limit = (int) Math.sqrt(numbers.length) + 1;

        for(int i = 2; i < limit; i++) {
            if (!numbers[i]) {
                for(int k = i*i; k < numbers.length; k+=i ) {
                    numbers[k] = true;
                }
            }
        }

        for(int i = 1; i < numbers.length; i++) {
            if (!numbers[i]) {
                primes.add(i);
            }
        }

        return primes;
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