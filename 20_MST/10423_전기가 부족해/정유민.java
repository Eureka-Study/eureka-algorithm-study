import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정유민 {

    static Edge[] edges;
    static int N, M, K, sum;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 발전소

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int city = Integer.parseInt(st.nextToken()) - 1;
            parent[city] = -1;
        }

        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(v1, v2, c);
        }

        Arrays.sort(edges, (e1, e2) -> e1.c - e2.c);

        int cnt = 0;
        for (Edge edge : edges) {
            if (union(edge.v1, edge.v2)) {
                sum += edge.c;
                cnt++;
                if (cnt == N - K) break;
            }
        }

        System.out.println(sum);
    }

    static int findSet(int x) {
        if (parent[x] == -1) return -1;
        if (parent[x] == x) return x;
        return parent[x] = findSet(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px == py) return false;
        if (px == -1) parent[py] = px;
        else if (py == -1) parent[px] = py; // 발전소 설치된 도시랑연결
        else parent[py] = px;
        return true;
    }

    static class Edge {
        int v1, v2, c;

        public Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }
}
