// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 173828 KB , 시간 : 1220 ms

package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gold3_13418_MST {
    static int N, M, min, max;
    static Edge[] edgesToMax;
    static Edge[] edgesToMin;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgesToMax = new Edge[M + 1];
        edgesToMin = new Edge[M + 1];
        parent = new int[N + 1];

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edgesToMax[i] = new Edge(v1, v2, c);

            if (c == 1) {
                edgesToMin[i] = new Edge(v1, v2, 0);
            } else {
                edgesToMin[i] = new Edge(v1, v2, 1);
            }

        }

        Arrays.sort(edgesToMax, (e1, e2) -> e1.c - e2.c);

        makeSet();

        int cnt = 0;
        for (Edge e : edgesToMax) {
            if (union(e.v1, e.v2)) {
                if (e.c == 0) max++;
                cnt++;
                if (cnt == N) break;
            }
        }

        max = max * max;

        Arrays.sort(edgesToMin, (e1, e2) -> e1.c - e2.c);

        makeSet();

        cnt = 0;
        for (Edge e : edgesToMin) {
            if (union(e.v1, e.v2)) {
                if (e.c == 1) min++;
                cnt++;
                if (cnt == N) break;
            }
        }

        min = min * min;

        System.out.println(max - min);
    }

    static class Edge {
        int v1, v2, c;

        Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }

        @Override
        public String toString() {
            return "[" + v1 + ", " + v2 + ", " + c + "]\n"; // 개행문자 추가
        }
    }

    static void makeSet() {
        for (int i = 0; i < N + 1; i++) {
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
