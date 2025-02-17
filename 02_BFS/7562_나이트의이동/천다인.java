//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 72176 KB , 시간 : 260 ms
import java.io.*;
import java.util.*;

class Main {
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int l = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());

            sb.append(bfs(l, startX, startY, targetX, targetY)).append("\n");
        }
        System.out.print(sb);
    }

    static int bfs(int l, int startX, int startY, int targetX, int targetY) {
        if (startX == targetX && startY == targetY) return 0;

        boolean[][] visited = new boolean[l][l];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], moves = cur[2];

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < l && ny < l && !visited[nx][ny]) {
                    if (nx == targetX && ny == targetY) return moves + 1;

                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, moves + 1});
                }
            }
        }
        return -1;
    }
}