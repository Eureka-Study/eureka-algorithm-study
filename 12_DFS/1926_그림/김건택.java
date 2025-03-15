// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 50788 KB , 시간 : 420 ms

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int max = 0;
    static int count = 0;
    static int[][] map;
    static boolean[][] visited;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    count++;
                    max = Math.max(bfs(i, j), max);
                }
            }
        }

        bw.write(count + "\n" + max);
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs(int i, int j) {
        int size = 0;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            size++;

            for (int d = 0; d < 4; d++) {
                int x = cur[0] + dx[d];
                int y = cur[1] + dy[d];

                if (x >= 0 && y >= 0 && x < N && y < M && map[x][y] == 1 && !visited[x][y]) {
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return size;
    }
}
