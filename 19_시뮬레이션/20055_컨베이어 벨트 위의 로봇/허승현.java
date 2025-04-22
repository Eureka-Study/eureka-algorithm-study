import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 조건
// N-1에 도달하면 로봇은 무조건 내린다
// 로봇은 앞칸에 로봇이 없고 내구도가 1 이상이면 한 칸 이동할 수 있다.
// 내구도가 0인 칸이 K개 이상이면 종료

// 문제 해결
// 벨트가 회전하고 로봇은 그 위에 움직이는 척을 한다.
// 로봇 위치 저장 배열
// 벨트 회전
//	-벨트,로봇 오른쪽으로 shift (맨 뒤는 앞으로)
//  -로봇도 함께 이동, N-1에 도착 로봇 제거
// 로봇 이동
// -뒤에서부터 확인하면서 한 칸 앞으로 이동 조건 체크
// 처음 위치에 올릴 수 있으면 올림
// 종료: 내구도 0의 칸 K개 이상

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 22596 KB , 시간 : 392ms

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] belt = new int[N * 2];
		boolean[] robots = new boolean[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N * 2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		int step = 1;
		int start = 0; // 회전 시작 인덱스

		// 로봇을 옮기는 과정
		while (true) {
			// 1. 벨트 + 로봇 회전
			// 실제 배열을 돌리지 않고 회전 시작 인덱스 계산
			start = (start - 1 + 2 * N) % (2 * N); // N이 3이라면 5번째 index가 0번 위치 역할(start=5)
			// 로봇도 함께 회전
			for (int i = N - 1; i > 0; i--) {
				robots[i] = robots[i - 1];
			}
			robots[0] = false;
			robots[N - 1] = false; // 내리는 위치에 로봇 제거

			// 2. 로봇 이동
			for (int i = N - 2; i >= 0; i--) {
				if (robots[i]) {
					int nextIdx = (start + i + 1) % (2 * N); // 로봇이 i에 있을 때 이동할 다음 위치

					if (!robots[i + 1] && belt[nextIdx] > 0) {
						belt[nextIdx]--;
						robots[i + 1] = true;
						robots[i] = false;
					}
				}

			}
			
			robots[N-1] = false; // 내리는 위치


			// 3번: 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (belt[start] > 0) {
				robots[0] = true;
				belt[start]--;
			}

			// 4번: 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			 int zeroCount = 0;
	            for (int durability : belt) {
	                if (durability == 0) zeroCount++;
	            }
	            if (zeroCount >= K) {
	                System.out.println(step);
	                break;
	            }

	            step++;
		}
	}

}