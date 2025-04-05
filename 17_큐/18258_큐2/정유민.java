// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 360384 KB, 시간 : 1188 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 정유민 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            if (s.startsWith("push")) {
                int num = Integer.parseInt(s.split(" ")[1]);
                queue.offer(num);
            }
            else if (s.equals("front")) {
                if (queue.isEmpty()) sb.append(-1).append("\n");
                else sb.append(queue.peekFirst()).append("\n");
            }
            else if (s.equals("back")) {
                if (queue.isEmpty()) sb.append(-1).append("\n");
                else sb.append(queue.peekLast()).append("\n");
            }
            else if (s.equals("size")) {
                sb.append(queue.size()).append("\n");
            }
            else if (s.equals("empty")) {
                if (queue.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
            else if (s.equals("pop")) {
                if (queue.isEmpty()) sb.append(-1).append("\n");
                else sb.append(queue.poll()).append("\n");
            }
        }

        System.out.print(sb);
    }
}
