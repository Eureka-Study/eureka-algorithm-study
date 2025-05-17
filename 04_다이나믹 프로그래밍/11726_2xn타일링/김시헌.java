// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 11516 KB , 시간 : 64 ms

import java.io.*;
import java.util.*;

public class Main {     // 2xn 타일링
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+2];
        dp[1] = 1;
        dp[2] = 2;

        if (N >= 3) {
            for (int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;    // 이 문제 핵심 10007의 나머지..
            }
        }

        System.out.println(dp[N]);
    }
}
