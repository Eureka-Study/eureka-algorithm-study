
//언어 : JAVA , (성공/실패) : 1/1, 메모리 : 43660 KB , 시간 : 424 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        List<Integer>[] list = new ArrayList[N + 1];
        int[] priority = new int[N + 1];

        for (int n = 1; n <= N; n++) {
            list[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");

            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());

            list[A].add(B); // A 뒤에 존재해야하는 존재들을 list로 저장
            priority[B]++; // B는 후열이기 때문에 우선순위에 밀린다.
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(); // queue에 들어간 순간은 문제를 풀어도 문제가 없으므로 쉬운 순부터(조건)

        for (int n = 1; n <= N; n++) {
            if (priority[n] == 0) // 아무런 조건이 없는 것부터 들어갈 수 있으므로 그것부터 풀기
                queue.offer(n);
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) { // 문제 다 풀때까지
            int q = queue.poll();

            for (int l : list[q]) { // A 뒤에 나올 B들
                if (--priority[l] == 0) // 우선순위를 줄여서 0이 된 문제는 추가
                    queue.offer(l);
            }

            sb.append(q).append(" "); // 문제 풀기
        }

        System.out.println(sb.toString().trim()); // 문제를 푼 순서
    }
}