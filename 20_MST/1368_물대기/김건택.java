package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Gold2_1368_MST {
    // Prim 실패
//    static int N, sum;
//    static int[] well;
//    static int[][] matrix;
//    static boolean[] visit;
//    static PriorityQueue<Vertex> pqueue = new PriorityQueue<Vertex>((v1, v2) -> v1.c - v2.c);
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//        well = new int[N];
//        matrix = new int[N][N];
//        visit = new boolean[N];
//
//        int min = 100000;
//
//        for (int i = 0; i < N; i++) {
//            well[i] = Integer.parseInt(br.readLine());
//            min = Math.min(min, well[i]);
//        }
//
//        for (int i = 0; i < N; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < N; j++) {
//                matrix[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        pqueue.offer(new Vertex(0, 0));
//        int cnt = 0;
//
//        while (!pqueue.isEmpty()) {
//            Vertex vertex = pqueue.poll();
//
//            if (visit[vertex.v]) continue;
//
//            visit[vertex.v] = true;
//            sum += vertex.c;
//            cnt ++;
//
//            if (cnt == N) break;
//
//            for (int i = 0; i < N; i++) {
//                if (matrix[vertex.v][i] == 0 || matrix[vertex.v][i] > well[vertex.v] || matrix[vertex.v][i] > well[i] || visit[i]) continue;
//
//                pqueue.offer(new Vertex(i, matrix[vertex.v][i]));
//            }
//        }
//        System.out.println(sum + min);
//    }
//
//    static class Vertex {
//        int v, c;
//        public Vertex(int v, int c) {
//            this.v = v;
//            this.c = c;
//        }
//    }

    // Kruskal 실패
//    static int N, sum;
//    static int[] well;
//    static List<Edge> edges;
//    static int[] parent;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//
//        well = new int[N];
//        edges = new ArrayList<Edge>();
//        parent = new int[N];
//
//        for (int i = 0; i < N; i++) {
//            well[i] = Integer.parseInt(br.readLine());
//        }
//
//        for (int i = 0; i < N; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < N; j++) {
//                if (i == j) {
//                    st.nextToken();
//                    continue;
//                }
//                edges.add(new Edge(i, j, Integer.parseInt(st.nextToken())));
//            }
//        }
//
//        makeSet();
//
//        edges.sort((e1, e2) -> e1.c - e2.c);
//
//        int cnt = 0;
//        for (Edge e : edges) {
//            if (union(e.v1, e.v2) && well[e.v1] >= e.c && well[e.v2] >= e.c) {
//                System.out.println(e);
//                sum += e.c;
//            }
//        }
//
//        Set<Integer> roots = new HashSet<>();
//
//        for (int i = 0; i < N; i++) {
//            roots.add(findSet(i));
//        }
//
//        for (Integer root : roots) {
//            System.out.println(well[root]);
//            sum += well[root];
//        }
//
//        System.out.println(sum);
//
//    }
//
//    static class Edge {
//        int v1, v2, c;
//        Edge(int v1, int v2, int c) {
//            this.v1 = v1;
//            this.v2 = v2;
//            this.c = c;
//        }
//
//        @Override
//        public String toString() {
//            return "{" + v1 + ", " + v2 + ", " + c + "}";
//        }
//    }
//
//    static void makeSet() {
//        for (int i = 0; i < N; i++) {
//            parent[i] = i;
//        }
//    }
//
//    static int findSet(int x) {
//        if (parent[x] == x) return x;
//        return parent[x] = findSet(parent[x]);
//    }
//
//    static boolean union(int x, int y) {
//        int px = findSet(x);
//        int py = findSet(y);
//
//        if (px == py) return false;
//        if (well[px] < well[py]) parent[py] = px;
//        else parent[px] = py;
//
//        return true;
//    }

    static int N;
    static List<Edge> edges;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        edges = new ArrayList<>();

        // 1. 각 논에 우물을 파는 비용 -> 가상 노드 N에서 각 논으로 간선 연결
        for (int i = 0; i < N; i++) {
            int wellCost = Integer.parseInt(br.readLine());
            edges.add(new Edge(N, i, wellCost)); // 가상 노드는 N번째로 설정
        }

        // 2. 논들 사이의 연결 비용 (i < j 조건으로 중복 제거)
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i < j) {
                    edges.add(new Edge(i, j, cost));
                }
            }
        }

        // 3. MST를 위한 초기화
        parent = new int[N + 1]; // 논 N개 + 가상노드 1개
        makeSet();

        // 4. 간선 정렬
        edges.sort(Comparator.comparingInt(e -> e.c));

        int sum = 0;
        int count = 0;

        // 5. Kruskal 알고리즘 수행
        for (Edge e : edges) {
            if (union(e.v1, e.v2)) {
                sum += e.c;
                count++;
                if (count == N) break; // 논 N개를 모두 연결하면 종료
            }
        }

        System.out.println(sum);
    }

    static class Edge {
        int v1, v2, c;
        Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }

    static void makeSet() {
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int findSet(int x) {
        if (parent[x] != x) {
            parent[x] = findSet(parent[x]);
        }
        return parent[x];
    }

    static boolean union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px == py) return false;

        parent[py] = px;
        return true;
    }
}
