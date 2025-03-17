// 언어 : JAVA , (성공/실패) : 0/1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); // 키 비교 횟수

        Deque<Integer> deque = new ArrayDeque<>();

        while (m-- > 0) {

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            if (deque.contains(b)) { // b가 이미 큐에 존재할 때, a는 항상 그 앞에 위치해야 한다.
                deque.offerFirst(a);
            }
            else if (deque.contains(a)) { // a가 이미 큐에 존재할 때, b는 항상 그 뒤에 위치해야 한다.
                deque.offerLast(b);
            }
            else {
                deque.offer(a);
                deque.offer(b);
                }
        }

        StringBuilder sb = new StringBuilder();

        while (!deque.isEmpty()) {
            sb.append(deque.poll()).append(" ");
        }
        System.out.print(sb);
    }
}
