// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 38368 KB , 시간 : 288 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int maxHeight;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 2][N + 2];
        visited = new boolean[N + 2][N + 2];


        StringTokenizer st;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                int height = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(height, maxHeight);
                map[i][j] = height;
            }
        }

        int maxSafeAreaCount = 0;

        for (int k = 0; k < maxHeight; k++) {
            visited = new boolean[N + 2][N + 2];
            int safeAreaCount = 0;
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    // map[i][j] 의 지대가 강수량보다 작거나 같을 때, 또는 이미 방문했을 때 continue
                    if (map[i][j] <= k || visited[i][j]) {
                        continue;
                    }
                    bfs(i, j, k);
                    safeAreaCount++;
                }
            }
            maxSafeAreaCount = Math.max(maxSafeAreaCount, safeAreaCount);
        }

        System.out.println(maxSafeAreaCount);
    }

    static void bfs(int x, int y, int rainHeight) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx > 0 && ny > 0 && nx <= N && ny <= N && map[nx][ny] > rainHeight && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

    }
}
