import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class MaximumAdvertisementRevenue {

  /**
   * Handles program inputs
   */
  private static void run() {
    FasterScanner scanner = new FasterScanner(System.in);
    int numberOfAds = scanner.nextInt();

    Long[] profit = new Long[numberOfAds];
    Long[] avgClicks = new Long[numberOfAds];

    for (int i = 0; i < profit.length; i++) {
      profit[i] = scanner.nextLong();
    }

    for (int i = 0; i < avgClicks.length; i++) {
      avgClicks[i] = scanner.nextLong();
    }

    System.out.println(calculateMaximumProfit(profit, avgClicks));

  }

  /**
   * Calculates the maximum revenue given the value paid by each ad and the average number of clicks of a spot
   * Complexity: O(2*nlog(n)) => O( nlog(n) )
   * Explanation: The nlog(n) comes from native java sort function [Collection.sort()]
   */
  private static long calculateMaximumProfit(Long[] profit, Long[] avgClicks) {
    long value = 0;

    Arrays.sort(profit, Collections.reverseOrder());
    Arrays.sort(avgClicks, Collections.reverseOrder());

    for (int i = 0; i < profit.length; i++) {
      value += profit[i] * avgClicks[i];
    }

    return value;
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