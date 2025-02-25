// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15776 KB , 시간 : 100 ms

import java.io.*;
import java.util.*;

// DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());

        int[] dp = new int[X + 1];  // 시간제한이 0.15초 이므로, 메모이제이션으로 풀이
        dp[0] = dp[1] = 0;          // 0과 1은 연산이 불가한 경우이니 0으로 초기화

        for (int i = 2; i < X + 1; i++) {
            dp[i] = dp[i-1] + 1;    // 기본조건: 과거에 "1을 뺀다" 시행했다
            if (i % 2 == 0) {       // 2로 나누어 떨어진다면, "2로 나눈다" 시행
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);     // 최솟값으로 적용
            }
            if (i % 3 == 0) {       // 2로 나누어 떨어진다면, "2로 나눈다" 시행
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);     // 최솟값으로 적용
            }
        }

        bw.write(String.valueOf(dp[X]));
        bw.flush();
        bw.close();

    }
}