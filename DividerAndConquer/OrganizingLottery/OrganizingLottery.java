import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OrganizingLottery {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int segments = scanner.nextInt();
        int points = scanner.nextInt();

        Struct[] structs = new Struct[2 * segments + points];

        for(int i = 0; i < segments; i++) {
            structs[2 * i] = new Struct(scanner.nextLong(), 'l');
            structs[2 * i + 1] = new Struct(scanner.nextLong(), 'r');
        }

        for(int i = 0; i < points; i++) {
            structs[2 * segments + i] = new Struct(scanner.nextLong(), 'p', i);
        }

        long[] result = calculateIntersections(structs, segments, points);

        for(int i = 0; i < result.length; i++) {
            System.out.print( i == 0 ? result[i] : " " + result[i] );
        }
    }

    private static long[] calculateIntersections(Struct[] structs, int segments, int points) {
        long[] result = new long[points];

        Arrays.sort(structs);

        long open = 0;
        long closed = 0;

        for(int i = 0; i < structs.length && closed < segments; i++ ) {
            Struct struct = structs[i];
            char type = struct.getType();

            if (type == 'l') {
                open++;
                continue;
            }

            if (type == 'p') {
                result[struct.getIndex()] = open;
                continue;
            }

            open--;
            closed++;
        }

        return result;
    }

    public static void main(String[] args) {
        run();
    }
}

class Struct implements Comparable<Struct>{

    private long coordinate;
    private char type;
    private int index;

    public Struct(long coordinate, char type, int index) {
        this.coordinate = coordinate;
        this.type = type;
        this.index = index;
    }

    public Struct(long coordinate, char type) {
        this.coordinate = coordinate;
        this.type = type;
        this.index = -1;
    }

    @Override
    public int compareTo(Struct next) {
        if (this.coordinate > next.coordinate ) return 1;
        if (this.coordinate < next.coordinate ) return -1;
        return this.type - next.type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Struct{");
        sb.append("coordinate=").append(coordinate);
        sb.append(", type=").append(type);
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }

    public long getCoordinate() {
        return coordinate;
    }

    public char getType() {
        return type;
    }

    public int getIndex() {
        return index;
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

