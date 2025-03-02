// 언어 : JAVA , (성공/실패) : 1/4 , 메모리 : 18092KB , 시간 : 120ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class oneMaking {
    public static void main(String[] args) throws IOException {
        /**
         * 시도횟수가 많았던 이유
         * 
         * 배열없이 하려고했다가 중복계산 횟수가 많아져 시간 초과가 발생
         * 
         * 최적화를 시도, 실패하여 현 코드와 같이 작성
         * 
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        for (int i = 2; i <= N; i++) { // 역으로 계산 상위로 올라가면서 다양한 선택지 중 가장 짧게 이동한 것을 선택
            dp[i] = dp[i - 1] + 1;

            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }

        System.out.println(dp[N]);
    }
}