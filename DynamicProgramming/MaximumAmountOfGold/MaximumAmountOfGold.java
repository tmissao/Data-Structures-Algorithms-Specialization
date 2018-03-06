import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumAmountOfGold {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int maxWeight = scanner.nextInt();
        int maxPieces = scanner.nextInt();

        int[] pieces = new int[maxPieces];
        int[][] DP = new int[maxWeight+1][maxPieces+1];

        for(int i = 0; i < pieces.length; i++) {
            pieces[i] = scanner.nextInt();
        }

        System.out.println(calculateMaxAmount(pieces, DP));
    }

    /**
     * Calculates the maximum weight enable to carry
     * Complexity: O( w * p ) where w is the Maximum Value o Weight and p the number os items
     */
    private static long calculateMaxAmount(int[] pieces, int[][] DP) {
        for(int i = 0; i < DP.length; i++) DP[i][0] = 0;
        for(int i = 0; i < DP[0].length; i++) DP[0][i] = 0;

        for(int w = 1; w < DP.length; w++) {
            for (int i = 1; i < DP[0].length; i++) {
                DP[w][i] = DP[w][i-1];
                int piece = pieces[i-1];

                if (piece <= w) {
                    DP[w][i] = Math.max(DP[w][i], DP[w-piece][i-1] + piece);
                }
            }
        }

        return DP[DP.length -1][DP[0].length -1];
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
