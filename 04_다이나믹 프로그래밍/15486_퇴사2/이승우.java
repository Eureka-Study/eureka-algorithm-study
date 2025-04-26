
//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 339308 KB , 시간 : 800 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] list = new int[N][2];

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            list[n] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
        }

        int[] dp = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            int time = list[i][0] + i; // 현 날짜에서 이 업무를 해결할 때까지 걸리는 날짜
            if (time <= N) { // 업무가 퇴사날짜보다 작은지 체크( 넘어가면 이 업무는 무조건 포기해야하기 때문)
                dp[i] = Math.max(dp[i + 1], list[i][1] + dp[time]); // 다음 날 예정 업무 금액 과 이 일 처리 금액 + 일 처리 다음날 부터 수행할 업무 금액
                                                                    // 중 큰 값을 리턴
            } else {
                dp[i] = dp[i + 1]; // 해당 업무는 수행이 불가능하기 때문에 그대로 따라갈 것이다.
            }
        }

        System.out.println(dp[0]);
    }
}
