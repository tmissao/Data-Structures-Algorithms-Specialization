import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MaximumNumberOfPrizes {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        long prizes = scanner.nextLong();


        ArrayList<Long> result = calculateMaximumNumberOfPrizes(prizes);

        System.out.println(result.size());

        for(int i = 0; i < result.size(); i++) {
            if (i == 0 ) { System.out.print(result.get(i)); }
            else { System.out.print(" " + result.get(i)); }
        }
    }

    /**
     * Calculates the quantity of prizes and their values given the number of prizes
     * Complexity: O( n^(1/2) )
     * Explanation: The nlog(n) comes from native java sort function [Collection.sort()]
     */
    private static ArrayList<Long> calculateMaximumNumberOfPrizes(long numberOfPrizes) {
        ArrayList<Long> prizes = new ArrayList<>();

        long sum = 0;

        for (long i = 1; numberOfPrizes - (sum + i ) >= i + 1; i++){
            prizes.add(i);
            sum += i;
        }

        prizes.add(numberOfPrizes - sum);

        return prizes;
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