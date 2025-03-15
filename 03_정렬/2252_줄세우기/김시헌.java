// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 44432 KB , 시간 : 368 ms

import java.io.*;
import java.util.*;

public class Main {    // 줄 세우기
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph; // 각 노드별로 걔보다 큰노드들 정보 저장
    static int[] degree;    // 각 노드의 차수를 담는 배열 (해당노드보다 작은노드 개수를 의미)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        degree = new int[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int smaller = Integer.parseInt(st.nextToken());
            int bigger = Integer.parseInt(st.nextToken());

            degree[bigger]++;   // 큰노드는 차수 1개 증가시킴
            graph.get(smaller).add(bigger); // 작은노드는 저장소에 큰노드 추가시킴
        }

        StringBuilder sb = new StringBuilder();

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {   // 차수가 0인 노드들
                q.offer(i);         // 큐에 넣기
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();    // 큐에서 노드 꺼내고
            sb.append(node).append(" ");    // 정답에 쓰기

            for (int i = 0; i < graph.get(node).size(); i++) {  // 그 노드보다 큰노드들
                if (degree[graph.get(node).get(i)] == 1) {      // 해당 큰노드가 차수 1이면
                    q.offer(graph.get(node).get(i));            // 큐에 넣기
                } else {
                    degree[graph.get(node).get(i)]--;           // 차수 1보다 크면 차수 1 줄이기
                }
            }
        }

        System.out.println(sb);
    }

}
