// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 26796 KB , 시간 : 232 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String bomb = br.readLine();
        int n = s.length();
        int m = bomb.length();

        char[] stack = new char[n];
        int top = 0;

        for (int i = 0; i < n; i++) {
            stack[top++] = s.charAt(i);

            // 폭발 문자열 길이 이상 쌓였으면 매칭 검사
            if (top >= m) {
                boolean matched = true;
                for (int j = 0; j < m; j++) {
                    if (stack[top - m + j] != bomb.charAt(j)) {
                        matched = false;
                        break;
                    }
                }
                // 매칭되면 pop
                if (matched) {
                    top -= m;
                }
            }
        }

        StringBuilder sb = new StringBuilder(top);
        for (int i = 0; i < top; i++) {
            sb.append(stack[i]);
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}
