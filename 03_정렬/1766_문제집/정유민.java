//언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 48076 KB , 시간 : 404 ms

/*
N개의 문제는 모두 풀어야 한다.
먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
가능하면 쉬운 문제부터 풀어야 한다.
 */

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] degree = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            degree[b]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer> answer = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();
            answer.add(now);

            for (int next : graph[now]) {
                degree[next]--;
                if (degree[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int x : answer) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}