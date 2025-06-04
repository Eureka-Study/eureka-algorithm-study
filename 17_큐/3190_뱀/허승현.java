//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14492KB , 시간 : 112ms

import java.io.*;
import java.util.*;

public class Main {

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 보드 크기 N, 사과 개수 K
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		// 사과 위치 1 마킹
		int[][] board = new int[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = 1;
		}

		// 방향 전환 정보 저장
		int L = Integer.parseInt(br.readLine());
		Map<Integer, Character> turns = new HashMap<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			turns.put(t, dir);
		}

		// 덱에 뱀의 몸통 좌표 저장(꼬리는 뒤, 머리는 앞)
		Deque<int[]> snake = new ArrayDeque<int[]>();
		snake.addFirst(new int[] { 1, 1 });
		board[1][1] = 2; // 뱀이 차지한 칸

		// 시뮬레이션용 변수
		int time = 0;
		int dir = 0; // 처음에는 오른쪽
		int head_X = 1;
		int head_Y = 1;

		while (true) {
			time++;

			int nx = head_X + dx[dir]; 
			int ny = head_Y + dy[dir];

			// 벽 충돌 체크
			if (nx < 1 || nx > N || ny < 1 || ny > N) {
				System.out.println(time);
				return;
			}

			// 몸 충돌 체크
			if (board[ny][nx] == 2) {
				System.out.println(time);
				return;
			}

			if (board[ny][nx] == 1) {
				// 사과가 있으면: 머리만 늘리고 꼬리는 그대로
				board[ny][nx] = 2; // 뱀 자리 표시
				snake.addFirst(new int[] { ny, nx });
			} else {
				// 사과가 없으면: 머리 늘리고 꼬리 한 칸 제거
				board[ny][nx] = 2;
				snake.addFirst(new int[] { ny, nx });

				// 꼬리 제거
				int[] tail = snake.removeLast();
				board[tail[0]][tail[1]] = 0; // 빈 칸으로 원복
			}

			// 머리 좌표 갱신
			head_X = nx;
			head_Y = ny;

			if (turns.containsKey(time)) {
				char turn = turns.get(time);
				if (turn == 'L') {
					dir = (dir + 3) % 4;
				} else if (turn == 'D') {
					dir = (dir + 1) % 4;
				}

			}
		}
	}

}