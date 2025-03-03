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
            if (s.equals("[") || s.equals("(")) { // 괄호가 열렸을 때
                stack.push(s);
            } else { // 괄호가 닫혔을 때
                int inner = 0;

                if (stack.isEmpty()) { // 열린적이 없는데 닫으면 잘못된 괄호이므로 0
                    System.out.println(0);
                    return;
                }

                // 스택이 비어있지않거나 괄호가 열린게 아닐 때까지 반복 (괄호 안 계산)
                while (!stack.isEmpty() && !stack.peek().equals("[") && !stack.peek().equals("(")) {
                    try {
                        inner += Integer.parseInt(stack.pop());
                    } catch (NumberFormatException e) { // 올바른 값이 아니므로 0
                        System.out.println(0);
                        return;
                    }
                }

                // 열린게 나올 때까지이므로 비어있으면 안됨
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }

                // 올바르게 닫히지않았으면 종료
                String openBracket = stack.pop();
                if (s.equals("]") && !openBracket.equals("[")) {
                    System.out.println(0);
                    return;
                } else if (s.equals(")") && !openBracket.equals("(")) {
                    System.out.println(0);
                    return;
                }

                // ]면 3 )면 2를 곱하기 inner이 0이면 바로 닫힌거이므로 1로하여 초기값으로 만들기 그게 아니면 내부에 값이 있던 것
                int value = (s.equals("]") ? 3 : 2) * (inner == 0 ? 1 : inner);
                stack.push(String.valueOf(value)); // 또 다시 감쌀수도 있으므로 다시 넣어 계산
            }
        }
        
        int result = 0;
        while (!stack.isEmpty()) { // 다 돌았으므로 스택에 쌓여있던 것 더하기
            try {
                result += Integer.parseInt(stack.pop());
            } catch (NumberFormatException e) { //괄호가 남아있으면 잘못된 것으므로 0
                System.out.println(0);
                return;
            }
        }

        System.out.println(result);
    }
}
