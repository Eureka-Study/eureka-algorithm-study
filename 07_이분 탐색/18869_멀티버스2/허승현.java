import java.io.*;
import java.util.*;

// 문제 정의
//균등한 우주의 쌍이 몇 개인지 구해본다.(구성만 같으면 됨)
//균등하다의 조건은?
//	두 우주의 행성의 크기가 모든 1<=i,j<=N에 대해서 각 우주의 행성의 번호를 기준으로 대소비교가 동일해야함

// 문제 아이디어
// 대소비교만 하면 되니까 등수를 매겨서 등수 수열이 같으면 균등한 우주일 듯

// Pseudo Code
// 등수로 만드는 함수(MakeRank):
//	중복을 제거하고 행성의 크기에 순위를 정한다.
//	이 문제도 이분 탐색으로 lowerBound를 정한다.(등수)
// 등수로 만들었다면 각 배열이 같은 개수를 찾아야 함 => Map으로 카운트
// Map의 저장된 cnt를 조합으로 쌍을 구함

// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 166764 KB , 시간 : 1532 ms
public class Main {

	static int M, N;
	static int[][] spaces;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 우주의 개수
		N = Integer.parseInt(st.nextToken()); // 행성의 수

		// 각 우주의 행성 크기 입력
		spaces = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				spaces[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 각 우주별 행성의 크기를 순위로 변경
		for (int[] space : spaces) {
			MakeRank(space);
		}

		Map<String, Integer> map = new HashMap<>();
		
		// 등수 수열을 map key로 저장해서 개수 count
		for (int[] space : spaces) {
			StringBuilder sb = new StringBuilder();
			for (int num : space) {
				sb.append(num).append(",");
			}
			String key = sb.toString();
			map.put(key, map.getOrDefault(key, 0) + 1); // 이미 존재하는 key이면 key + 1 아니면 1로 설정
		}
		
		// key별 개수로 조합 쌍 구하기
		int result = 0;
		for (int count : map.values()) {
			result += count * (count - 1) / 2;
		}
		System.out.println(result);
	}

	static void MakeRank(int[] space) {
		//정렬된 배열
		int[] cloned = space.clone();
		Arrays.sort(cloned);

		// 중복 제거
		List<Integer> distinct = new ArrayList<Integer>();
		distinct.add(cloned[0]);
		for (int i = 1; i < cloned.length; i++) {
			if (cloned[i] != cloned[i - 1]) {
				distinct.add(cloned[i]);
			}
		}

		// 행성 크기의 등수 선정
		for (int i = 0; i < space.length; i++) {
			int left = 0;
			int right = distinct.size();
			// 이분 탐색
			while (left < right) {
				int mid = (left + right) / 2;
				if (space[i] <= distinct.get(mid)) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}
			space[i] = left;
		}

	}
}
