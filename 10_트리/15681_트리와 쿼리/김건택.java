package boj;

import java.io.*;
import java.util.*;

public class Gold5_15681 {

    static int N, R, Q;

    static boolean[] visited;
    static List[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            graph[U].add(V);
            graph[V].add(U);
        }

        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            if (u == R) {
                bw.write(String.valueOf(N) + "\n");
                continue;
            }

            bw.write(String.valueOf(bfs(u)));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs(int u) {
        Arrays.fill(visited, false);
        int count = 1;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        visited[N] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            ArrayList<Integer>list = (ArrayList<Integer>) graph[v];
            for (Integer i : list) {
                if (!visited[i]) {
                    if (i == u) {
                        count = 0;
                        queue.clear();
                    }
                    queue.offer(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
