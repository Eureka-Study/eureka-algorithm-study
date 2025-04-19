import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


// 문제 해결
// 이분 그래프: 그래프의 모든 정점이 두 그룹으로 나눠지며, 서로 다른 그룹 그룹의 정점이 간선으로 연결되어 있는 그래프
// BFS로 같은 레벨의 정점은 같은 색으로 칠한다.
// 탐색을 진행할 때 자신과 인접한 정점의 색이 자신과 동일하면 이분 그래프 X
// 그래프가 비연결 그래프인 경우에는 모든 정점에 대해서 확인해야 함

// 1 ≤ V ≤ 20,000
// 1 ≤ E ≤ 200,000

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 271312 KB , 시간 : 856ms

public class Main {

	static ArrayList<Integer>[] graph;
	static int[] colors;
	static boolean isBipartite;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		while (K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 그래프 초기화
			graph = new ArrayList[V + 1];
			for (int i = 0; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			
			// 간선 입력
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				graph[u].add(v);
				graph[v].add(u);
				
			}
			
			colors = new int[V + 1];
			isBipartite = true;
			
			// 비연결 그래프일 수 있으니 모든 정점 탐색
			for (int i = 1; i <= V; i++) {
				if (colors[i] == 0) {
					if (!bfs(i)) {
						isBipartite = false;
						break;
					}
				}
			}
			
			System.out.println(isBipartite ? "YES" : "NO");
		}
		
	}
	
	
	static boolean bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(start);
		colors[start] = 1;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for (int next : graph[curr]) {
				if (colors[next] == 0) { // 아무것도 칠해지지 않은 상태
					colors[next] = -colors[curr];
					queue.offer(next);
				}else if (colors[next] == colors[curr]) { // 인접 노드가 같은 색인 경우 이분 그래프 X
					return false;
				}
			}
		}
		
		return true;
	}
}