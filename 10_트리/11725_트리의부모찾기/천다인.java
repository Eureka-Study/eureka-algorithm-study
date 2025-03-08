//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 67380 KB , 시간 : 548 ms
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        // 트리를 인접 리스트로 표현하기 위해 리스트 초기화
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리의 연결 상태를 리스트에 저장
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        int[] parents = new int[n + 1];  // 각 노드의 부모 정보를 저장할 배열
        boolean[] visited = new boolean[n + 1];  // 노드 방문 여부를 체크할 배열

        // BFS를 수행하기 위해 큐를 사용하여 루트 노드(1)부터 시작
        Queue<Integer> q = new LinkedList<>();
        q.add(1);  // 루트 노드를 큐에 삽입
        visited[1] = true;  // 루트 노드를 방문 처리

        // BFS를 통해 각 노드의 부모 노드를 탐색
        while (!q.isEmpty()) {
            int current = q.poll();  // 현재 방문할 노드를 큐에서 가져옴

            // 현재 노드와 연결된 노드 중 방문하지 않은 노드를 탐색
            for (int child : tree.get(current)) {
                if (!visited[child]) {
                    visited[child] = true;  // 방문 처리
                    parents[child] = current;  // 현재 노드를 부모 노드로 설정
                    q.add(child);  // 자식 노드를 큐에 추가
                }
            }
        }

        // 2번 노드부터 각 노드의 부모 노드를 sb에 추가
        for (int i = 2; i <= n; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.print(sb);
    }
}