package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Gold3_9466 {
    static int[] graph;
    static boolean[] visited;
    static boolean[] finished;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            graph = new int[n+1];
            visited = new boolean[n+1];
            finished = new boolean[n+1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                graph[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 1; j <= n; j++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

        }
    }
    static void dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> path = new ArrayList<>();
        int current = start;

        while (true) {
            if (!visited[current]) {
                visited[current] = true;
                stack.push(current);
                path.add(current);
                current = graph[current];
            } else {
                if (!finished[current]) {
                    int cycleStart = current;
                    boolean counting = false;
                    for (int node : path) {
                        if (node == cycleStart) counting = true;
                        if (counting) count++;
                    }
                }
                break;
            }
        }

        while (!stack.isEmpty()) {
            int node = stack.pop();
            finished[node] = true;
        }
    }
}
