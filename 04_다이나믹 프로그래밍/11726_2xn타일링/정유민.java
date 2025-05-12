// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 12888 KB , 시간 : 92 ms

import java.util.Scanner;

public class twoXn타일링 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[1001];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%10007;
        }
        System.out.println(dp[n]);
    }
}
