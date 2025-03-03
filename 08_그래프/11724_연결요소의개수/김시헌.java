// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 145076 KB , 시간 : 536 ms

import java.io.*;
import java.util.*;

public class Silver_11724 {     // 연결 요소의 개수
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;     // 노드별 연결 노드 저장
    static boolean[] visit;     // 방문 여부
    static int count = 0;       // 연결요소 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        visit = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());    // 2차원 arraylist 초기화
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);    // u -> v
            graph.get(v).add(u);    // v -> u
        }

        for (int i = 1; i <= N ; i++) {     // 노드 1부터 N까지
            if (!visit[i]) {    // 노드 i 가 아직 방문 전이라면
                bfs(i);         // bfs 돌기
                count++;        // bfs 한번 다 돌고나면 카운트 1 추가
            }
        }

        System.out.println(count);
    }

    static void bfs(int startNode) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(startNode);
        visit[startNode] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < graph.get(node).size(); i++) {
                int nextNode = graph.get(node).get(i);
                if (!visit[nextNode]) {
                    queue.offer(nextNode);
                    visit[nextNode] = true;
                }
            }
        }
    }
}
