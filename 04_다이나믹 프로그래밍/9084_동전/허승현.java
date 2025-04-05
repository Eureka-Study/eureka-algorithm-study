import java.io.*;
import java.util.*;

// 문제 정의
// 동전의 종류가 주어질 때 주어진 금액을 만드는 모든 방법을 세는 프로그램 작성

// 문제 해결 전략
// 목표 값 M에 도달하는 데까지 dp 이용
// 동전 오름차 순으로 방법 수를 저장
// 특정 값을 만드는 방법은 dp[특정 값] = dp[특정 값] + dp[특정 값-현재 동전 값]

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 :  14096KB , 시간 : 108ms

public class Main {

	static int[] dp;
	static int[] coins;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine()); // 동전 종류
			coins = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}

			int M = Integer.parseInt(br.readLine());
			dp = new int[M + 1];
			dp[0] = 1;
			for (int i = 0; i < N; i++) {
				int coin = coins[i];
				for (int j = coin; j <= M; j++) {
					dp[j] = dp[j] + dp[j - coin];
				}
			}

			sb.append(dp[M]).append("\n");
		}
		System.out.println(sb);
	}

}
