// 언어 : JAVA , (성공/실패) : 0/1 , 메모리 :  , 시간 : 

import java.io.*;
import java.util.*;

public class 텀프로젝트 {

    static List<Integer>[] list;
    static boolean[] check;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            list = new ArrayList[n + 1];
            check = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int next = Integer.parseInt(st.nextToken());
                list[i].add(next);
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (!check[i] && bfs(i) == 0) { // 아직 방문하지 않은 정점에서 사이클 탐색
                    cnt++; // 사이클 발견 x -> 카운트 증가
                }
            }

            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }

    static int bfs(int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        check[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int pos = queue.poll();

            for (int next : list[pos]) {
                if (start == next) { // 사이클이 발견되면 1 반환
                    return 1;
                }
                if (!check[next]) { // 방문하지 않은 정점이면
                    check[next] = true; // 방문 표시
                    queue.offer(next);
                }
            }
        }
        return 0;
    }
}
