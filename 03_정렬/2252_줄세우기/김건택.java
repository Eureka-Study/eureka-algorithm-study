import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 학생 수 (노드 개수)
        int M = sc.nextInt(); // 비교한 횟수 (간선 개수)

        List<Integer>[] graph = new ArrayList[N + 1]; // 인접 리스트
        int[] inDegree = new int[N + 1]; // 진입 차수 배열

        // 그래프 초기화
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 입력 받기
        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            graph[A].add(B);
            inDegree[B]++; // B의 진입 차수 증가
        }

        // 위상 정렬 수행 (BFS)
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 노드를 큐에 삽입
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 위상 정렬 실행
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.append(current).append(" ");

            // 현재 노드와 연결된 노드들의 진입 차수를 감소
            for (int next : graph[current]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        // 결과 출력
        System.out.println(result);
    }
}
