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

    /**
     * Simulates package networking process with a buffer
     * Complexity: O(n)
     */
    private static void simulate(int bufferSize, Queue<Packet> packets) {
        LinkedList<Packet> buffer = new LinkedList<>();
        long time = 0;

        while(!packets.isEmpty() || !buffer.isEmpty()) {

            // Remove all already processed packets
            while(!buffer.isEmpty() && buffer.getFirst().getFinalTime() <= time) {
                Packet packet = buffer.removeFirst();
                packet.setStartProcessing(packet.getFinalTime() - packet.getProcessTime());
            }

            while(!packets.isEmpty() && packets.peek().getArrivalTime() <= time) {
                Packet packet = packets.remove();

                // Checks if Buffer is full
                if (buffer.size() >= bufferSize ) {
                    packet.setStartProcessing(-1);
                    continue;
                }

                // If buffer is empty and the packet process time is zero, it does not need enter in the buffer
                // Because it goes directly to process
                if (buffer.isEmpty() && packet.getProcessTime() == 0) {
                    packet.setStartProcessing(time);
                    continue;
                }

                // If buffer is empty the packet will be processed at time + packet process time
                // Otherwise will be time of the last packet will be processed + packet process time
                if (buffer.isEmpty()) {
                    packet.setFinalTime(time + packet.getProcessTime());
                } else {
                    packet.setFinalTime(buffer.getLast().getFinalTime() + packet.getProcessTime());
                }

                buffer.add(packet);
            }

            // The next time to be check will be the lower time between a new packet arrive and a packet get processed
            if (!buffer.isEmpty() && !packets.isEmpty()) {
                time = buffer.getFirst().getFinalTime() < packets.peek().getArrivalTime()
                    ? buffer.getFirst().getFinalTime() : packets.peek().getArrivalTime();
            } else if (!buffer.isEmpty()) {
                time = buffer.getFirst().getFinalTime();
            } else if (!packets.isEmpty()) {
                time = packets.peek().getArrivalTime();
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}

class Packet {

    private final long arrivalTime;
    private final long processTime;
    private long finalTime;
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

    public long getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(long finalTime) {
        this.finalTime = finalTime;
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