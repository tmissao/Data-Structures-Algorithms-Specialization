import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongestCommonSubsequenceOfTwoSequences {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int length = scanner.nextInt();
        long[] S1 = new long[length];

        for(int i = 0; i < S1.length; i++) {
            S1[i] = scanner.nextLong();
        }

        length = scanner.nextInt();
        long[] S2 = new long[length];

        for(int i = 0; i < S2.length; i++) {
            S2[i] = scanner.nextLong();
        }

        long[][] DP = new long[S2.length + 1 ][S1.length + 1];

        longestCommonSubsequence(S1, S2, DP);

        System.out.println(DP[DP.length - 1][DP[0].length - 1]);

    }

    /**
     *  Calculates the length of the longest common subsequence of two sequences
     *  Complexity: O( n * m ), where n is the length of the first sequence and m the length
     *  of the second one.
     */
    private static void longestCommonSubsequence(long[] S1, long[] S2, long[][] DP) {
        for(int i = 1; i <= S2.length; i++) {
            for( int j = 1; j <= S1.length; j++) {

                long max;

                // If the S1[n] is equals to S2[m] it means that a value in common was found. So
                // the length of the common subsequence is the previous value DP[n-1][m-1] + 1.
                // Otherwise the max value of the previous subsequence DP[n][m-1] and DP[n-1][m] is selected
                if (S2[i-1] == S1[j-1]) {
                    max = DP[i-1][j-1] + 1;
                } else {
                    max = Math.max(DP[i-1][j], DP[i][j-1]);
                }

                DP[i][j] = max;
            }
        }
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
