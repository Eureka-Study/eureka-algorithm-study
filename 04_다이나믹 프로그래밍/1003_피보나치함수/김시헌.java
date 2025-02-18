// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 11556 KB , 시간 : 64 ms

import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());    // 테케 개수

        // 테케별 구현
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            dp = new int[N+2][2];   // N+1이 아니라 N+2로 초기화해줘야 N=0 일때도 인덱스오류 안남

            dp[0][0] = 1; dp[0][1] = 0;     // dp[0] = {1,0}
            dp[1][1] = 0; dp[1][1] = 1;     // dp[1] = {1,1}

            for (int j = 2; j <= N; j++) {
                dp[j][0] = dp[j-1][0] + dp[j-2][0];
                dp[j][1] = dp[j-1][1] + dp[j-2][1];
            }

            // 출력
            bw.write(String.valueOf(dp[N][0] + " " + dp[N][1]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
