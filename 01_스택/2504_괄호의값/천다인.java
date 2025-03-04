// 언어 : JAVA , (성공/실패) : 0/1 , 메모리 : 14304 KB , 시간 : 108 ms
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        System.out.println(calculateBracketValue(input));
    }

    private static int calculateBracketValue(String s) {
        Stack<Character> stack = new Stack<>(); // 괄호의 짝을 확인하기 위한 스택
        int temp = 1; // 현재 괄호의 값을 추적하는 변수
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // 여는 괄호 '('인 경우
            if (ch == '(') {
                stack.push(ch); // 스택에 '(' 추가
                temp *= 2; // 현재 값에 2를 곱함
            }
            // 여는 괄호 '['인 경우
            else if (ch == '[') {
                stack.push(ch); // 스택에 '[' 추가
                temp *= 3; // 현재 값에 3을 곱함
            }
            // 닫는 괄호 ')'인 경우
            else if (ch == ')') {
                // 스택이 비었거나, 짝이 맞지 않는 경우 올바른 괄호열이 아님
                if (stack.isEmpty() || stack.peek() != '(') {
                    return 0; // 올바르지 않은 괄호열이므로 0 반환
                }
                // 직전 문자가 '('였다면 현재 값을 결과에 추가
                if (s.charAt(i - 1) == '(') {
                    res += temp;
                }
                stack.pop(); // '(' 제거
                temp /= 2; // 괄호가 닫혔으므로 2로 나눠줌
            }
            // 닫는 괄호 ']'인 경우
            else if (ch == ']') {
                // 스택이 비었거나, 짝이 맞지 않는 경우 올바른 괄호열이 아님
                if (stack.isEmpty() || stack.peek() != '[') {
                    return 0; // 올바르지 않은 괄호열이므로 0 반환
                }
                // 직전 문자가 '['였다면 현재 값을 결과에 추가
                if (s.charAt(i - 1) == '[') {
                    res += temp;
                }
                stack.pop(); // '[' 제거
                temp /= 3; // 괄호가 닫혔으므로 3으로 나눠줌
            }
        }
        // 모든 연산이 끝난 후 스택이 비어 있으면 최종 결과 반환, 그렇지 않으면 0 반환
        return stack.isEmpty() ? res : 0;
    }
}