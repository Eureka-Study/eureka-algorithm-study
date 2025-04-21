// 언어 : JAVA , (성공/실패) : 1/3 , 메모리 : 271788 KB , 시간 : 924 ms

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Gold4_1707 {
    static List<Integer>[] graph;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V+1];
            check = new int[V + 1];

            for (int j = 1; j <= V; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                graph[v1].add(v2);
                graph[v2].add(v1);
            }

            boolean isBipartite = true;

            for (int j = 1; j <= V; j++) {
                if (check[j] == 0) {
                    if (!bfs(j)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            sb.append(isBipartite ? "YES\n" : "NO\n");
        }
        System.out.println(sb);
    }

    static boolean bfs(int v) {
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(v);
        check[v] = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (check[next] == 0) {
                    check[next] = check[current] * (-1);
                    queue.offer(next);
                }
                else if (check[next] == check[current]) {
                    return false;
                }
            }
        }
        return true;
    }
}
