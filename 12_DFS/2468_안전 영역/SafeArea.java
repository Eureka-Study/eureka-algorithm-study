import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정유민 {
    static int n;
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        int maxHeight = 0;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int maxSafeAreas = 1;

        for (int h = 1; h <= maxHeight; h++) {
            visit = new boolean[n + 1][n + 1];
            int safeAreas = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] > h && !visit[i][j]) {
                        dfs(i, j, h);
                        safeAreas++;
                    }
                }
            }

            maxSafeAreas = Math.max(maxSafeAreas, safeAreas);
        }

        System.out.println(maxSafeAreas);
    }

    private static void dfs(int y, int x, int height) {
        visit[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 1 || ny > n || nx < 1 || nx > n || visit[ny][nx] || map[ny][nx] <= height)
                continue;

            dfs(ny, nx, height);
        }
    }
}
