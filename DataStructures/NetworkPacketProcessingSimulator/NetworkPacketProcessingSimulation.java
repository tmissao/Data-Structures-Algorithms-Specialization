import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class NetworkPacketProcessingSimulation {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        Queue<Packet> queue = new LinkedList<>();
        List<Packet> packets = new LinkedList<>();

        int bufferSize = scanner.nextInt();
        int numberOfPackets = scanner.nextInt();

        for (int i = 0; i < numberOfPackets; i++) {
            Packet packet = new Packet(scanner.nextLong(), scanner.nextLong());
            queue.add(packet);
            packets.add(packet);
        }

        simulate(bufferSize, queue);

        for (Packet packet : packets) System.out.println(packet.getStartProcessing());
    }

    private static void simulate(int bufferSize, Queue<Packet> packets) {
        Queue<Packet> buffer = new LinkedList<>();
        long time = 0;

        while(!packets.isEmpty() || !buffer.isEmpty()) {

            while(!packets.isEmpty() && packets.peek().getArrivalTime() <= time) {
                Packet packet = packets.remove();

                if(packet.getProcessTime() == 0 && buffer.isEmpty()) {
                    packet.setStartProcessing(time);
                    continue;
                }

                if(buffer.size() >= bufferSize) {
                    packet.setStartProcessing(-1);
                    continue;
                }

                buffer.add(packet);
            }

            if (buffer.isEmpty() && packets.isEmpty()) {
                return;
            }

            if (buffer.isEmpty()) {
                time = packets.peek().getArrivalTime();
                continue;
            }

            Packet packet = buffer.remove();
            packet.setStartProcessing(time);
            time += packet.getProcessTime();
        }
    }



    public static void main(String[] args) {
        run();
    }
}

class Packet {

    private final long arrivalTime;
    private final long processTime;
    private long startProcessing;

    public Packet(long arrivalTime, long processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
        this.startProcessing = Long.MAX_VALUE;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getProcessTime() {
        return processTime;
    }

    public long getStartProcessing() {
        return startProcessing;
    }

    public void setStartProcessing(long startProcessing) {
        this.startProcessing = startProcessing;
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