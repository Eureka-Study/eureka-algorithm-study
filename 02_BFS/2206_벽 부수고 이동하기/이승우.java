//언어 : JAVA , (성공/실패) : 1/6 , 메모리 : 260012 KB , 시간 : 1648 ms

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
                list[n][m] = s.charAt(m - 1) - '0'; // 벽인지 길인지
            }
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2) -> {
            if (n1[3] == n2[3])
                return n1[2] - n2[2];
            return n1[3] - n2[3];
        }); // 우선순위 큐 벽이 없는 구간 부터 탐색을 하고 같을 경우 이동한 거리가 짧은 순으로 탐색
        boolean[][][] visited = new boolean[2][N + 1][M + 1]; // 벽일 때랑 벽이 아닐 때랑 구분하여 탐색을 한다.
        int min = Integer.MAX_VALUE;

        queue.offer(new int[] { 1, 1, 1, 0 }); // 초기는 1, 1 이며 처음 위치도 이동 거리에 포함이므로 1, 벽을 부순 적이 없기에 0

        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            if (visited[q[3]][q[0]][q[1]])
                continue;
            if (q[0] == N && q[1] == M) { // 최종 위치에 도달했을 경우
                min = Math.min(q[2], min);
                continue; // 다른 도달 가능성이 있기 때문에 true로 바뀌기 전에 넘기기
            }
            visited[q[3]][q[0]][q[1]] = true;

            for (int[] yx : d) {
                int[] newPoint = new int[] { q[0] + yx[0], q[1] + yx[1], q[2] + 1, q[3] };

                if (newPoint[0] > 0 && newPoint[0] <= N && // 배열 안에 존재해야함
                        newPoint[1] > 0 && newPoint[1] <= M && // 배열 안에 존재해야함
                        !(newPoint[3] == 1 && list[newPoint[0]][newPoint[1]] == 1)) { // 벽을 이미 부셨을 경우 벽을 만나면 무조건 못지나간다.
                    newPoint[3] = newPoint[3] == 0 ? list[newPoint[0]][newPoint[1]] : 1; // 벽을 부셨는지 체크
                    if (!visited[newPoint[3]][newPoint[0]][newPoint[1]]) { // 아직 안지나갔다면
                        queue.offer(newPoint);
                    }
                }
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min); // int 최대값일 경우 n, m에 도달한 적이 없다는 의미이므로 -1이다.
    }
}
