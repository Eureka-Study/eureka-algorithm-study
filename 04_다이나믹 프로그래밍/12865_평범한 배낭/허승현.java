import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] dp;
	static int[] W, V;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		W = new int[N];
		V = new int[N];
		dp = new int[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());

		}

		for (int i = 0; i < N; i++) {
			for (int j = M; j >= W[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
			}
		}

		System.out.println(dp[M]);
	}

}