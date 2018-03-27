import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.*;

public class FindPatternInText {

    /**
     * Biggest integer prime
     */
    private final static long PRIME = 1000000007;

    /**
     * Magical integer used to generated the hash value
     */
    private final static long SEED_A = 263;

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();
        findPattern(pattern, text);
    }

    /**
     * Searches by a pattern inside of a given text
     * O(t) where t is the text`s length
     */
    static void findPattern(String pattern, String text) {
        long pHash = 0;
        long tHash = 0;
        long h = 1;
        StringBuffer sb = new StringBuffer();
        boolean first = true;

        for (int i = 0; i < pattern.length() -1; i++) {
            h = (h * SEED_A) % PRIME;
        }

        for (int i = 0; i < pattern.length(); i++) {
            pHash = (SEED_A * pHash + pattern.charAt(i))% PRIME;
            tHash = (SEED_A * tHash + text.charAt(i))% PRIME;
        }

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {

            if ( pHash == tHash ) {
                boolean isEqual = true;
                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i+j) != pattern.charAt(j)) {
                        isEqual = false;
                        break;
                    }
                }

                if (isEqual) {
                    if (first) { sb.append(i); first = false;}
                    else { sb.append(" ").append(i);  }
                }
            }

            if ( i < text.length() - pattern.length() ) {
                long partA = (((SEED_A * (tHash - text.charAt(i) * h )) % PRIME) + PRIME) % PRIME;
                tHash = (partA + text.charAt(i+pattern.length())) % PRIME;
            }
        }

        System.out.println(sb.toString());
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
