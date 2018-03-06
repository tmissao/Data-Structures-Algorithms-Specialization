import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumValueOfAnArithmeticExpression {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        String expression = scanner.next();

        int quantityOfNumbers = (expression.length()-1)/2 + 1;

        long[] numbers = new long[quantityOfNumbers];
        long[][] DPMax = new long[quantityOfNumbers][quantityOfNumbers];
        long[][] DPMin = new long[quantityOfNumbers][quantityOfNumbers];
        String[] operations = new String[quantityOfNumbers - 1];


        for(int i = 0, n = 0, k = 0; i < expression.length(); i++) {
            if (i%2 == 0) {
                numbers[n++] = Integer.parseInt(expression.charAt(i) + "");
            } else {
                operations[k++] = expression.charAt(i) + "";
            }
        }

        System.out.println(calculateMaxValue(numbers, operations, DPMax, DPMin));
    }

    /**
     * Calculate the max value that can be obtained placing parentheses in a expression
     */
    private static long calculateMaxValue(long[] numbers, String[] operations, long[][] DPMax, long[][] DPMin) {
        for(int i = 0; i < DPMax.length; i++) {
            DPMax[i][i] = numbers[i];
            DPMin[i][i] = numbers[i];
        }

        for(int s = 0; s < DPMax.length - 1; s++) {
            for( int i = 0; i < DPMax[0].length - s - 1; i++) {
                int j = i + s + 1;
                long[] minMax = calculateMinMax(operations, DPMax, DPMin, i, j);
                DPMin[i][j] = minMax[0];
                DPMax[i][j] = minMax[1];
            }
        }

        return DPMax[0][DPMax[0].length - 1];
    }

    /**
     * Calculate the max and min value that can be obtained executing a operation
     */
    private static long[] calculateMinMax(String[] operations, long[][] DPMax, long[][] DPMin, int i , int j) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for(int k = i; k < j; k++) {
            long [] variations = new long[4];
            variations[0] = executeOperation(operations[k], DPMax[i][k], DPMax[k+1][j]);
            variations[1] = executeOperation(operations[k], DPMax[i][k], DPMin[k+1][j]);
            variations[2] = executeOperation(operations[k], DPMin[i][k], DPMax[k+1][j]);
            variations[3] = executeOperation(operations[k], DPMin[i][k], DPMin[k+1][j]);

            for(long value : variations) {
                min = Math.min(min, value);
                max = Math.max(max, value);
            }

        }

        return new long[] {min, max};
    }

    /**
     * Executes the operation
     */
    private static long executeOperation(String operation, long a, long b) {
        if (operation.equals("+")) return a + b;
        if (operation.equals("-")) return a - b;
        if (operation.equals("*")) return a * b;
        return a / b;
    }

    private static void printMatrix(long[][] matrix) {
        for(long[] row : matrix) {
            for(long value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        System.out.println();
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
