// 언어 : JAVA , (성공/실패) : 0/1 , 메모리 : 16120 KB , 시간 : 152 ms
import java.io.*;
import java.util.*;

class Main {
    static int m, n, k;
    static int[][] field;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1}; // 상하좌우 이동
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); // 가로 길이
            n = Integer.parseInt(st.nextToken()); // 세로 길이
            k = Integer.parseInt(st.nextToken()); // 배추 개수

            field = new int[n][m];
            visited = new boolean[n][m];

            // 배추 위치 입력
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                field[y][x] = 1; // 배추가 있는 위치
            }

            int count = 0; // 필요한 지렁이 개수

            // 모든 위치 탐색
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (field[i][j] == 1 && !visited[i][j]) {
                        bfs(j, i);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }

    // BFS를 이용한 영역 탐색
    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int curX = pos[0];
            int curY = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    if (field[ny][nx] == 1 && !visited[ny][nx]) {
                        queue.offer(new int[]{nx, ny});
                        visited[ny][nx] = true;
                    }
                }
            }
        }
    }
}