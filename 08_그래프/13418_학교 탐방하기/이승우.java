
//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 190924 KB , 시간 : 940 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 학교탐방하기 {
    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge>[] adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            // 어디에서 이동이 될지 모르므로 둘 다 저장
            adj[A].add(new Edge(B, C));
            adj[B].add(new Edge(A, C));
        }

        int minUphill = prim(adj, N, false); // 최선 경로
        int maxUphill = prim(adj, N, true); // 최악 경로

        int result = maxUphill * maxUphill - minUphill * minUphill; // 피로도는 제곱이므로 제곱을 하고 뺀다.
        System.out.println(result);
    }

    static int prim(List<Edge>[] adj, int n, boolean descending) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (e1, e2) -> descending ? e1.cost - e2.cost : e2.cost - e1.cost); // 최선은 내리막길 우선 / 최악은 오르막길 우선

        int uphillCount = 0;
        int visitedCount = 0;

        visited[0] = true;
        visitedCount++;
        for (Edge e : adj[0]) { // 입구에서 출발
            pq.offer(e);
        }

        while (visitedCount <= n && !pq.isEmpty()) { // 비거나 이미 최대값인 경우까지
            Edge e = pq.poll();
            if (visited[e.to]) { // 이미 돈 곳은 또 돌 필요가 없다.
                continue;
            }

            visited[e.to] = true;
            visitedCount++;
            if (e.cost == 0) { // 0은 오르막길이므로 피로도 누적
                uphillCount++;
            }
            for (Edge next : adj[e.to]) { // 해당 건물에서 갈 수 있는 모든 건물 확인
                if (!visited[next.to]) { // 다음 건물에 이미 간적이 있는지
                    pq.offer(next); // 없으면 확인하러 가보기
                }
            }
        }
        return uphillCount; // 다 돌았으면 이 것이 최종 피로도
    }
}
