// 언어 : JAVA , (성공/실패) : 1/5 , 메모리 : 21640 KB , 시간 : 204 ms

import java.io.*;
import java.util.*;

public class Main {    // 옥상 정원 꾸미기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 빌딩 개수 인풋
        Deque<Integer> stack = new ArrayDeque<>();  // 높은 빌딩들 모은 스택
        long ans = 0;    // 정답

        int height = Integer.parseInt(br.readLine());   // 인풋 받기
        stack.push(height);

        for (int i = 1; i < N; i++) {
            height = Integer.parseInt(br.readLine());   // 인풋 받기

            while (!stack.isEmpty() && height >= stack.peek()) {    // 만약 현재 높이가 스택의 peek보다 크면
                stack.pop();        // 스택의 peek을 꺼냄
            }
            ans += stack.size();    // 현재 전까지 peek이 볼 수 있는 오른쪽 빌딩들 개수 더해주기

            stack.push(height);     // 현재 빌딩 높이 스택에 넣어주기
        }

        System.out.println(ans);
    }
}
