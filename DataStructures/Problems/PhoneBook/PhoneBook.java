import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PhoneBook {

    private static final String ADD_COMMAND = "add";
    private static final String DEL_COMMAND = "del";
    private static final String FIND_COMMAND = "find";
    private static final String NOT_FOUND = "not found";

    private static final HashMap<String, String> hash = new HashMap<>();

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        int numberOfCommands = scanner.nextInt();

        for(int i = 0; i < numberOfCommands; i++) {
            executeCommand(scanner.nextLine());
        }
    }

    /**
     * Executes the command sent. The possible commands are: [ADD_COMMAND], [DEL_COMMAND] and [FIND_COMMANDS]
     * Complexity: O(1)
     */
    public static void executeCommand(String command) {
        String[] values = command.split("\\s+");

        switch (values[0]) {
            case ADD_COMMAND: {
                hash.put(values[1], values[2]);
                break;
            }
            case DEL_COMMAND: {
                hash.remove(values[1]);
                break;
            }
            default: {
                System.out.println(hash.getOrDefault(values[1], NOT_FOUND));
            }
        }
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

    String nextLine() {
        try {
            return reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    Integer nextInt() {
        return Integer.parseInt(next());
    }

    Long nextLong() {
        return Long.parseLong(next());
    }
}

