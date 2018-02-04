import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FibonacciNumberAgain {

  /**
   * Handles program inputs
   */
  private static void run() {
    FasterScanner scanner = new FasterScanner(System.in);
    long nth = scanner.nextLong();
    int mod = scanner.nextInt();

    System.out.println(findFibonacciMod(nth, mod));
  }

  /**
   * Finds Pizano Periodical Sequence
   */
  private static ArrayList<Integer> getPizanoSequence(long max, int mod) {
    ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(0, 1));

    for (int i = 2; i <= max; i++) {
      int value = (sequence.get(i - 1) + sequence.get(i - 2)) % mod;

      // Every periodic sequence from Pisano initiates with '01'
      // Thus, if the current value is 1 and the previous is 0, a new sequence started
      if (value == 1 && sequence.get(i - 1) == 0) {
        sequence.remove(sequence.size() - 1);
        break;
      }

      sequence.add(value);
    }

    return sequence;
  }

  /**
   * Calculates the mod of the nth Fibonacci number
   */
  private static long findFibonacciMod(long nthFibonacci, int mod) {

    ArrayList<Integer> pizanoSequence = getPizanoSequence(nthFibonacci, mod);

    long modIndex = nthFibonacci % pizanoSequence.size();
    return pizanoSequence.get((int) modIndex);
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


