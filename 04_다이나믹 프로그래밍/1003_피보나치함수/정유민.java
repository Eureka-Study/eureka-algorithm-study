package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1003_피보나치함수dp {

    static int[][] dp = new int[41][2];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        dp[0][0] = 1; // => 0을 넣었을 때 0이 1번 호출
        dp[0][1] = 0; // => 0을 넣었을 때 1이 0번 호출
        dp[1][0] = 0; // => 1을 넣었을 때 0이 0번 호출
        dp[1][1] = 1; // => 1을 넣었을 때 1이 1번 호출

        //점화식
        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];  // 0의 개수
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];  // 1의 개수
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
        }
        System.out.print(sb);
    }
}
