//언어 : JAVA , (성공/실패) : 1/4 , 메모리 : 23384 KB , 시간 : 248 ms
import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 빌딩 개수
        Stack<Integer> stack = new Stack<>();
        long count = 0; // 총 볼 수 있는 옥상 정원의 수

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine()); // 현재 빌딩 높이

            // 스택이 비어 있지 않고, 스택의 top이 현재 빌딩보다 낮다면 pop
            while (!stack.isEmpty() && stack.peek() <= height) {
                stack.pop();
            }

            // 스택에 남아 있는 빌딩 수가 현재 빌딩에서 볼 수 있는 빌딩 수
            count += stack.size();

            // 현재 빌딩을 스택에 push
            stack.push(height);
        }
        System.out.print(count);
    }
}