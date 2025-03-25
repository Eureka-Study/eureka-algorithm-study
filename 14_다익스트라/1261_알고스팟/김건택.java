import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;  // 최댓값으로 초기화
            }
        }

        bfs();
        System.out.println(dist[N - 1][M - 1]);  // 목적지까지 최소 벽 부수기 횟수 출력
    }

    static void bfs() {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    int newDist = dist[x][y] + map[nx][ny];

                    if (newDist < dist[nx][ny]) {
                        dist[nx][ny] = newDist;
                        if (map[nx][ny] == 0) {
                            deque.offerFirst(new int[]{nx, ny});  // 벽 없음 → 앞에 추가
                        } else {
                            deque.offerLast(new int[]{nx, ny});   // 벽 있음 → 뒤에 추가
                        }
                    }
                }
            }
        }
    }
}
