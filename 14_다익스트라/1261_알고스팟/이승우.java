//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14620 KB , 시간 : 116 ms
import java.io.*;
import java.util.*;

public class 알고스팟 {
    public static void main(String[] args) throws IOException {
        final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] room = new int[N][M];
        int[][] dist = new int[N][M];

        for (int y = 0; y < N; y++) {
            char[] line = br.readLine().toCharArray();
            Arrays.fill(dist[y], Integer.MAX_VALUE); // 초기 값 max로 지정
            for (int x = 0; x < M; x++) {
                room[y][x] = line[x] - '0';
            }
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, 0, 0});
        dist[0][0] = 0; // 0, 0은 0으로 만들기

        while (!deque.isEmpty()) {
            int[] now = deque.pollFirst();
            int cost = now[0];
            int x = now[1];
            int y = now[2];

            if (x == M - 1 && y == N - 1) { // 목적지에 도달했을 경우
                System.out.println(cost);
                return;
            }

            for (int[] d : directions) { // 이동경로 탐색
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < M && ny < N) { // 정상적인 경로인지 파악
                    int nextCost = cost + room[ny][nx];
                    if (dist[ny][nx] > nextCost) { // 지금 가는 루트가 더 적게 벽을 부수는지 파악(벽을 더 많이 부술 경우 굳이 더 진행할 필요 없음)
                        dist[ny][nx] = nextCost;
                        if (room[ny][nx] == 1) { // 만약 벽일 경우 나중에 빼기 위해 last에 넣음
                            deque.offerLast(new int[]{nextCost, nx, ny});
                        } else { // 만약 길일 경우 우선적으로 빼기 위해 first
                            deque.offerFirst(new int[]{nextCost, nx, ny});
                        }
                    }
                }
            }
        }
    }
}
