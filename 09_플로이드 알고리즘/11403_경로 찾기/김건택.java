// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16356 KB , 시간 : 168 ms
package boj;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Silver1_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] graph = new List[N];
        boolean[] visited;

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int check = Integer.parseInt(st.nextToken());
                if (check == 1) {
                    graph[i].add(j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
//            visited[i] = true;

            while (!queue.isEmpty()) {
                int v = queue.poll();

                for (Integer next : graph[v]) {
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }

            for (boolean b : visited) {
                if (b) {
                    bw.write("1 ");
                } else {
                    bw.write("0 ");
                }
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
