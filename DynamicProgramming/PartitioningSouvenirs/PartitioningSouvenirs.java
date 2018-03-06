import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PartitioningSouvenirs {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int numberOfSouvenirs = scanner.nextInt();

        int[] souvenirs = new int[numberOfSouvenirs];

        for(int i = 0; i < souvenirs.length; i++) {
            souvenirs[i] = scanner.nextInt();
        }

        System.out.println(checkEvenlyPartition(souvenirs, 3) ? 1 : 0);
    }

    /**
     * Checks if the sum of souvenirs values can be divided equally between people
     * Complexity: O(Sum(souvenirs) + souvenirs)
     */
    private static boolean checkEvenlyPartition(int[] souvenirs, int people) {
        int souvenirValues = 0;
        for(int value : souvenirs) souvenirValues += value;

        if (souvenirValues % people != 0) {
            return false;
        }

        boolean[][] DP = new boolean[souvenirValues/people + 1][souvenirs.length + 1];

        for(int i = 0; i < DP[0].length; i++) {
            DP[0][i] = true;
        }

        for (int i = 1; i <= souvenirValues/people; i++) {
            for (int j = 1; j <= souvenirs.length; j++)  {
                DP[i][j] = DP[i][j-1];

                if (i >= souvenirs[j-1]) {
                    DP[i][j] = DP[i][j] || DP[i - souvenirs[j-1]][j-1];
                }
            }
        }

        return DP[souvenirValues/people][DP[0].length - 1];
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
