// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 49408 KB , 시간 : 532 ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gold4_1922 {
    static int N, M, sum;
    static Edge[] edges;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = new Edge[M];
        parent = new int[N];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(v1-1, v2-1, c);
        }

        Arrays.sort(edges, (e1, e2) -> e1.c - e2.c);

        makeSet();

        int cnt = 0;
        int edgesLen = edges.length;
        for (int i = 0; i < edgesLen; i++) {
            Edge edge = edges[i];

            if (union(edge.v1, edge.v2)) {
                sum += edge.c;
                cnt++;
                if (cnt == N - 1) break;
            }
        }

        System.out.println(sum);

    }

    static class Edge {
        int v1, v2, c;
        Edge (int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }

    static void makeSet() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    static int findSet(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findSet(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px == py) return false;
        if (px < py) parent[py] = px;
        else parent[px] = py;

        return true;
    }
}
