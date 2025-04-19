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

            list[a].add(new int[] { b, c });
            list[b].add(new int[] { a, c });
        }

        st = new StringTokenizer(br.readLine(), " ");
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int a = dijkstra(1, v1, N);
        int b = dijkstra(v1, v2, N);
        int c = dijkstra(v2, N, N);

        int d = dijkstra(1, v2, N);
        int e = dijkstra(v2, v1, N);
        int f = dijkstra(v1, N, N);

        int path1 = (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE || c == Integer.MAX_VALUE)
                ? Integer.MAX_VALUE
                : a + b + c;

        int path2 = (d == Integer.MAX_VALUE || e == Integer.MAX_VALUE || f == Integer.MAX_VALUE)
                ? Integer.MAX_VALUE
                : d + e + f;

        int answer = Math.min(path1, path2);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static int dijkstra(int start, int end, int N) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] { start, 0 });

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];

            if (visited[cur])
                continue;
            visited[cur] = true;

            for (int[] next : list[cur]) {
                int neighbor = next[0];
                int cost = next[1];
                if (!visited[neighbor] && dist[neighbor] > dist[cur] + cost) {
                    dist[neighbor] = dist[cur] + cost;
                    pq.offer(new int[] { neighbor, dist[neighbor] });
                }
            }
        }
        return dist[end];
    }
}