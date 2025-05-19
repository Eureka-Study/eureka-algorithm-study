import java.io.*;
import java.util.*;

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 107328KB , 시간 : 672ms

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][][] visited; // y,x,isbroken
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}

		}
		
		int result = bfs();
		
		System.out.println(result);
	}
	
	
	static int bfs() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {0,0,1,0}); // y,x,거리,벽 부숨?
		visited[0][0][0] = true;
				
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int cy = curr[0];
			int cx = curr[1];
			int dist = curr[2];
			int broken = curr[3];
			
			if (cy == N - 1 && cx == M - 1) return dist;
			
			for (int d = 0; d<4; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				
				// 빈 칸이고 아직 방문 안함
				if (map[ny][nx] == 0 && !visited[ny][nx][broken]) {
					visited[ny][nx][broken] = true;
					queue.add(new int[] {ny,nx,dist+1,broken});
				}
				// 벽이고 아직 벽을 부순 적이 없다면
				if (map[ny][nx] == 1 && broken == 0 && !visited[ny][nx][1]) {
					visited[ny][nx][1] = true;
					queue.add(new int[] {ny,nx,dist + 1, 1});
				}
				
				
			}
		}
		return -1;
	}

}
