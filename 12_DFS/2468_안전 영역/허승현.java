import java.io.*;
import java.util.*;

// 언어 : JAVA , (성공/실패) : 1/0, 메모리 : 36704 KB , 시간 : 260ms

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		int max_height = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max_height = Math.max(max_height, map[i][j]);
			}
		}
		
		int Safe_area = 0;
		for (int h = 0; h <= max_height; h++) {
			visited = new boolean[N][N];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] > h) { //방문하지 않았고 h보다 깊이가 높다면 bfs탐색
						bfs(i,j,h);
						cnt++;
					}
				}
			}
			Safe_area = Math.max(Safe_area, cnt);
		}
		
		System.out.println(Safe_area);
		
	}
	
	static void bfs(int y, int x, int h) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {y,x});
		visited[y][x] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cy = cur[0];
			int cx = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if (ny < 0 || nx < 0 || nx >= N || ny >= N) continue;
				
				if (!visited[ny][nx] && map[ny][nx] > h) {
					visited[ny][nx] = true;
					queue.offer(new int[] {ny,nx});
				}
				
			}
		}
	}
}