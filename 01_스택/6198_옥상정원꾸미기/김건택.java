// 언어 : JAVA , (성공/실패) : 0/1 , 메모리 :  KB , 시간 :  ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long ans = 0;

        Deque<Integer> stack = new ArrayDeque<>(); // 빌딩의 높이를 입력받을 스택 선언

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine()); // 차례로 빌딩 높이 입력

            while (!stack.isEmpty() && stack.peek() <= h) { // 스택의 top이 입력 받은 빌딩 높이보다 작거나 같으면 pop
                stack.pop();
            }

            ans += stack.size(); // 스택에 남아 있는 건물(h)들은 현재 건물을 볼 수 있음

            stack.push(h); // 현재 건물을 스택에 추가
        }
        System.out.println(ans);
    }
}