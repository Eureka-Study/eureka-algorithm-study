// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14440 KB , 시간 : 112ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 정유민_방법1 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visit;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = s.charAt(j - 1) - '0';
            }
        }

        System.out.println(bfs(1, 1));
    }

    private static int bfs(int y, int x) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(y, x, 1));
        visit[y][x] = true;

        while (!queue.isEmpty()) {
            Node polled = queue.poll();

            if (polled.y == n && polled.x == m) {
                return polled.cnt; // n, m에 도착하면 최단 거리 반환 !
            }

            for (int i = 0; i < 4; i++) {
                int ny = polled.y + dy[i];
                int nx = polled.x + dx[i];

                if (ny < 1 || ny > n || nx < 1 || nx > m || visit[ny][nx] || map[ny][nx] == 0)
                    continue; // 벽이거나, 방문했다면 X

                queue.offer(new Node(ny, nx, polled.cnt + 1));
                visit[ny][nx] = true;
            }
        }
        return -1; // 여까지 안옴.
    }

    static class Node {
        int y, x, cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
