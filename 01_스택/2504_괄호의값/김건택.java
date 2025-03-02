// 언어 : JAVA , (성공/실패) : 0/1 , 메모리 : 0 KB , 시간 : 0 ms

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int answer;
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        Deque<Character> stack = new ArrayDeque<>();

        int temp = 1;  // 현재 괄호의 값을 저장
        int result = 0; // 최종 결과

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(ch);
                temp *= 2;
            } else if (ch == '[') {
                stack.push(ch);
                temp *= 3;
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.peek() != '(') System.out.println(0);
                if (s.charAt(i - 1) == '(') result += temp;
                stack.pop();
                temp /= 2;
            } else if (ch == ']') {
                if (stack.isEmpty() || stack.peek() != '[') System.out.println(0);
                if (s.charAt(i - 1) == '[') result += temp;
                stack.pop();
                temp /= 3;
            }
        }

        answer =  stack.isEmpty() ? result : 0;

        System.out.println(answer);
    }
}
