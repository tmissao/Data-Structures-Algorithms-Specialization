import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LastDigitOfTheSumOfFibonacciNumberAgain {

  /**
   * Handles program inputs
   */
  private static void run() {
    FasterScanner scanner = new FasterScanner(System.in);
    long start = scanner.nextLong();
    long end = scanner.nextLong();

    System.out.println(sequenceSumFibonnacciMods(start, end));
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
   * Calculates the sum of mod between the period start <= nth <= end Fibonacci number
   */
  private static long sequenceSumFibonnacciMods(long start, long end) {
    ArrayList<Integer> pizanoSequence = getPizanoSequence(end, 10);
    long sumBegin = sumFibonacciMods(start - 1, pizanoSequence);
    long sumEnd = sumFibonacciMods(end, pizanoSequence);

    return (sumEnd - sumBegin) % 10;
  }

  /**
   * Calculates the sum of mod between the first nth Fibonacci number
   */
  private static long sumFibonacciMods(long nthFibonacci, ArrayList<Integer> pizanoSequence) {

    long loopsInSequence = nthFibonacci / pizanoSequence.size();
    long modIndex = nthFibonacci % pizanoSequence.size();
    long sequenceSum = 0;
    long modSum = 0;

    for (int i = 0; i < pizanoSequence.size(); i++) {
      sequenceSum += pizanoSequence.get(i);
      if (i <= modIndex) {
        modSum += pizanoSequence.get(i);
      }
    }

    return ((sequenceSum * loopsInSequence) + modSum);
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


