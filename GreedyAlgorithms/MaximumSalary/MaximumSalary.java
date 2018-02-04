import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MaximumSalary {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int quantity = scanner.nextInt();
        CustomNumber[] numbers = new CustomNumber[quantity];

        for(int i = 0; i < quantity; i++) {
            numbers[i] = new CustomNumber(scanner.nextInt());
        }

        System.out.println(calculateMaximumNumberConcat(numbers));
    }

    /**
     * Calculates the quantity of prizes and their values given the number of prizes
     * Complexity: O( n^(1/2) )
     * Explanation: The nlog(n) comes from native java sort function [Collection.sort()]
     */
    private static String calculateMaximumNumberConcat(CustomNumber[] numbers) {

        StringBuffer sb = new StringBuffer("");

        Arrays.sort(numbers);

        for(CustomNumber n : numbers) {
            sb.append(n.getValue());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        run();
    }
}

class CustomNumber implements Comparable<CustomNumber> {

    private String value;

    CustomNumber(int value) {
        this.value = String.valueOf(value);
    }

    @Override
    public int compareTo(CustomNumber number) {
        return Integer.parseInt(number.value + this.value) > Integer.parseInt(this.value + number.value) ? 1 : -1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomDigit{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getValue() {
        return value;
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