import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MoneyChangeAgain {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int money = scanner.nextInt();

        int[] coins = new int[] {1, 3, 4};
        int[] DP = new int[money + 1];

        System.out.println(calculateChange(money, coins, DP));
    }

    /**
     * Calculates the minimum number of coins necessary to change some money
     * Complexity: O(n + k) where n is the value and k the number of coins
     */
    private static int calculateChange(int value, int[] coins, int[] DP) {
        if(value == 0) {
            DP[value] = 0;
            return 0;
        }

        if (DP[value] != 0) {
            return DP[value];
        }

        int minChange = Integer.MAX_VALUE;
        for(int coin : coins) {
            if (coin <= value) {
               int change = calculateChange(value - coin, coins, DP) + 1;
               minChange = minChange > change ? change : minChange;
            }
        }

        DP[value] = minChange;
        return minChange;
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
