import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class CollectingSignatures {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int numberOfSegments = scanner.nextInt();
        Segment[] segments = new Segment[numberOfSegments];

        for(int i = 0; i < segments.length; i++) {
            segments[i] = new Segment(scanner.nextLong(), scanner.nextLong());
        }

        ArrayList<Long> result = calculateMinimumSegmentsGroup(segments);

        System.out.println(result.size());

        for(int i = 0; i < result.size(); i++) {
            if (i == 0 ) { System.out.print(result.get(i)); }
            else { System.out.print(" " + result.get(i)); }
        }
    }

    /**
     * Calculates the minimum Segments Groups and their common point
     * Complexity: O(nlog(n)) + O(n) => O( nlog(n) )
     * Explanation: The nlog(n) comes from native java sort function [Collection.sort()]
     */
    private static ArrayList<Long> calculateMinimumSegmentsGroup(Segment[] segments) {
        ArrayList<Long> commonPoints = new ArrayList<>();
        Arrays.sort(segments);

        for(int i = 0; i < segments.length; i++) {
            Segment segment = segments[i];
            long minPoint = segment.getStart();
            long maxPoint = segment.getEnd();
            long commonPoint = segment.getEnd();

            for(int k = i + 1; k < segments.length; k++) {
                Segment next = segments[k];
                if (minPoint > next.getStart() || next.getStart() > maxPoint) {
                    break;
                }
                commonPoint = Math.min(commonPoint, next.getEnd());
                maxPoint = Math.min(maxPoint, next.getEnd());
                i = k;
            }
            commonPoints.add(commonPoint);
        }

        return commonPoints;
    }

    public static void main(String[] args) {
        run();
    }
}

class Segment implements Comparable<Segment> {

    private final long start;
    private final long end;

    Segment(long p1, long p2) {
        this.start = p1;
        this.end = p2;
    }

    @Override
    public int compareTo(Segment segment) {
        return segment.start > this.start ? -1 : 1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Segment{");
        sb.append("start=").append(start);
        sb.append(", end=").append(end);
        sb.append('}');
        return sb.toString();
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
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