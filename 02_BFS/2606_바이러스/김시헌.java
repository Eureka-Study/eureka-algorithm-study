// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11852 KB , 시간 : 72 ms

import java.io.*;
import java.util.*;

public class Main {
    static int computer;
    static int couple;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        computer = Integer.parseInt(br.readLine());
        couple = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();    // 2차원 ArrayList 선언
        for (int i = 0; i < computer + 1; i++) {
            graph.add(new ArrayList<Integer>());    // 컴퓨터 개수보다 1개 더 많게 할당해주기
        }
        StringTokenizer st;
        for (int i = 0; i < couple; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);    // a번 컴퓨터 그래프에 b 추가
            graph.get(b).add(a);    // b번 컴퓨터 그래프에 a 추가
        }

        visited = new boolean[computer+1];    // 방문여부 boolean 배열

        bw.write(String.valueOf(bfs(1)));   // bfs에 1번 넣고 돌리기
        bw.flush();
        bw.close();
        br.close();

    }

    static int bfs(int startNode) {
        int count = 0;      // 감염된 컴퓨터수 저장용

        Queue<Integer> queue = new ArrayDeque<>();  // 큐 선언
        queue.offer(startNode);     // 큐에 시작노드 (1번) 넣기
        visited[startNode] = true;  // 시작노드 (1번) 방문처리

        while (!queue.isEmpty()) {      // 큐가 빌때까지
            int node = queue.poll();    // 큐 꼭대기 꺼내서 node 변수에 저장
            for (int i = 0; i < graph.get(node).size(); i++) {
                int nextNode = graph.get(node).get(i);  // 다음노드는 그래프[node] -> nextNode에 저장
                if (!visited[nextNode]) {           // 만약 nextNode가 아직 방문 안했다면
                    visited[nextNode] = true;       // nextNode 방문처리
                    queue.offer(nextNode);          // nextNode 큐에 넣기
                    count++;        // 감염됐으니 count 1개 추가
                }
            }
        }
        return count;
    }
}
