import java.io.*;
import java.util.*;

// 문제 정의
// 집합 S에서 6개를 고르는 경우의 수 모두 출력

// 문제 아이디어
// 조합(백트래킹으로 구현)

// Pseudo Code
// if tgtIdx == 6:
//    조합 출력
//    return
// if srcIdx == nums.length:
//    return
// 현재 숫자 선택
// 	tgt[tgtIdx] = nums[srcIdx]
// 	comb(srcIdx + 1, tgtIdx + 1)
// 현재 숫자 미선택
//	comb(srcIdx + 1, tgtIdx)

// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14260 KB , 시간 : 108ms

public class Main {
	
	// 원본 숫자 집합
	static int[] nums;
	// 조합을 저장할 배열 (6개)
	static int[] tgt = new int[6];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken()); // 집합의 크기
			
			if (k == 0) break;
			
			nums = new int[k];
			
			for (int i = 0; i < k; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			// 조합 함수 호출 (시작 인덱스는 0, 타겟 인덱스도 0)
			comb(0,0);
			
			sb.append("\n");

		}
		System.out.println(sb);
	}
	
	
	static void comb(int srcIdx, int tgtIdx) {
		// base case: 6개 다 고른 경우
		if (tgtIdx == tgt.length) {
			for (int num : tgt) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// base case: 끝까지 탐색했는데 6개를 못고른 경우
		if (srcIdx == nums.length) return;
		
		// 현재 숫자 선택
		tgt[tgtIdx] = nums[srcIdx]; 
		comb(srcIdx+1, tgtIdx + 1); 
		
		// 현재 숫자 비선택
		comb(srcIdx+1, tgtIdx);
	}
	
}