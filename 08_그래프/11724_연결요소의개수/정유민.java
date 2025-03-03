//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 142028 KB , 시간 : 600 ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 정유민 {
    static boolean[] visited;
    static List<Integer>[] graph; // 인접 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 개수

        visited = new boolean[n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 시작 노드
            int v = Integer.parseInt(st.nextToken()); // 끝 노드
            graph[u].add(v);
            graph[v].add(u);
        }

        int components = 0; // 연결 요소 개수

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i);
            }
        }

        System.out.println(components);
    }

    private static void dfs(int node) {
        visited[node] = true;
        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
