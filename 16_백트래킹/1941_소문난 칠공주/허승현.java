import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;



//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 73220 KB , 시간 : 448ms

public class Main {
	
	static char[][] map = new char[5][5];
	static int result = 0;
	static int[] selected = new int[7];
	static boolean[] visited = new boolean[25];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		comb(0,0);
		System.out.println(result);
		
	}
	
	static void comb(int depth, int start) {
		if (depth == 7) {
			if (checkConnect()) {
				result++;
			}
			return;
		}
		
		for (int i = start; i < 25; i++) {
			selected[depth] = i;
			comb(depth+1,i+1);
		}
	}
	
	static boolean checkConnect() {
		boolean[] check = new boolean[7]; // 선택된 인덱스 방문 여부
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		check[0] = true;
		queue.offer(selected[0]);
		
		int connectedCnt = 1;
		int sCnt = map[selected[0] / 5][selected[0] % 5] == 'S' ? 1 : 0;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			int y = now / 5;
			int x = now % 5;
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
				
				int idx = ny * 5 + nx;
				
				// 선택된 애들 중에 있고, 아직 방문 안 했으면
				for (int i = 0; i < 7; i++) {
					if (!check[i] && selected[i] == idx) {
						check[i] = true;
						queue.offer(idx);
						connectedCnt++;
						if (map[ny][nx] == 'S') sCnt++;
					}
				}
			}
		}
		
		return connectedCnt == 7 && sCnt >= 4;
	}
	
	

}