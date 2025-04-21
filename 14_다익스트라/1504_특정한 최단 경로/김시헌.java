// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 71792 KB , 시간 : 676 ms

import java.io.*;
import java.util.*;

public class Main {    // 특정한 최단 경로
    static int N, E;
    static ArrayList<Node>[] graph;
    static boolean[] visit;
    static int[] dist;
    static int INF = 200000 * 1000 + 1;     // 200,000,001

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 풀이
        int result1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);     // v1 경유 후 v2 경유
        int result2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);     // v2 경유 후 v1 경유

        if (result1 >= INF && result2 >= INF) System.out.println(-1);
        else System.out.println(Math.min(result1, result2));    // 더 작은값 출력
    }

    static int dijkstra(int start, int end) {
        visit = new boolean[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>( (n1, n2) -> n1.cost - n2.cost );
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int tgt = current.tgt;
            int cost = current.cost;

            if (!visit[tgt]) {
                visit[tgt] = true;

                for (int i = 0; i < graph[tgt].size(); i++) {
                    Node next = graph[tgt].get(i);
                    if (dist[next.tgt] > next.cost + cost) {    // 앞으로 갈 tgt노드: dist값이 현재까지 온 비용(cost)에 tgt까지 갈 비용을 더한거보다 크면
                        dist[next.tgt] = next.cost + cost;      // 초기화 해주기

                        pq.offer(new Node(next.tgt, dist[next.tgt]));   // tgt로 갈 노드에 비용은 초기화한 값으로 pq에 넣기
                    }
                }
            }
        }

        return dist[end];
    }

    static class Node {
        int tgt, cost;
        Node(int tgt, int cost) {
            this.tgt = tgt; this.cost = cost;
        }
    }
}
