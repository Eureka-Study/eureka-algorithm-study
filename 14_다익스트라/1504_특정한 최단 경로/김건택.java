package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Gold4_1504_Dijkstra_retry {

    // 간선 정보를 저장할 클래스: 목적지 정점 번호(to), 간선 비용(cost)
    static class Node implements Comparable<Node> {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        // 우선순위 큐에서 간선 비용이 작은 순으로 정렬
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static final int INF = 200000000; // 충분히 큰 값으로 무한을 표현
    static List<Node>[] graph;        // 인접 리스트로 그래프 표현
    static int N;                     // 정점 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 입력: 정점 수 N, 간선 수 E
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력: 양방향 간선이므로 양쪽 모두 추가
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        // 반드시 거쳐야 하는 두 정점 v1, v2 입력
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 다익스트라 세 번 수행해서 거리 배열 확보
        int[] from1 = dijkstra(1);    // 시작점 1에서의 최단거리
        int[] fromV1 = dijkstra(v1);  // v1에서의 최단거리
        int[] fromV2 = dijkstra(v2);  // v2에서의 최단거리

        // 가능한 두 경로 계산:
        // 경로1: 1 -> v1 -> v2 -> N
        // 경로2: 1 -> v2 -> v1 -> N
        long path1 = (long) from1[v1] + fromV1[v2] + fromV2[N];
        long path2 = (long) from1[v2] + fromV2[v1] + fromV1[N];

        // 두 경로 중 작은 값을 선택. 경로가 없다면 -1 출력
        long result = Math.min(path1, path2);
        System.out.println(result >= INF ? -1 : result);
    }

    // 다익스트라 알고리즘
    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];         // 시작점으로부터의 최단거리 저장
        Arrays.fill(dist, INF);              // 초기에는 모두 무한대
        dist[start] = 0;

        pq.offer(new Node(start, 0));        // 시작점 추가

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 이미 처리된 더 짧은 경로가 있다면 건너뜀
            if (dist[cur.to] < cur.cost) continue;

            // 현재 정점에서 연결된 이웃들을 확인
            for (Node next : graph[cur.to]) {
                int newCost = dist[cur.to] + next.cost;

                // 더 짧은 경로를 발견하면 갱신하고 큐에 넣음
                if (dist[next.to] > newCost) {
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }

        return dist;
    }
}
