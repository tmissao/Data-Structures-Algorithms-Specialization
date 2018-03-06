import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongestCommonSubsequenceOfThreeSequences {

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

        length = scanner.nextInt();
        long[] S3 = new long[length];

        for(int i = 0; i < S3.length; i++) {
            S3[i] = scanner.nextLong();
        }

        int[][][] DP = new int[S3.length + 1][S2.length + 1 ][S1.length + 1];

        longestCommonSubsequence(S1, S2, S3, DP);


        System.out.println(DP[DP.length -1][DP[0].length - 1][DP[0][0].length - 1]);
    }

    /**
     *  Calculates the length of the longest common subsequence of three sequences
     *  Complexity: O( n * m * z), where n is the length of the first sequence, m the length
     *  of the second one, and z the length of the third sequence.
     */
    private static void longestCommonSubsequence(long[] S1, long[] S2, long[] S3, int[][][] DP) {
        for(int k = 1; k <= S3.length; k++) {
            for (int i = 1; i <= S2.length; i++) {
                for (int j = 1; j <= S1.length; j++) {

                    int max = Integer.MIN_VALUE;

                    // If the S1[n] is equals to S2[m] and they are equals to S3[z] it means that a value in common was found.
                    // So, the length of the common subsequence is the previous value DP[z-1][n-1][m-1] + 1.
                    // Otherwise the max value of the previous subsequence
                    // DP[k][i][j], DP[k][i][j-1], DP[k][i-1][j]
                    // DP[k-1][i][j], DP[k-1][i][j-1], DP[k-1][i-1][j]
                    // is selected
                    if (S2[i - 1] == S1[j - 1] && S3[k - 1] == S2[i - 1]) {
                        max = DP[k-1][i-1][j-1] + 1;
                    } else {
                        max = Math.max(max, DP[k-1][i][j]);
                        max = Math.max(max, DP[k-1][i][j-1]);
                        max = Math.max(max, DP[k-1][i-1][j]);
                        max = Math.max(max, DP[k][i][j]);
                        max = Math.max(max, DP[k][i][j-1]);
                        max = Math.max(max, DP[k][i-1][j]);
                    }

                    DP[k][i][j] = max;
                }
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
