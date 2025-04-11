import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;


//  문제 해결
// 위상 정렬을 사용(선후관계가 정의됨)
// PD들이 짠 순서로 남일이가 순서를 정하는 것이 불가능 한 경우는 큐가 비었을 때 모든 노드들의 진입 차수가 0이 아니어야 함(순환구조)
// 0이면 해당 순서로 정하는 것이 가능

// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14804 KB , 시간 : 120ms
public class Main {
	
	static int N, M;
	static int[] ranks; // 진입 차수
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken()); // 가수의 수
		M = Integer.parseInt(st.nextToken()); // 보조 PD 수 

		// 차수 배열 및 인접 리스트 초기화
		ranks = new int[N+1];
		list = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			String[] line = br.readLine().split(" ");
			int size = Integer.parseInt(line[0]);
			for (int j = 2; j <= size; j++) {
				int prev = Integer.parseInt(line[j-1]);
				int curr = Integer.parseInt(line[j]);				
				// 인접 행렬
				list[prev].add(curr);			
				// 진입 차수
				ranks[curr]++;
			}
		}
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		// 진입 차수가 0인거 큐에 넣기
		for (int i = 1; i <= N; i++) {
			if (ranks[i] == 0) {
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			sb.append(num).append("\n");
			for (int adj : list[num] ) {
				// 진입 차수 감소
				ranks[adj]--;
				if (ranks[adj] == 0) {
					queue.add(adj);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (ranks[i] != 0) {
				System.out.println("0");
				return;
			}
		}
		
		System.out.println(sb);
	}

}