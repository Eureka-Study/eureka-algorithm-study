import java.io.*;
import java.util.*;

// 합이 S보다 작으면 수열의 길이를 늘려가자
// 합이 S 이상인 순간 왼쪽 포인터를 줄여서 최소 길이 갱신

// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 :  23792KB , 시간 : 244ms
public class Main {

	static int N, S;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		nums = new int[N];

		// 수열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 0, sum = 0;
		int minlen = Integer.MAX_VALUE;

		while (true) {
			if (sum >= S) {
				minlen = Math.min(minlen, right - left);
				sum -= nums[left++];
			} else if (right == N)
				break;
			else {
				sum += nums[right++];
			}

		}

		System.out.println(minlen == Integer.MAX_VALUE ? 0 : minlen);
	}

}