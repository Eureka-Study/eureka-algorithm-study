import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 학교탐방하기 {
    static int[] parent;

    // 간선 클래스: u <-> v, w = (C==0?1:0)
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // path 컴프레션
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }


    static int kruskal(Edge[] edges, int needEdges) {
        int cnt = 0, sum = 0;
        for (Edge e : edges) {
            int ru = find(e.u), rv = find(e.v);
            if (ru != rv) {
                union(ru, rv);
                sum += e.w;
                if (++cnt == needEdges) break;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());    // 건물 개수
        int M = Integer.parseInt(st.nextToken());    // 도로 개수

        // 입력된 M+1개의 간선을 저장
        Edge[] edges = new Edge[M + 1];
        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // C==0(오르막): w=1, C==1(내리막): w=0
            edges[i] = new Edge(a, b, (c == 0 ? 1 : 0));
        }

        // 두 번 Kruskal을 위해 배열 복제
        Edge[] asc  = Arrays.copyOf(edges, edges.length);
        Edge[] desc = Arrays.copyOf(edges, edges.length);

        // 최소 스패닝 트리: w 오름차순
        Arrays.sort(asc, Comparator.comparingInt(e -> e.w));
        // 최대 스패닝 트리: w 내림차순
        Arrays.sort(desc, (e1, e2) -> e2.w - e1.w);

        int neededEdges = N; // 노드 수 N+1 → 필요한 간선 수 N

        // 최소 피로도 경로 (오르막 최소)
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) parent[i] = i;
        int minUphills = kruskal(asc, neededEdges);

        // 최대 피로도 경로 (오르막 최대)
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) parent[i] = i;
        int maxUphills = kruskal(desc, neededEdges);

        // 결과: k_max^2 - k_min^2
        int result = maxUphills * maxUphills - minUphills * minUphills;
        System.out.println(result);
    }
}
