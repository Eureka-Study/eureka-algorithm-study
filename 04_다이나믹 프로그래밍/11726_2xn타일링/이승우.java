
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14396 KB , 시간 : 108 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class nx2타일링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        if (N >= 0)
            dp[0] = 1; // 2 x 0 은 아무것도 채울 수 없다는 경우의 수
        if (N > 0)
            dp[1] = 1; // 2 x 1 은 하나만 채울 수 있다는 경우의 수

        for (int n = 2; n <= N; n++) { // n의 경우의 수는 n - 1의 경우와 n - 2의 경우를 더한 값이다.
                                       // ( n - 1은 어차피 1개의 경우만 존재, n - 2는 2개의 경우의 수가 존재하지만 n - 1과 겹치므로 사실 상 1개의 경우의 수만
                                       // 존재한다.
                                       // 따라서 n - 1 경우와 n - 2를 더하면 최종 경우의 수이다.)
            dp[n] = (dp[n - 1] + dp[n - 2]) % 10007; // 경우의 수가 너무 커지므로 10007로 나누라고 되어있다.
        }

        System.out.println(dp[N]);
    }
}
