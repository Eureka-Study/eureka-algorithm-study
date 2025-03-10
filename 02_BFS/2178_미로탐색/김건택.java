// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14680 KB , 시간 : 112 ms
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int min = Integer.MAX_VALUE;

    static int[][] map;
    static boolean[][] visit;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        visit = new boolean[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                map[i][j] = chars[j - 1] - '0';
            }
        }

//        dfs(1, 1, 1);
        bfs(1, 1, 1);

        System.out.println(min);
    }

    static void dfs(int x, int y, int count) {
        if (x == N && y == M) {
            min = Math.min(min, count);
        }

        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (nx >= 1 && ny >= 1 && nx <= N && ny <= M && map[nx][ny] == 1 && !visit[nx][ny]) {
                dfs(nx, ny, count + 1);
            }
        }

        // 재탐색을 위해 방문했던 곳을 미방문 처리
        visit[x][y] = false;
    }

    static void bfs(int x, int y, int count) {
        visit[x][y] = true;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y, count});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == N && cur[1] == M) {
                min = cur[2];
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // map 의 범위를 벗어나지 않고, 통로이면서 방문하지 않았다면
                if (nx >= 1 && ny >= 1 && nx <= N && ny <= M && map[nx][ny] == 1 && !visit[nx][ny]) {
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                    visit[nx][ny] = true;
                }
            }
        }
    }
}
