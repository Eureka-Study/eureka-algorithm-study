// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14268 KB , 시간 : 104ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int line = Integer.parseInt(br.readLine());

        //인접 리스트 형태. -> 간선 리스트를 만든다. 단방향
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[][] edges = new int[line][2];

        StringTokenizer st;
        for (int i = 0; i < line; i++) { //for-each 문 잘못 사용함.
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        //인접리스트 다 만듦

        //인접 리스트 형태로 그래프를 저장
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());//없으면 새로운 List 넣어줌.
            graph.putIfAbsent(edge[1], new ArrayList<>());//없으면 새로운 List 넣어줌.
            graph.get(edge[0]).add(edge[1]); // 그래프 상 줄 긋기 (양방향)
            graph.get(edge[1]).add(edge[0]); // 그래프 상 줄 긋기 (양방향)
        }

        bfs(graph, 1);//1이 루트 노드인 그래프를 탐색, 아닌 그래프는 제외.


    }

    static void bfs(Map<Integer, List<Integer>> graph, int idx) {
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();//방문 한 곳 체크용
        queue.offer((idx));
        visited.add(idx);
        int cnt = 0;

        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            cnt++; // 연결되면 바이러스 걸리는 거임.

            if (graph.containsKey(curIdx)) {
                for (Integer next : graph.get(curIdx)) {
                    if (!visited.contains(next)) {//방문 한 곳 안가기
                        queue.offer(next);//
                        visited.add(next);//방문지에 올리기
                    }
                }
            }
        }
        System.out.print(cnt-1); //루트 노드 1은 빼야됨.
    }
}