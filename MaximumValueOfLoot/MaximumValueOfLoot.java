import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MaximumValueOfLoot {

  /**
   * Handles program inputs
   */
  private static void run() {
    FasterScanner scanner = new FasterScanner(System.in);
    int numberOfItems = scanner.nextInt();
    int capacity = scanner.nextInt();
    Loot[] loots = new Loot[numberOfItems];

    for (int i = 0; i < numberOfItems; i++) {
      loots[i] = new Loot(scanner.nextInt(), scanner.nextInt());
    }

    System.out.printf("%.4f", calculateMaximumLoot(capacity, loots));

  }

  /**
   * Calculates the maximum loot value given a weight and a list of possible loots
   * Complexity: O(nlog(n)) + O(n) => O( nlog(n) )
   * Explanation: The nlog(n) comes from native java sort function [Collection.sort()]
   */
  private static double calculateMaximumLoot(int maxWeight, Loot[] loots) {
    int weight = 0;
    double value = 0;

    Arrays.sort(loots);

    for (int i = 0; i < loots.length && weight < maxWeight; i++) {
      Loot loot = loots[i];
      int carry = Math.min((maxWeight - weight), loot.getWeight());
      weight += carry;
      value += carry * loot.getValuePerWeight();
    }

    return value;
  }

  public static void main(String[] args) {
    run();
  }
}

/**
 *  Information about the loot item
 */
class Loot implements Comparable<Loot> {

  private final int value;
  private final int weight;
  private double valuePerWeight;

  Loot(int value, int weight) {
    this.value = value;
    this.weight = weight;
    this.valuePerWeight = ((double) value) / ((double) weight);
  }

  public int getValue() {
    return value;
  }

  public int getWeight() {
    return weight;
  }

  public double getValuePerWeight() {
    return valuePerWeight;
  }

  @Override
  public int compareTo(Loot loot) {
    return loot.valuePerWeight > this.valuePerWeight ? 1 : -1;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Loot{");
    sb.append("value=").append(value);
    sb.append(", weight=").append(weight);
    sb.append(", valuePerWeight=").append(valuePerWeight);
    sb.append('}');
    return sb.toString();
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