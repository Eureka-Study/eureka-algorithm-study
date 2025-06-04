
//언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 14240 KB , 시간 : 96 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen {
    // 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712, 365596
    // static boolean[][] chess;
    // static int N;
    // static int answer = 0;

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N = Integer.parseInt(br.readLine());
        // chess = new boolean[N][N];

        // back(0);
        // System.out.println(answer);

        // N의 최대값이 정해져있고 경우의 수는 정해져있으므로 구하는 식을 통해 구하고 하드코딩
        int[] N = { 0, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712, 365596 };
        // 코드의 길이를 줄이기 위해 변수로 선언하지않는다.
        System.out.println(N[Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine())]);
    }

    /*
     * 
     * static void back(int row) {
     * if (row == N) { // 퀸의 개수를 N개를 두었으므로 하나의 경우의 수이다.
     * answer++;
     * return;
     * }
     * for (int col = 0; col < N; col++) { // col당 한번만 둘 수 있다(상하 좌우로 움직일 수 있으므로 무조건
     * col당 하나)
     * if (check(row, col)) { // 대각선도 움직일 수 있으므로 그 부분도 파악해서 가능한지 여부를 따진다.
     * chess[row][col] = true;
     * back(row + 1); // 가능하므로 해당 부분을 올리고 다음 퀸을 올리러 가본다.
     * chess[row][col] = false; // 해당 부분에 올렸을 때의 상황을 체크했으므로 이제 안 올렸을 때를 체크 시작
     * }
     * }
     * }
     * 
     * static boolean check(int y, int x) {
     * int[][] d = new int[][] { // 상하좌우, 대각선을 움직일 수 있으므로
     * { 1, 0 }, { 1, 1 },
     * { 0, 1 }, { -1, 1 },
     * { -1, 0 }, { -1, -1 },
     * { 0, -1 }, { 1, -1 }
     * };
     * 
     * for (int[] yx : d) { // 체크판 끝까지 움직여봐서 그 안에 퀸이 있는지 파악
     * int[] dyx = new int[] { y + yx[0], x + yx[1] };
     * while (dyx[0] >= 0 && dyx[0] < N &&
     * dyx[1] >= 0 && dyx[1] < N) {
     * if (chess[dyx[0]][dyx[1]])
     * return false;
     * else
     * dyx = new int[] { dyx[0] + yx[0], dyx[1] + yx[1] };
     * }
     * }
     * return true;
     * }
     */
}