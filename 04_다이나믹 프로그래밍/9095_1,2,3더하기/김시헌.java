// 언어 : JAVA , (성공/실패) : 2/0 , 메모리 : 11576 KB , 시간 : 60 ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int[] inputList = new int[T];
        for (int i = 0; i < T; i++) {
            inputList[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < T; i++) {
            int num = inputList[i];     // 이번 테케의 n

            int[] dp = new int[11];     // dp 배열, 초기값
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            if (num >= 4) {
                for (int n = 4; n <= num; n++) {
                    dp[n] = dp[n-1] + dp[n-2] + dp[n-3];    // 점화식
                }
            }

            bw.write(String.valueOf(dp[num]));
            bw.newLine();
        }
        bw.close();
        br.close();
    }
}

/*
// 예전에 풀었던 코드
for (int num : inputList) {
    int dp[] = new int[num + 1];

    dp[0] = 1;
    dp[1] = 1;

    if (num == 2) {
        dp[2] = 2;
    } else if (num >= 3) {
        dp[2] = 2;
        for (int i = 3; i <= num; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
    }
    bw.write(String.valueOf(dp[num]));
    bw.flush();
    bw.newLine();
}
bw.close();
 */