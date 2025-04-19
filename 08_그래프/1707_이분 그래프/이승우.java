import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class 이분그래프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());

            List<Integer>[] list = new ArrayList[V + 1];

            for (int v = 1; v <= V; v++) {
                list[v] = new ArrayList<>();
            }

            for (int e = 1; e <= E; e++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());

                list[u].add(v);
                list[v].add(u);
            }

            int[] colors = new int[V + 1];
            boolean isBipartite = true;

            for (int i = 1; i <= V; i++) {
                if (colors[i] == 0) {
                    Deque<Integer> queue = new ArrayDeque<>();
                    queue.add(i);
                    colors[i] = 1;

                    while (!queue.isEmpty()) {
                        int curr = queue.poll();
                        for (int next : list[curr]) {
                            if (colors[next] == 0) {
                                colors[next] = 3 - colors[curr];
                                queue.add(next);
                            } else if (colors[next] == colors[curr]) {
                                isBipartite = false;
                                break;
                            }
                        }
                        if (!isBipartite)
                            break;
                    }
                }
                if (!isBipartite)
                    break;
            }

            sb.append(isBipartite ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }
}