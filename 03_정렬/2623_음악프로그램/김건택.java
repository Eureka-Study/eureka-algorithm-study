package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Gold3_2623_retry {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[N + 1];
        int[] degree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] order = new int[n];
            for (int j = 0; j < n; j++) {
                order[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < n - 1; j++) {
                int from = order[j];
                int to = order[j + 1];
                graph[from].add(to);
                degree[to]++;
            }
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            for (int next : graph[current]) {
                degree[next]--;
                if (degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (result.size() != N) { // 크기가 가수의 수와 다르면 불가능
            bw.write("0\n");
        } else {
            for (int singer : result) {
                bw.write(singer + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
