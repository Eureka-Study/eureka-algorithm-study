package boj;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gold4_1647_retry {
    static int N, M, sum;
    static Edge[] edges;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(v1 -1, v2 -1, c);
        }

        makeSet();

        Arrays.sort(edges,(e1, e2) -> e1.c - e2.c);

        int cnt = 0;
        int edgesLen = edges.length;
        int max=0;
        for (int i = 0; i < edgesLen; i++) {
            Edge edge = edges[i];
            if (union(edge.v1, edge.v2)) {
                sum+=edge.c;
                max = Math.max(max, edge.c);
                cnt++;
                if(cnt == N -1) break;
            }
        }
        System.out.println(sum - max);
    }

    static class Edge {
        int v1, v2, c;

        Edge(int v1, int v2, int c){
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

    static boolean union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if( py == px ) return false; // 두 부모가 같다. 같은 집합이다. 이미 두 정점이 같은 집합으로 선택되어 있다.
        if( px < py ) parent[py] = px;
        else parent[px] = py;

        return true;
    }

    static int findSet(int x) {
        if( parent[x] == x ) return x;
        return parent[x] = findSet(parent[x]);
    }
}
