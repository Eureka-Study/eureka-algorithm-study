package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Gold4_9935_stack_retry {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();

        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] strArray = str.toCharArray();
        char[] bombArray = bomb.toCharArray();

        for (int i = 0; i < strArray.length; i++) {
            boolean flag = false;

            // 폭탄의 첫 글자와 같다면
            if (strArray[i] == bombArray[0]) {
                // 두 번째 글자부터 전체 비교 시작
                for (int j = 1; j < bombArray.length; j++) {

                    if (strArray[i+j] != bombArray[j]) {
                        for (int k = 0; k < j; k++) {
                            stack.push(strArray[i+k]);
                        }
                        i += j;
                        flag = true;
                        break;
                    }

                    i += bombArray.length;
                }

            }
            if (!flag) {
                stack.push(strArray[i]);
            }
        }

        System.out.println(stack.size());

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        System.out.println(sb.reverse().toString());
    }
}
