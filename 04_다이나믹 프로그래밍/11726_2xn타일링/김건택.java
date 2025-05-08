// 언어 : JAVA , (성공/실패) : 1/6 , 메모리 : 14284 KB , 시간 : 108 ms

package boj;

import java.io.*;

public class Silver3_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 2];

        dp[1] = 1;
        dp[2] = 2;

        if (n > 2) {
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i-2] + dp[i-1]) % 10007;
            }
        }

        System.out.println(dp[n]);
    }
}
