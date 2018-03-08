import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ParallelProcessing {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);

        int threads = scanner.nextInt();
        int processes = scanner.nextInt();
        Queue<Task> queue = new LinkedList<>();

        for(int i = 0; i < processes; i++) {
            queue.add(new Task(scanner.nextLong()));
        }

        System.out.print(simulateProcessing(threads, queue));
    }

    /**
     * Simulates Parallel processing with t threads
     *
     * Complexity: O(2nlog(t)) => O(nlog(t)) where t is the number of threads and n the number of tasks
     * Explanation: Since this algorithm utilizes a Heap its insertions and extraction operation are logarithmic time.
     * This logarithmic time is based on the tree height, and the tree height is based on the number of threads.
     * The tree height is log(t), and its operations are called n times. Therefore the algoritm running time
     * is O(nlog(t))
     */
    private static String simulateProcessing(int threads, Queue<Task> processes) {
        PriorityQueue<Task> tasks = new PriorityQueue<>(100000);
        StringBuilder logs = new StringBuilder();
        long time = 0;

        // Every Thread take one task
        for(int i = 0; i < threads && processes.size() > 0; i++) {
            Task task = processes.poll();
            task.setThreadId(i);
            task.setFinishTime(task.getProcessTime());
            logs.append(i).append(" ").append(time).append("\n");

            tasks.offer(task);
        }

        // While still has tasks being processed
        while(!tasks.isEmpty()) {
            Task finished = tasks.poll();

            // The thread that is free takes the next process
            if (!processes.isEmpty()) {
                Task next = processes.poll();
                next.setFinishTime(finished.getFinishTime() + next.getProcessTime());
                next.setThreadId(finished.getThreadId());
                logs.append(finished.getThreadId()).append(" ").append(finished.getFinishTime()).append("\n");

                tasks.offer(next);
            }
        }

        return logs.toString();
    }

    public static void main(String[] args) {
        run();
    }
}

class Task implements Comparable<Task> {

    private final long processTime;
    private long finishTime;
    private long threadId;

    public Task(long processTime) {
        this.processTime = processTime;
        this.threadId = -1;
    }

    public Task(long processTime, long threadId) {
        this.processTime = processTime;
        this.threadId = threadId;
    }

    public long getProcessTime() {
        return processTime;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public int compareTo(Task t) {
        if (this.getFinishTime() > t.getFinishTime()) return 1;
        if (this.getFinishTime() < t.getFinishTime()) return -1;
        if (this.getThreadId() > t.getThreadId()) return 1;
        return -1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Task{");
        sb.append("processTime=").append(processTime);
        sb.append(", threadId=").append(threadId);
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
