import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// 문제 정의
// 1. 벽은 AOJ로 박살낼 수 있음(=빈 방), AOJ 사용 조건 없음
// 2. 상하좌우 인접 빈 방 이동 가능
// => 벽을 최소로 부수면서 (N,M)으로 이동
// 문제 해결 방법
// 일단 bfs로 최단 경로를 탐색하는데 최대한 벽을 피해가야 한다.
// 벽인 경우도 경로에 추가하기는 하는데 탐색 우선순위를 후순위로하면 될 듯
// 우선순위 큐를 써서 다익스트라로도 풀 수 있을 듯 한데 그럼 O(ElogV)가 되어서 느릴 것 같아서 일단 bfs로

// PseudoCode
//시작위치에서 deque를 이용한 bfs로 탐색
//bfs():
//	while(deque가 비어있지 않으면):
//		현재 위치 꺼내기
//		4방향 탐색:
//			미로 범위 체크
//			부순 벽 개수 = 현재까지의 부순 벽 개수 + maze[ny][nx]
//					if 부순 벽 개수가 더 적을 수 있으면:
//						현위치 벽 개수 갱신
//						if maze[ny][nx]가 벽이면:
//							우선순위 UP(deque앞에 삽입)
//						else:
//							deque뒤에 삽입

// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14580 KB , 시간 : 116 ms
public class Main {

	static int N, M;
	static int[][] maze;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[][] breakWall; // 부순 벽 개수 저장

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		maze = new int[N][M];
		breakWall = new int[N][M];

		// 미로 정보 입력
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = str.charAt(j) - '0';
				breakWall[i][j] = Integer.MAX_VALUE;
			}
			
		}

		bfs();

		System.out.println(breakWall[N - 1][M - 1]);

	}
	
	static void bfs() {
		// 우선순위 활용(벽을 최대한 피해서 bfs로 가기 위해 deque 사용)
		Deque<int[]> deque = new ArrayDeque<int[]>();
		deque.offer(new int[] {0,0});
		breakWall[0][0] = 0;
		
		
		while(!deque.isEmpty()) {
			int[] curr = deque.poll();
			int cy = curr[0];
			int cx = curr[1];
			
			// 상하좌우 탐색
			for(int i = 0; i < 4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				// 현재위치까지 부순 벽 개수 + 다음 이동 위치에 벽 유(1)무(0)를 더한 것이 곧 부순 벽 개수
				int nextbreak = breakWall[cy][cx] + maze[ny][nx];
				
				if (nextbreak < breakWall[ny][nx]) { 
					// 더 벽을 적게 부순 경로가 존재하므로 갱신
					breakWall[ny][nx] = nextbreak;
					
					// 벽이면 후순위로 탐색하여 최대한 피해가도록
					if(maze[ny][nx] == 0) {
						// 빈 방이면 먼저 탐색하도록 앞에 삽입
						deque.addFirst(new int[] {ny,nx});
					}
					else {
						deque.addLast(new int[] {ny,nx});
					}
				}
			}
		}
	}

}