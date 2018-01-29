import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumPairwiseProduct {

  public static void main(String[] args) {
    run();
  }

  /**
   * Calculates the maximum product of two distinct numbers
   */
  private static void run() {
    Reader reader = new Reader();
    long numbers = reader.nextInt();

    long maxNumber = Integer.MIN_VALUE;
    long maxNumber2 = Integer.MIN_VALUE;

    for (int i = 0; i < numbers; i++) {
      int number = reader.nextInt();

      if (number > maxNumber) {
        maxNumber2 = maxNumber;
        maxNumber = number;
        continue;
      }

      if (number > maxNumber2) {
        maxNumber2 = number;
      }
    }

    System.out.println(maxNumber*maxNumber2);
  }

  /**
   * Faster Scanner
   */
  static class Reader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String next() {
      try {
        while (st == null || !st.hasMoreTokens()) {
          st = new StringTokenizer(br.readLine());
        }

      } catch (Exception e) {
        e.printStackTrace();
      }

      return st.nextToken();
    }

    Integer nextInt() {
      return Integer.parseInt(next());
    }
  }
}
