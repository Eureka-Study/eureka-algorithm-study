import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정유민 {
    static int n, r, q;
    static List<Integer>[] tree;
    static int[] subtreeSize;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        subtreeSize = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        subtreeSize(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int queryNode = Integer.parseInt(br.readLine());
            sb.append(subtreeSize[queryNode]).append("\n");
        }
        System.out.print(sb);
    }

    private static void subtreeSize(int root) {
        Deque<Integer> queue = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();
        queue.offer(root);
        visited[root] = true;


        while (!queue.isEmpty()) {
            int node = queue.poll();
            stack.push(node);
            for (int child : tree[node]) {
                if (!visited[child]) {
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }

        // 후위순위 방식으로
        while (!stack.isEmpty()) {
            int node = stack.pop();
            subtreeSize[node] = 1;
            for (int child : tree[node]) {
                if (subtreeSize[child] > 0) {
                    subtreeSize[node] += subtreeSize[child];
                }
            }
        }
    }
}
