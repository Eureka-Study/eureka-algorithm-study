// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 359964 KB , 시간 : 2288 ms

import java.io.*;
import java.util.*;

public class Gold_1647 {    // 도시 분할 계획
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Vertex>[] graph = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        boolean[] visit = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Vertex(B, C));
            graph[B].add(new Vertex(A, C));
        }

        PriorityQueue<Vertex> pq = new PriorityQueue<>( (v1, v2) -> v1.c - v2.c );
        pq.offer(new Vertex(1, 0));

        int costSum = 0;    // 현재 MST 간선 비용 합
        int maxCost = 0;    // 현재 간선비용 최댓값 (마지막에 두 도시로 나눌때 없앨 것)

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();

            if (visit[current.v]) continue;
            visit[current.v] = true;

            maxCost = Math.max(maxCost, current.c);
            costSum += current.c;

            ArrayList<Vertex> curList = graph[current.v];
            for (int i = 0; i < curList.size(); i++) {
                Vertex next = curList.get(i);
                if (!visit[next.v]) pq.offer(next);
            }
        }

        System.out.println(costSum - maxCost);  // MST 완성 후, 간선 최댓값 없애줌으로써 도시분할 완료

    }

    static class Vertex {
        int v, c;
        Vertex(int v, int c) {
            this.v = v; this.c = c;
        }
    }
}
