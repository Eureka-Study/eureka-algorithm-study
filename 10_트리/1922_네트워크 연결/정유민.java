// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 51116 KB , 시간 : 588 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정유민 {

    static Edge[] edges; // 간선 리스트 (컴퓨터 연결 정보)
    static int N, M, sum; // N: 컴퓨터 수, M: 간선 수, sum: 최소 비용 합
    static int[] parent; // 각 컴퓨터의 대표 노드

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N];
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1; // 0 베이스로 하기 위해 1 뺐다.
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(v1, v2, c);
        }

        makeSet();
        Arrays.sort(edges, (e1, e2) -> e1.c - e2.c); // 간선 비용 기준 정렬

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

    static void makeSet() {
        for (int i = 0; i < N; i++) {
            parent[i] = i; // 각 컴퓨터가 자기 자신을 대표함
        }
    }

    static boolean union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px == py) return false; // 이미 같은 집합
        if (px < py) parent[py] = px; // 병합
        else parent[px] = py;
        return true;
    }

    static int findSet(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findSet(parent[x]); // 경로 압축 재귀로
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
