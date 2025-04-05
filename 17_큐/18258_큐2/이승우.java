//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 333512 KB , 시간 : 1236 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 큐2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        
        for(int n = 1; n <= N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            switch (st.nextToken()) {
                case "push":
                    int X = Integer.parseInt(st.nextToken()); //push 일 경우만 뒤가 존재
                    deque.offer(X); // 추가해주기
                    break;
                case "pop":
                    sb.append(deque.isEmpty() ? -1 : deque.pollFirst()) // 하나 빼는데 없을 경우 -1, 있으면 큐의 최상단 빼기
                      .append("\n");
                    break;
                case "size":
                    sb.append(deque.size()) // deque의 사이즈 측정
                      .append("\n");
                    break;
                case "empty":
                    sb.append(deque.isEmpty() ? 1 : 0) // 비어있으면 1 아니면 0
                      .append("\n");
                    break;
                case "front":
                    sb.append(deque.isEmpty() ? -1 : deque.peekFirst()) // 큐의 최상단 확인, 만약 비어있을 경우 -1
                      .append("\n");
                    break;
                case "back":
                    sb.append(deque.isEmpty() ? -1 : deque.peekLast()) // 큐의 최하단 확인, 만약 비어있을 경우 -1
                      .append("\n");
                    break;
                default:
                    break;
            }
        }

        System.out.print(sb);
    }
}
