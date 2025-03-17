//언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 45696 KB , 시간 : 388 ms
import java.io.*;
import java.util.*;
class Main {
    static int n, m;
    static int[][] canvas; // 도화지 저장
    static boolean[][] visited; // 방문 여부를 체크
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        canvas = new int[n][m];
        visited = new boolean[n][m];

        // 도화지 정보 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                canvas[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0; // 그림의 개수
        int maxArea = 0; // 가장 큰 그림의 넓이

        // 도화지를 순회하면서 그림 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 1(그림 부분)이고 아직 방문하지 않은 경우 새로운 그림 발견
                if (canvas[i][j] == 1 && !visited[i][j]) {
                    count++; // 그림 개수 증가
                    maxArea = Math.max(maxArea, bfs(i, j)); // BFS로 그림 넓이 계산 후 최대값 갱신
                }
            }
        }
        System.out.println(count);
        System.out.println(maxArea);
    }
    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int area = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            // 상하좌우 이동하며 연결된 1 찾기
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위를 벗어나지 않고, 방문하지 않았으며, 1인 경우만 이동
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && canvas[nx][ny] == 1) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    area++;
                }
            }
        }
        return area; // 최종 그림의 넓이 반환
    }
}