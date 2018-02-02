import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MoneyChange {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int amount = scanner.nextInt();

        System.out.println(calculateMinimumCoinsChange(amount));
    }

    /**
     * Calculates the minimum coins number necessary to change an amount
     */
    private static long calculateMinimumCoinsChange(int amount) {
        int[] coins = { 10, 5, 1 };
        int numCoins = 0;
        int value = amount;

        for(int i = 0; i < coins.length && value > 0; i++) {
            int nCoin = value/coins[i];
            value -= nCoin * coins[i];
            numCoins += nCoin;
        }

        return numCoins;
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