// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 52404 KB , 시간 : 460 ms
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 프림 알고리즘 사용
// 보통의 프림 알고리즘은 하나의 시작점에서 시작하지만
// 발전소가 있는 노드를 전부 시작점으로 보고 pqueue 에 넣고 시작

public class Gold3_10423_MST_retry {
    static int N, M, K, sum;
    static List<Vertex>[] graph;
    static boolean[] visited;
    static PriorityQueue<Vertex> pqueue = new PriorityQueue<>((v1, v2) -> v1.c - v2.c);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        visited = new boolean[N];
        int[] pStation = new int[K]; // 발전소가 설치된 도시

        for (int i = 0 ; i < N; i++) {
            graph[i] = new ArrayList<Vertex>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int ps = Integer.parseInt(st.nextToken());
            pStation[i] = ps - 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[v1-1].add(new Vertex(v2-1, c));
            graph[v2-1].add(new Vertex(v1-1, c));
        }

        for (int v : pStation) {
            visited[v] = true;
            for (Vertex next : graph[v]) {
                pqueue.offer(next);
            }
        }

        while (!pqueue.isEmpty()) {
            Vertex v = pqueue.poll();

            if (visited[v.v]) {
                continue;
            }

            visited[v.v] = true;
            sum += v.c;

            for (Vertex next : graph[v.v]) {
                if (!visited[next.v]) {
                    pqueue.offer(next);
                }
            }

        }

        System.out.println(sum);

    }

    static class Vertex {
        int v, c;
        Vertex(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

}
