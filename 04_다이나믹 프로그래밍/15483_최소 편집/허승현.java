//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 18368KB , 시간 : 132ms

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] stringA = br.readLine().toCharArray();
		char[] stringB = br.readLine().toCharArray();

		int result = levenshteinDistance(stringA, stringB);
		System.out.println(result);

	}

	static int levenshteinDistance(char[] src, char[] tgt) {
		int m = src.length;
		int n = tgt.length;

		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			dp[i][0] = i;
		}
		for (int j = 1; j <= n; j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (src[i - 1] == tgt[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j],
							dp[i][j - 1]),
							dp[i - 1][j - 1]
					) + 1;
				}
			}
		}
		return dp[m][n];
	}

}