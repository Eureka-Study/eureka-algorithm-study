import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N + 1]; // 작업별 소요 시간
        int[] inDegree = new int[N + 1]; // 진입 차수 배열
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] dp = new int[N + 1]; // 각 작업을 완료하는 데 걸리는 최소 시간

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 입력 처리
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken()); // 작업 소요 시간
            int preCount = Integer.parseInt(st.nextToken()); // 선행 작업 개수

            for (int j = 0; j < preCount; j++) {
                int preTask = Integer.parseInt(st.nextToken());
                graph.get(preTask).add(i); // 선행 작업 -> 현재 작업
                inDegree[i]++; // 현재 작업의 진입 차수 증가
            }
        }

        // 위상 정렬 (BFS)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) { // 선행 작업이 없는 경우 큐에 삽입
                queue.add(i);
                dp[i] = time[i]; // 초기 작업은 자기 시간 그대로 설정
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                inDegree[next]--; // 진입 차수 감소
                dp[next] = Math.max(dp[next], dp[current] + time[next]); // 최대 시간 계산

                if (inDegree[next] == 0) { // 모든 선행 작업이 완료되었으면 큐에 삽입
                    queue.add(next);
                }
            }
        }

        // 전체 작업 완료까지 걸리는 최대 시간 출력
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
