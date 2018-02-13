import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EditingDistance {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        String S1 = scanner.next();
        String S2 = scanner.next();

        int[][] DP = new int[S2.length() + 1][S1.length() + 1];

        System.out.println(calculateEditDistance(S1, S2, DP));
    }

    /**
     * Calculates the edit distance between two strings (minimum number of operations to transform one string
     * into the other). This algorithm uses a dynamic programming strategy.
     * Complexity: O(m*n) where m is the length of the first string, and n the length of the second one
     */
    private static int calculateEditDistance(String S1, String S2, int[][] DP) {
        for (int i = 0; i < DP[0].length; i++) DP[0][i] = i; // initializes the first line with char position
        for (int i = 0; i < DP.length; i++) DP[i][0] = i; // initializes the column line with char position

        for (int i = 1; i < DP.length; i++) {
            for (int j = 1; j < DP[i].length; j++) {

                // Checks if the char i and j of the two string are the same, if it is the editing distance in this step
                // is zero (match), otherwise is the editing distance is the editing distance o [i-1][j-1] + 1 (mismatch)
                int diagonal = S2.charAt(i - 1) == S1.charAt(j - 1) ? DP[i - 1][j - 1] : DP[i - 1][j - 1] + 1;

                // Gets the lower editing distance between [i][j-1] (deletion) and [i-1][j] (insertion) plus 1
                int indel = DP[i - 1][j] < DP[i][j - 1] ? DP[i - 1][j] + 1 : DP[i][j - 1] + 1;

                // Gets the lower editing distance between insertion, deletion, match and mismatch operations
                DP[i][j] = Math.min(diagonal, indel);
            }
        }

        return DP[DP.length - 1][DP[0].length - 1];
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