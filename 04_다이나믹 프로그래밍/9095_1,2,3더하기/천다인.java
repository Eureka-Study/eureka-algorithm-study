//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14212 KB , 시간 : 104 ms
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        int[] dp = new int[12];

        // 기본값 설정 (1, 2, 3을 만드는 방법의 수)
        dp[1] = 1; // (1)
        dp[2] = 2; // (1+1), (2)
        dp[3] = 4; // (1+1+1), (1+2), (2+1), (3)

        // 점화식을 이용한 dp 배열 채우기
        for (int i = 4; i <= 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.print(sb);
    }
}