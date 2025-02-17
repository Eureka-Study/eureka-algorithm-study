// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 41892 KB , 시간 : 268 ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 상좌 - 상우 - 하좌 - 하우 - 좌상 - 좌하 - 우상- 우하 순서
    static int[] dy = { -2, -2, 2, 2, -1, 1, -1, 1 };
    static int[] dx = { -1, 1, -1, 1, -2, -2, 2, 2 };

    static boolean[][] visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            visit = new boolean[n][n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            int answer = bfs(startY, startX, endY, endX);

            System.out.println(answer);
        }
    }

    static int bfs(int y, int x, int endY, int endX) {
        Queue<Knight> queue = new ArrayDeque<>();
        queue.offer(new Knight(y, x, 0));
        visit[y][x] = true;

        while (!queue.isEmpty()) {
            Knight knight = queue.poll();

            if (knight.y == endY && knight.x == endX) {
                return knight.moveCnt;
            }

//            System.out.println(knight.y + " " + knight.x);

            // 꺼낸 knight 객체로부터 갈 수 있는
            for (int d = 0; d < 8; d++) {
                int ny = knight.y + dy[d];
                int nx = knight.x + dx[d];
                int moveCount = knight.moveCnt;

                if (ny < 0 || nx < 0 || ny >= visit.length || nx >= visit.length || visit[ny][nx]) continue;

                queue.offer(new Knight(ny, nx, moveCount + 1));
                visit[ny][nx] = true;
            }
        }
        return -1;
    }
    static class Knight {
        int y, x;
        int moveCnt;

        Knight (int y, int x, int moveCnt) {
            this.y = y;
            this.x = x;
            this.moveCnt = moveCnt;
        }

        @Override
        public String toString() {
            return "Knight [y=" + y + ", x=" + x + "]";
        }
    }
}