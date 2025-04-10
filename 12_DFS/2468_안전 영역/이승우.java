
// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 121372KB , 시간 : 316ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 안전영역 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] list = new int[N][N];

        Set<Integer> rainZone = new HashSet<>(); // 중복된 비 지역을 제거하기위해 hashSet 활용

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < N; j++) {
                int rain = Integer.parseInt(st.nextToken());
                list[i][j] = rain;

                rainZone.add(rain);
            }
        }

        rainZone.add(0); // 조건에 비가 안내릴 수도 있다고 조건이 명시되어있다.

        int answer = 0;

        for (int rain : rainZone) {
            boolean[][] flooding = new boolean[N][N]; // 침수 지역을 매번 체크해야하기 때문에 매번 초기화해서 최대 안전 지역 체크

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (list[i][j] > rain)
                        flooding[i][j] = true; // rain보다 이상이어야 안전 지역
                }
            }

            answer = Math.max(answer, countSafeAreas(flooding)); // 최대 값 체크
        }

        System.out.println(answer);

    }

    static int countSafeAreas(boolean[][] flooding) {
        int N = flooding.length;
        boolean[][] visited = new boolean[N][N];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (flooding[i][j] && !visited[i][j]) { // 확인한적 없고 침수가 안되어있어야 함
                    dfs(flooding, visited, i, j);
                    count++; // 지역 탐색 후 그 구역은 이제 침수가 아닌 지역 하나로 인식
                }
            }
        }

        return count;
    }

    static void dfs(boolean[][] flooding, boolean[][] visited, int x, int y) { // 재귀로 현 지점에서 침수가 아닌 곳을 탐색, 그 구역이 침수가 아닌
                                                                               // 지역 1이 되는 것
        int N = flooding.length;
        visited[x][y] = true;

        int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for (int[] dir : d) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (flooding[nx][ny] && !visited[nx][ny]) {
                    dfs(flooding, visited, nx, ny);
                }
            }
        }
    }
}
