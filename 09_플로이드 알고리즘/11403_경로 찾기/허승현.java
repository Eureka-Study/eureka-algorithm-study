import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 플로이드-워셜 알고리즘
// 모든 노드 쌍 (i,j)에 대해 경로가 존재하는 지 확인
// i->j로 바로 가는 길이 있거나 (adj[i][j] == 1)
// k를 경유해서 가는 길이 있음 (adj[i][k] == 1 && adj[k][j] == 1)

// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16260 KB , 시간 : 160ms

public class Main {

	static int N;
	static int[][] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		adj =  new int[N][N];
		
		//인접 행렬 초기화
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 플로이드-워셜
		for (int k = 0; k < N; k++) { // 경유점
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(adj[i][k] == 1 && adj[k][j] == 1) { // k를 경유해서 가는 길이 있음 (adj[i][k] == 1 && adj[k][j] == 1)
						adj[i][j] = 1;
					}
				}
				
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(adj[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	
}