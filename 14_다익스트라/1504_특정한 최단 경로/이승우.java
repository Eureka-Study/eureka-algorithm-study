
//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 68648 KB , 시간 : 656 ms
import java.io.*;
import java.util.*;

public class 특정한최단경로 {

    static List<int[]>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            /**
             * a, b 사이를 건널 때 걸리는 시간(거리)
             */
            list[a].add(new int[] { b, c });
            list[b].add(new int[] { a, c });
        }

        st = new StringTokenizer(br.readLine(), " ");
        /**
         * 1부터 시작해야하며
         * v1과 v2를 반드시 통과해야하고
         * N까지 도착하는 최소값 체크
         */
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        /**
         * 총 두가지의 경우의 수가 존재
         * 1, v1, v2, N
         * 1, v2, v1, N
         * 이 중 작은 것을 확인
         */
        int a = dijkstra(1, v1, N);
        int b = dijkstra(v1, v2, N);
        int c = dijkstra(v2, N, N);

        int d = dijkstra(1, v2, N);
        int e = dijkstra(v2, v1, N);
        int f = dijkstra(v1, N, N);

        /**
         * 혹시 이 중 불가능한 경로가 존재하는지 확인
         */
        int path1 = (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE || c == Integer.MAX_VALUE)
                ? Integer.MAX_VALUE
                : a + b + c;

        int path2 = (d == Integer.MAX_VALUE || e == Integer.MAX_VALUE || f == Integer.MAX_VALUE)
                ? Integer.MAX_VALUE
                : d + e + f;

        // 최소값 확인
        int answer = Math.min(path1, path2);
        // 최대값이면 갈 수 있는 경우가 없으므로 -1 아니면 갈 수 있으므로 최소 값 표시
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static int dijkstra(int start, int end, int N) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 최소값을 찾는 것이므로 최대값으로 초기화
        dist[start] = 0; // 처음은 내가 있던 곳이므로 비용은 0

        // [다음 타켓 경로, 비용] 이므로 가장 저렴한 곳을 가는게 목표여서 배열의 1번 기준으로 정렬을 해야한다.
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] { start, 0 }); // 처음은 역시 start 지점이며 아무것도 이동하지않았으므로 0

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];

            if (visited[cur]) // 이미 이동을 했는지 확인
                continue;
            visited[cur] = true; // 이동했음을 체크

            for (int[] next : list[cur]) {
                int neighbor = next[0];
                int cost = next[1];
                if (!visited[neighbor] && dist[neighbor] > dist[cur] + cost) { // 이동하지않았고 이동할 경로가 이전에 예측했던 경로보다 저렴하게 이동
                                                                               // 가능한지 확인
                    dist[neighbor] = dist[cur] + cost;
                    pq.offer(new int[] { neighbor, dist[neighbor] });
                }
            }
        }
        return dist[end];// 다 이동했으면 최종 목적지의 최단 경로 리턴
    }
}