// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14404 KB , 시간 : 112ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 정유민_방법2 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map;
    static int n, m;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = s.charAt(j) - '0';
            }
        }
        bfs(1, 1);
        System.out.println(map[n][m]);
    }

    private static void bfs(int y, int x) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(y, x));
        visit[y][x]= true;

        while (!queue.isEmpty()) {
            Node polled = queue.poll();

            for (int i = 0; i < 4; i++) {

                int ny = polled.y + dy[i];
                int nx = polled.x + dx[i];


                if (ny < 1 || ny > n || nx < 1 || nx > m || visit[ny][nx] || map[ny][nx]==0)
                    continue; // 벽이거나, 방문했다면 X

                map[ny][nx]= map[polled.y][polled.x] + 1; // 누적 거리 계산

                visit[ny][nx]= true;
                queue.offer(new Node(ny, nx));
            }
        }
    }
    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
