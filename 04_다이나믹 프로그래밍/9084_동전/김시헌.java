// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11740 KB , 시간 : 68 ms

import java.io.*;
import java.util.*;

public class Main {    // 동전
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] coins;
        int[] dp;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            coins = new int[N];
            for (int j = 0; j < N; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            dp = new int[M+1];

            // 풀이 1 (숫자 중심으로) => 오답
            // int firstCoin = coins[0];
            // for (int j = 0; j < firstCoin; j++) {   // 첫 코인 (ex. 1) 전까진 dp값들 0으로 세팅
            //     dp[j] = 0;
            // }
            // dp[firstCoin] = 1;  // 첫 코인의 dp값은 1 (ex. dp[1] = 1 )
            //
            // if (M > firstCoin) {
            //     for (int j = firstCoin + 1; j <= M; j++) {
            //         dp[j] = 0;
            //         for (int k = 0; k < N; k++) {
            //             int num = j - coins[k];             // ex) dp[n] = dp[n-1] + dp[n-2] => n-1, n-2
            //             if (num >= 0) dp[j] += dp[num];     // ex) n-1 >= 0, n-2 >= 0
            //         }
            //         System.out.println(j + " : dp[j] = " + dp[j]);
            //     }
            // }


            // 풀이 2 (숫자 말고, 코인 관점으로 -> 한 코인 다 돌고, 또 다른 코인 돌고)
            dp[0] = 1;  // 0번째를 1가지로 초기화
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];

                for (int k = 0; k <= M; k++) {  // 숫자 0부터 숫자 M까지
                    if (k + coin <= M){         // 숫자 M 초과 방지용
                        dp[k + coin] += dp[k];  // ex) dp[5] = dp[5] + dp[0]
                    }
                }
            }

            System.out.println(dp[M]);
        }
    }
}
