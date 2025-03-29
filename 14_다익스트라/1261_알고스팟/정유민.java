//언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 12116 KB , 시간 : 72 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 정유민 {
    static int n, m;
    static int[][] map;
    static int[][] minCost; // 최소 벽 개수 누적 저장
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Node {
        int y, x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        minCost = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
                minCost[i][j] = Integer.MAX_VALUE;
            }
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(0, 0));
        minCost[0][0] = 0;

        while (!deque.isEmpty()) {
            Node now = deque.poll();
            int y = now.y;
            int x = now.x;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                //누적 비용에 새롭게 간 곳의 비용 더하고
                int cost = minCost[y][x] + map[ny][nx];

                // 비용이 더 작으면 새롭게 갱신하면 됨
                if (cost < minCost[ny][nx]) {
                    minCost[ny][nx] = cost;
                    if (map[ny][nx] == 1) // 후순위로 미뤄서 비용이 0인것부터 꺼낼 수 있도록 한다
                        deque.offerLast(new Node(ny, nx));
                    else
                        deque.offerFirst(new Node(ny, nx));
                }
            }
        }
        System.out.println(minCost[n - 1][m - 1]);
    }
}
