//언어 : JAVA , (성공/실패) : 0/1 , 메모리 : 47500 KB , 시간 : 452 ms
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int m = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수

        List<Integer>[] graph = new ArrayList[n + 1]; // 인접 리스트 (그래프)
        int[] inDegree = new int[n + 1]; // 진입 차수 배열

        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 입력 및 진입 차수 계산
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 앞에 서야 하는 학생
            int b = Integer.parseInt(st.nextToken()); // 뒤에 서야 하는 학생

            graph[a].add(b); // a → b (a가 b 앞에 서야 함)
            inDegree[b]++;   // b의 진입 차수 증가
        }

        // 위상 정렬 수행
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 노드(학생)부터 큐에 삽입
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // BFS 기반 위상 정렬
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append(" "); // 출력 결과 저장

            for (int next : graph[current]) {
                inDegree[next]--; // 연결된 노드의 진입 차수 감소
                if (inDegree[next] == 0) { // 새롭게 진입 차수가 0이 되면 큐에 추가
                    queue.add(next);
                }
            }
        }

        System.out.print(sb);
    }
}