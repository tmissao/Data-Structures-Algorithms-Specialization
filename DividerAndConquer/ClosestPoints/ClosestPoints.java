import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ClosestPoints {

    private static final SortPointsByX sortX = new SortPointsByX();
    private static final SortPointsByY sortY = new SortPointsByY();

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int numberOfPoints = scanner.nextInt();

        Point[] points = new Point[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            points[i] = new Point( scanner.nextLong(), scanner.nextLong() );
        }

        System.out.printf("%.4f", closestDistance(points));

    }

    /**
     * Calculates the minimum distance between two point int the array
     * Complexity: Since the recurrence equation is 2T(n/2) + nlog(n) + 6n
     * using the master theorem, the complexity is defined as O(nlog(n))
     */
    private static double closestDistance(Point[] pointsX) {

        // Sorts the point by their X coordinate
        Arrays.sort(pointsX, sortX);

        for (int i = 0; i < pointsX.length - 1; i++) {
            // If there is two equals point than the closest distance is 0.0
            if (pointsX[i].equals(pointsX[i + 1])) {
                return 0.0;
            }
        }

        return findClosestDistance(pointsX, 0, pointsX.length - 1);
    }

    /**
     * Calculates the distance between the points
     */
    private static double findClosestDistance(Point[] px, int start, int end) {
        // There is just one point or there is no point
        if (start >= end) {
            return Double.POSITIVE_INFINITY;
        }

        // If there are three or less point, it is possible calculate the distance between them
        if (end - start + 1 <= 3) {
            return calculateDistance(px, start, end);
        }

        int middle = (start + end) / 2;
        long dividerX = px[middle].getX();

        // Divides the problem in two parts
        double leftDistance = findClosestDistance(px, start, middle);
        double rightDistance = findClosestDistance(px, middle + 1, end);

        // Gets the lower distance between the two parts
        double distance = Math.min(leftDistance, rightDistance);


        // Now it is necessary to calculate the distance between the closest points between the
        // two sets, just if the points is less the current distance from the partition point
        ArrayList<Point> strip = new ArrayList();

        for(int i = start; i <= end; i++ ) {
            if (Math.abs(px[i].getX() - dividerX) < distance) {
                strip.add(px[i]);
            }
        }

        // Return the minimum distance
        return Math.min(distance, stripClosest(strip, distance));
    }

    /**
     * Calculates the distance between points, this algorithm is O(nˆ2), however if n is known and
     * it is small, is small like 2 or 3 is possible to affirm that it is O(1)
     */
    private static double calculateDistance(Point[] p, int start, int end) {
        double minDistance = Double.POSITIVE_INFINITY;

        for(int i = start; i < end; i++) {
            for(int j = i + 1; j <= end; j++) {
                double distance = p[i].getDistance(p[j]);
                minDistance = minDistance > distance ? distance : minDistance;
            }
        }

        return minDistance;
    }

    /**
     * Calculates the distance between points, in this case, points between the left partition and the right partition
     * that there a no more than [distance] from the partition point.
     *
     * For one point in question, is just necessary to calculate the distance between the points that there are in a distance
     * of (distance, 2 * distance), in other words, if the points are sorted by there Y coordinate, it is just required
     * to calculate the distance for the next seven subsequent points. Thus the complexity of this algorithm is O(n) since
     * the inner loop just run seven times.
     */
    private static double stripClosest(List<Point> strip, double distance) {
        double minDistance = distance;
        Collections.sort(strip, sortY);

        for(int i = 0; i < strip.size() - 1; i++) {
            for (int j = i + 1; j < strip.size() && Math.abs(i - j) <= 7; j++ ) {
                double aux = strip.get(j).getDistance(strip.get(i));
                minDistance = minDistance > aux ? aux : minDistance;
            }
        }

        return minDistance;
    }


    public static void main(String[] args) {
        run();
    }
}

/**
 * Comparetor to sort by coordinate Y
 */
class SortPointsByY implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
        return p1.compareY(p2);
    }
}

/**
 * Comparetor to sort by coordinate X
 */
class SortPointsByX implements Comparator<Point> {

    @Override
    public int compare(Point p1, Point p2) {
        return p1.compareX(p2);
    }
}

/**
 *  Class representing a point
 */
class Point {

    private final long x;
    private final long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Point next) {
        return Math.sqrt(Math.pow(this.x - next.getX(), 2)  + Math.pow(this.y - next.getY(), 2));
    }

    public int compareX(Point next) {
        return this.x > next.getX() ? 1 : -1;
    }

    public int compareY(Point next) {
        return this.y > next.getY() ? 1 : -1;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (x != point.getX()) return false;
        return y == point.getY();
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Point{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
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

