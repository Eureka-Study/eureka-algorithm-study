//언어 : JAVA , (성공/실패) : 0/1 , 메모리 : 18108 KB , 시간 : 124 ms
// 점화식
// dp[N] = min(dp[N-1] + 1, dp[N/2] + 1(if N mod 2 == 0), dp[N/3] + 1(if N mod 3 == 0))
import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) {
                dp[i] = min(dp[i], dp[i / 2] + 1);
            }
            if (i % 3 == 0) {
                dp[i] = min(dp[i], dp[i / 3] + 1);
            }
        }
        System.out.print(dp[n]);
    }
    static int min(int x, int y) {
        return x < y ? x : y;
    }
}