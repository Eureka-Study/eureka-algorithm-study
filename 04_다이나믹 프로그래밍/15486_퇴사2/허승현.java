import java.io.*;
import java.util.*;

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 315792 KB , 시간 : 680ms

// 주의할 점은 만약 마지막 날에 T = 1 이라면 일을 할 수 있기 때문에 DP를 1~N+1까지 받아야 한다.

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N + 2];
		int[] P = new int[N + 2];
		int[] dp = new int[N + 2]; // i일에 얻을 수 있는 최대 이익

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());

		}
		
		for (int i = 1; i <= N+1; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			
			// 오늘 상담을 선택할 수 있음
			if (i + T[i] <= N + 1) {
				dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
			}
		}
		
		System.out.println(dp[N+1]); // N일까지 가능한 모든 상담을 마쳤을 때 얻을 수 있는 최대 수익
	}

}