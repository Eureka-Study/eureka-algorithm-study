//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 260012 KB , 시간 : 1648 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 벽부수고이동하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[][] d = new int[][] {
                { 1, 0 },
                { 0, 1 },
                { -1, 0 },
                { 0, -1 }
        };

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int[][] list = new int[N + 1][M + 1];

        for (int n = 1; n <= N; n++) {
            String s = br.readLine();
            for (int m = 1; m <= M; m++) {
                list[n][m] = s.charAt(m - 1) - '0';
            }
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2) -> {
            if (n1[3] == n2[3])
                return n1[2] - n2[2];
            return n1[3] - n2[3];
        });
        boolean[][][] visited = new boolean[2][N + 1][M + 1];
        int min = Integer.MAX_VALUE;

        queue.offer(new int[] { 1, 1, 1, 0 });

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            if (visited[q[3]][q[0]][q[1]])
                continue;
            if (q[0] == N && q[1] == M) {
                min = Math.min(q[2], min);
                continue;
            }
            visited[q[3]][q[0]][q[1]] = true;

            for (int[] yx : d) {
                int[] newPoint = new int[] { q[0] + yx[0], q[1] + yx[1], q[2] + 1, q[3] };

                if (newPoint[0] > 0 && newPoint[0] <= N &&
                        newPoint[1] > 0 && newPoint[1] <= M &&
                        !(newPoint[3] == 1 && list[newPoint[0]][newPoint[1]] == 1)) {
                    newPoint[3] = newPoint[3] == 0 ? list[newPoint[0]][newPoint[1]] : 1;
                    if (!visited[newPoint[3]][newPoint[0]][newPoint[1]]) {
                        queue.offer(newPoint);
                    }
                }
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
