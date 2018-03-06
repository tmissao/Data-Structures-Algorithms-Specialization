import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class CheckBracketsInCode {

    /**
     * Handles program inputs
     */
    private static void run() {
        FasterScanner scanner = new FasterScanner(System.in);
        String input = scanner.next();
        int result = checkBrackets(input);
        System.out.println( result == -1 ? "Success" : result);
    }

    /**
     * Checks if characters '(', '{', '[' have their correspondent closing character ')', '}', ']'
     * Complexity: O(n) where n is the number os characters in the input
     */
    private static int checkBrackets(String input) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> stackIndex = new Stack<>();

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stack.push(ch);
                    stackIndex.push(i + 1);
                    break;
                case '}':
                    if (stack.isEmpty() || stack.peek() != '{') {
                        return i + 1;
                    } else {
                        stack.pop();
                        stackIndex.pop();
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        return i + 1;
                    } else {
                        stack.pop();
                        stackIndex.pop();
                    }
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        return i + 1;
                    } else {
                        stack.pop();
                        stackIndex.pop();
                    }
                    break;
            }
        }

        return stackIndex.isEmpty() ? -1 : stackIndex.pop();
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
