//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 48580KB , 시간 : 400ms

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] ranks = new int[N + 1];
		ArrayList<Integer>[] list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			ranks[b]++;
		}

		PriorityQueue<Integer> pq = new PriorityQueue();

		for (int i = 1; i <= N; i++) {
			if (ranks[i] == 0) {
				pq.add(i);
			}
		}

		while (!pq.isEmpty()) {
			int num = pq.poll();
			sb.append(num).append(" ");
			for (int adj : list[num]) {
				ranks[adj]--;
				if (ranks[adj] == 0) {
					pq.add(adj);
				}
			}
		}

		System.out.println(sb);
	}

}