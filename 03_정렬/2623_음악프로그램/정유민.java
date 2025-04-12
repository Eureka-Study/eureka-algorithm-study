import java.io.*;
import java.util.*;

public class 음악프로그램 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 가수 수
        int M = Integer.parseInt(st.nextToken()); // 보조 PD 수

        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[N + 1]; // 진입 차수 배열

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 구성 및 진입 차수 계산
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());

            for (int j = 1; j < count; j++) {
                int curr = Integer.parseInt(st.nextToken());
                graph.get(prev).add(curr);
                indegree[curr]++;
                prev = curr;
            }
        }

        // 위상 정렬 수행
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        // 진입 차수가 0인 노드 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            for (int next : graph.get(current)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // 결과 출력
        if (result.size() != N) {
            System.out.println(0); // 사이클 존재
        } else {
            for (int singer : result) {
                System.out.println(singer);
            }
        }
    }
}
