import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Town {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] town = new long[N];

        for (int i = 0; i < N; i++) {
            town[i] = Long.parseLong(br.readLine());
        }

        Stack<Long> stack = new Stack<>();
        long marking = 0;

        for (int i = 0; i < N; i++) {
            // 스택에서 현재 건물보다 낮은 건물들을 제거
            while (!stack.isEmpty() && stack.peek() <= town[i]) {
                stack.pop();
            }

            // 스택에 남아 있는 건물 수가 현 건물을 볼 수 있는 건물 수
            marking += stack.size();

            // 현재 건물을 스택에 추가
            stack.push(town[i]);
        }

        System.out.println(marking);
    }
}