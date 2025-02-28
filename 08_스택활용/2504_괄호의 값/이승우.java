// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 14408KB , 시간 : 116ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Bracket {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack = new Stack<>();
        String[] bracket = br.readLine().split("");

        for (String s : bracket) {
            if (s.equals("[") || s.equals("(")) {
                stack.push(s);
            } else {
                int inner = 0;

                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }

                while (!stack.isEmpty() && !stack.peek().equals("[") && !stack.peek().equals("(")) {
                    try {
                        inner += Integer.parseInt(stack.pop());
                    } catch (NumberFormatException e) {
                        System.out.println(0);
                        return;
                    }
                }

                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }

                String openBracket = stack.pop();
                if (s.equals("]") && !openBracket.equals("[")) {
                    System.out.println(0);
                    return;
                } else if (s.equals(")") && !openBracket.equals("(")) {
                    System.out.println(0);
                    return;
                }

                int value = (s.equals("]") ? 3 : 2) * (inner == 0 ? 1 : inner);
                stack.push(String.valueOf(value));
            }
        }
        
        int result = 0;
        while (!stack.isEmpty()) {
            try {
                result += Integer.parseInt(stack.pop());
            } catch (NumberFormatException e) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(result);
    }
}
