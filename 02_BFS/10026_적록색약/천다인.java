// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 17324 KB , 시간 : 136 ms
import java.io.*;
import java.util.*;

class Main {
    static int n; // 그리드 크기 (n x n)
    static char[][] grid; // 색깔 정보가 저장된 2차원 배열
    static boolean[][] visited; // 방문 여부를 저장하는 배열
    static final int[] dx = {0, 0, -1, 1}; // 이동 방향 (상, 하, 좌, 우)
    static final int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new char[n][n];

        // 입력값을 2차원 배열로 저장
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // 일반인의 경우 구역 개수 계산
        visited = new boolean[n][n]; // 방문 배열 초기화
        int normalArea = countRegions(false);

        // 적록색약의 경우 구역 개수 계산
        visited = new boolean[n][n]; // 다시 방문 배열 초기화
        int colorBlindArea = countRegions(true);

        // 결과 출력
        System.out.println(normalArea + " " + colorBlindArea);
    }

    // 구역 개수를 세는 함수
    static int countRegions(boolean isColorBlind) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) { // 방문하지 않은 지점이라면
                    dfs(i, j, grid[i][j], isColorBlind); // 해당 구역 탐색
                    count++; // 하나의 구역을 찾았으므로 개수 증가
                }
            }
        }
        return count;
    }

    // DFS를 이용한 영역 탐색
    static void dfs(int x, int y, char color, boolean isColorBlind) {
        visited[x][y] = true; // 현재 위치 방문 처리

        // 4방향 탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 그리드 범위를 벗어나거나 이미 방문한 경우 넘어감
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

            char nextColor = grid[nx][ny]; // 다음 탐색할 위치의 색상

            if (isColorBlind) { // 적록색약의 경우
                // R과 G를 같은 색으로 취급하여 연결된 구역 탐색
                if ((color == 'R' || color == 'G') && (nextColor == 'R' || nextColor == 'G')) {
                    dfs(nx, ny, color, isColorBlind);
                }
                // 동일한 색상일 경우 탐색
                else if (nextColor == color) {
                    dfs(nx, ny, color, isColorBlind);
                }
            } else { // 일반인의 경우
                // 동일한 색상일 경우 탐색
                if (nextColor == color) {
                    dfs(nx, ny, color, isColorBlind);
                }
            }
        }
    }
}