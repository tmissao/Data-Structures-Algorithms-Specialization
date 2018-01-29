import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fibonacci {

  /**
   *  Keeps the already calculated fibonacci number to increase algorithm performance
   */
  private static Long[] fibonacciSequence = new Long[46];

  private static void setup() {
    fibonacciSequence[0] = 0L;
    fibonacciSequence[1] = 1L;
    fibonacciSequence[2] = 1L;
  }

  private static void run() {
    FasterScanner scanner = new FasterScanner(System.in);
    int number = scanner.nextInt();
    System.out.println(fibonacci(number));
  }

  /**
   * Calculates n[th] fibonacci number
   * Complexity = 2n + 1
   */
  private static long fibonacci(int n) {
    if (fibonacciSequence[n] != null) { return fibonacciSequence[n]; }
    fibonacciSequence[n] = fibonacci(n-1) + fibonacci(n-2);
    return  fibonacciSequence[n];
  }

  public static void main(String[] args) {
    setup();
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
}


