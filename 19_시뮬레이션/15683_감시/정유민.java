import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 감시 {
    static int N, M, min = Integer.MAX_VALUE;
    static int[][] map;
    static List<CCTV> cctvs = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    static class CCTV {
        int y, x, type;

        CCTV(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        dfs(0, map);
        System.out.println(min);
    }

    static void dfs(int depth, int[][] arr) {
        if (depth == cctvs.size()) {
            int count = countBlindSpot(arr);
            min = Math.min(min, count);
            return;
        }

        CCTV cctv = cctvs.get(depth);
        int[][] temp;

        for (int[] dirs : getDirections(cctv.type)) {
            temp = copyMap(arr);
            for (int dir : dirs) {
                watch(temp, cctv.y, cctv.x, dir);
            }
            dfs(depth + 1, temp);
        }
    }

    static List<int[]> getDirections(int type) {
        List<int[]> directions = new ArrayList<>();
        switch (type) {
            case 1:
                for (int d = 0; d < 4; d++) directions.add(new int[]{d});
                break;
            case 2:
                directions.add(new int[]{0, 2});
                directions.add(new int[]{1, 3});
                break;
            case 3:
                directions.add(new int[]{0, 1});
                directions.add(new int[]{1, 2});
                directions.add(new int[]{2, 3});
                directions.add(new int[]{3, 0});
                break;
            case 4:
                directions.add(new int[]{0, 1, 2});
                directions.add(new int[]{1, 2, 3});
                directions.add(new int[]{2, 3, 0});
                directions.add(new int[]{3, 0, 1});
                break;
            case 5:
                directions.add(new int[]{0, 1, 2, 3});
                break;
        }
        return directions;
    }

    static void watch(int[][] arr, int y, int x, int dir) {
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        while (0 <= ny && ny < N && 0 <= nx && nx < M) {
            if (arr[ny][nx] == 6) break;
            if (arr[ny][nx] == 0) arr[ny][nx] = -1;
            ny += dy[dir];
            nx += dx[dir];
        }
    }

    static int[][] copyMap(int[][] arr) {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(arr[i], 0, temp[i], 0, M);
        }
        return temp;
    }

    static int countBlindSpot(int[][] arr) {
        int count = 0;
        for (int[] row : arr) {
            for (int val : row) {
                if (val == 0) count++;
            }
        }
        return count;
    }
}
