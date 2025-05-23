//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 52860KB , 시간 : 516ms

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] f = new int[N][2];
		int size = 0;

		// 3/1일 이전에 지거나 12/1 이후에 피는 꽃은 배제
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			if (em <= 301 || sm >= 1201)
				continue;
			f[size][0] = sm;
			f[size][1] = em;
			size++;
		}

		Arrays.sort(f, 0, size, (a, b) -> {
			if (a[0] != b[0])
				return a[0] - b[0]; // 시작 날짜 오름차순
			return b[1] - a[1]; // 시작 날짜 같으면 끝나는 날짜 내림차순
		});

		int cnt = 0;
		int idx = 0;
		int cur = 301; // 현재까지 커버된 마지막 날짜
		int need = 1201; // 목표 날짜

		while (cur < need) {
			int maxEnd = cur;
			// start <= cur 인 꽃들 중 가장 end 큰 것 찾기
			while (idx < size && f[idx][0] <= cur) {
				maxEnd = Math.max(maxEnd, f[idx][1]);
				idx++;
			}

			// 더 이상 확장 불가
			if (maxEnd == cur) {
				System.out.println(0);
				return;
			}

			cur = maxEnd;
			cnt++;

		}
		System.out.println(cnt);

	}

}