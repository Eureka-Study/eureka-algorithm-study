import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] graph; // 그래프 표현할 인접리스트
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 실패 코드
//        StringTokenizer st;
//        for (int i = 2; i <= N; i++) {
//            st = new StringTokenizer(br.readLine());
//            int parent = Integer.parseInt(st.nextToken());
//            int child = Integer.parseInt(st.nextToken());
//
//            tree[child] = parent;
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 2; i <= N; i++) {
//            sb.append(tree[i]).append("\n");
//        }
//
//        System.out.println(sb);

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();


        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b); // 무방향 그래프이므로 양방향으로 저장
            graph[b].add(a);
        }

        parent = new int[N + 1];
        visited = new boolean[N + 1];

        dfs(1);

        for (int i = 2; i <= N; i++) System.out.println(parent[i]);
    }

    static void dfs(int node) {
        visited[node] = true;

        for (int next : graph[node]) {
            if (!visited[next]) {
                parent[next] = node;
                dfs(next);
            }
        }
    }
}
