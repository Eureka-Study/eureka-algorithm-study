// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 48668 KB , 시간 : 452 ms

package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//1. N개의 문제는 모두 풀어야 한다.
//2. 먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
//3. 가능하면 쉬운 문제부터 풀어야 한다.
public class Gold2_1766_topological_sorting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N + 1];
        int[] degree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            degree[to]++;
        }

        // 쉬운 문제부터 풀기 위해 Queue 가 아닌 PrrorityQueue 사용
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            for (int next : graph[current]) {
                degree[next]--;
                if (degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : result) {
            sb.append(n).append(" ");
        }

        System.out.println(sb);
    }
}
