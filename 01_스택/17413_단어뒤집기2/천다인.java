import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean inTag = false;

        String str = br.readLine();

        for (char c : str.toCharArray()) {
            if (c == '<') {
                while (!stack.isEmpty()) sb.append(stack.pop());

                inTag = true;
                sb.append(c);
            } else if (c == '>') {
                inTag = false;
                sb.append(c);
            } else if (inTag) {
                sb.append(c);
            } else {
                if (c == ' ') {
                    while (!stack.isEmpty()) sb.append(stack.pop());
                    sb.append(c);
                } else {
                    stack.push(c);
                }
            }
        }
        while (!stack.isEmpty()) sb.append(stack.pop());

        System.out.print(sb);
    }
}