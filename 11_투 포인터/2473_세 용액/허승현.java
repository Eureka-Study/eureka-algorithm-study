//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 17392KB , 시간 : 248ms

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long arr[] = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);

		long result = Long.MAX_VALUE;
		// 세 용액의 인덱스
		int idx1 = 0;
		int idx2 = 1;
		int idx3 = 2;

		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;
			while (left < right) {
				long sum = arr[i] + arr[left] + arr[right];
				long abs = Math.abs(sum);
				
				// 가장 0에 근접한 수 갱신
				if (abs < result) {
					result = abs;
					idx1 = i;
					idx2 = left;
					idx3 = right;
					if (result == 0)
						break;
				}

				if (sum > 0) {
					right--;
				} else {
					left++;
				}
			}
		}
		
		System.out.println(arr[idx1]+" "+arr[idx2]+" "+arr[idx3]);

	}

}