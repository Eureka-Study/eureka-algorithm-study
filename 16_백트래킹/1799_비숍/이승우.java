import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간 초과
 */
public class 비숍 {
    static int N;
    static int[][] chess;
    static int answer;
    static int[][] d = new int[][] { // 비숍은 대각선으로 움직인다.
            { 1, 1 },
            { -1, 1 },
            { -1, -1 },
            { 1, -1 }
    };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        chess = new int[N][N]; // 체스판에 비숍을 두는 칸을 정의
        boolean[][] isSelected = new boolean[N][N]; // 비숍을 둔 위치를 파악

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int count = 0;
            while (st.hasMoreTokens()) {
                chess[n][count++] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(chessCheck(0, 0, 0, isSelected));

    }

    public static int chessCheck(int y, int x, int count, boolean[][] isSelected) {
        int newCount = count;
        for (int i = y; i < N; i++) {
            for (int j = (i == y ? x : 0); j < N; j++) {
                if (chess[i][j] == 1 && check(i, j, isSelected)) { // 둘 수 있고 이미 둔 비숍이 현 비숍을 잡을 수 없는지 파악
                    isSelected[i][j] = true; // 잡을 수 없으므로 일단 둬본다.
                    newCount = Math.max(newCount, chessCheck(i, j + 1, count + 1, isSelected)); // 둔 경우와 안 둔 경우 중 누가 더
                                                                                                // 큰지 파악
                    isSelected[i][j] = false; // 이 비숍을 둔 경우를 모두 파악을 했으므로 안둔 경우도 파악
                }
            }
        }
        return newCount;
    }

    public static boolean check(int y, int x, boolean[][] isSelected) { // 현 위치에 비숍을 둘 때 이미 둔 비숍이 잡을 수 없는지 파악
        for (int[] xy : d) { // 비숍을 움직여본다.
            int dx = x, dy = y;
            while (dx >= 0 && dx < N &&
                    dy >= 0 && dy < N) { // 체스판의 크기를 넘어갈 때까지 움직여보기
                dx += xy[1];
                dy += xy[0];

                if (dx >= 0 && dx < N &&
                        dy >= 0 && dy < N &&
                        isSelected[dy][dx]) // 크기가 넘어갔는지 파악하고 이동한 위치에 비숍이 있으면 불가능하므로 바로 종료
                    return false;
            }
        }
        return true; // 여기에 도달하면 모든 이동한 위치에 비숍이 없다는 것
    }
}
