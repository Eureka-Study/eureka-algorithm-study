// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 142048 KB , 시간 : 608 ms
import java.io.*;
import java.util.*;
class Main {
    static int n, m; // 정점(n)과 간선(m)의 개수
    static ArrayList<Integer>[] graph; // 인접 리스트를 이용한 그래프 저장
    static boolean[] visited; // 방문 여부를 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점 개수 입력
        m = Integer.parseInt(st.nextToken()); // 간선 개수 입력
        graph = new ArrayList[n + 1]; // 정점 번호가 1부터 시작하므로 (n+1) 크기 할당

        // 그래프 초기화 (각 정점에 대한 리스트 생성)
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 정점 u
            int v = Integer.parseInt(st.nextToken()); // 정점 v
            graph[u].add(v); // 양방향 그래프이므로 u -> v 연결
            graph[v].add(u); // v -> u 연결
        }

        visited = new boolean[n + 1]; // 방문 배열 초기화
        int comp = 0; // 연결 요소 개수

        // 모든 정점을 탐색하며 DFS 실행
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) { // 방문하지 않은 정점이면 새로운 연결 요소 발견
                dfs(i); // DFS로 연결된 모든 정점 방문
                comp++; // 연결 요소 개수 증가
            }
        }

        System.out.print(comp); // 최종 연결 요소 개수 출력
    }

    // DFS를 이용한 연결 요소 탐색
    static void dfs(int node) {
        visited[node] = true; // 현재 노드 방문 처리
        for (int next : graph[node]) { // 현재 노드와 연결된 모든 노드 탐색
            if (!visited[next]) { // 방문하지 않은 노드라면
                dfs(next); // 재귀적으로 탐색 진행
            }
        }
    }
}