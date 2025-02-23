// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16264 KB , 시간 : 148 ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, K;
    static int[][] map;
    static boolean[][] visit;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visit = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = 1; // 배추 위치 1 표시
            }

            bw.write(bfs() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs() {
        int count = 0;

        for (int i = 0; i < N; i++) { // map 돌면서 1 에서 bfs 수행
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    if (visit[i][j]) continue;

                    Deque<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    visit[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] n = queue.poll();

                        for (int d = 0; d < 4; d++) {
                            int ny = n[0] + dy[d];
                            int nx = n[1] + dx[d];

                            if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] != 1) continue;
                            queue.offer(new int[]{ny, nx});
                            visit[ny][nx] = true;
                        }
                    }
                    count++;
                }
            }
        }

        return count;
    }
}
