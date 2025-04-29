import java.io.*;
import java.util.*;

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 95248KB , 시간 : 424ms

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N];
		
		// 입력값 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		int max = 0;
		
		// 연속하는 수열의 최대 길이 탐색
		for (int i = 0; i < N; i++) {
			dp[nums[i]] = dp[nums[i]-1] + 1;
			max = Math.max(max, dp[nums[i]]);
		}
		
		System.out.println(N-max);

	}

}
