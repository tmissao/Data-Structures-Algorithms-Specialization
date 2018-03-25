import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class RabinKarp {

    /**
     * Biggest integer prime
     */
    private final static long PRIME = 1000000007;

    /**
     * Magical integer used to generated the hash value
     */
    private final static int SEED_A = 263;

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        String text = scanner.nextLine();
        String pattern = scanner.nextLine();

        for(int i : findPattern(text, pattern)) {
            System.out.println(i);
        }

    }

    /**
     * Searches by a pattern inside of a given text
     * Complexity: O(2 * t) => O(t) where t is the text`s length
     */
    private static List<Integer> findPattern(String text, String pattern) {
        List<Integer> results = new LinkedList<>();
        long pHash = polynomialHash(pattern, SEED_A);
        long[] hashes = preComputedHashes(text, pattern.length());

        for(int i = 0; i < text.length() - pattern.length() + 1; i++) {
            String subString = text.substring(i, i + pattern.length());
            if (pHash == hashes[i] && pattern.equals(subString)) {
                results.add(i);
            }
        }

        return results;
    }

    /**
     * Computes a polynomial hash of a substring
     * Complexity: O(t) where t is the length of string
     */
    private static long[] preComputedHashes(String text, int patternLength) {
        long[] hashes = new long[text.length() - patternLength + 1];
        String lastSubstring = text.substring(text.length() - patternLength, text.length());

        // Calculates the hash of the last substring
        hashes[hashes.length - 1] = polynomialHash(lastSubstring, SEED_A);

        long y = 1;

        for(int i = 0; i < patternLength; i++) {
            y = (y * SEED_A) % PRIME;
        }

        // Given the next Substring hash, calculates the current substring hash
        for(int i = hashes.length - 2; i >= 0; i--) {
            hashes[i] = ((SEED_A * hashes[i + 1]) + text.charAt(i) - (y * text.charAt(i + patternLength)) ) % PRIME;
        }

        return hashes;
    }

    /**
     * Generates a polynomial hash
     */
    private static long polynomialHash(String element, int a) {
        long value = 0;
        for(int i = element.length() - 1; i >= 0; i--) {
            value = (value * a + element.charAt(i)) % PRIME;
        }

        return value;
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

    String nextLine() {
        try {
            return reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    Integer nextInt() {
        return Integer.parseInt(next());
    }

    Long nextLong() {
        return Long.parseLong(next());
    }
}
