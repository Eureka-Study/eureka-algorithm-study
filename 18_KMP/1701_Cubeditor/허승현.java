import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문제 해결
// 주어진 문자열에서 접미사 배열을 구하고, 그 배열 중 가장 긴 접미사 배열의 길이를 찾는다.
// 모든 접미사를 구해서 두 접미사를 비교하여, 둘 사이의 가장 긴 공통 접미사의 길이를 찾는다.

// 연산을 줄일 수 있는 방법을 모르겠다....
// KMP 알고리즘 (접미사와 접두사를 이용)
// 접두사와 접미사를 이용한 부분 일치 최대 길이를 통해 연산의 수를 줄일 수 있다.
// 주어진 문자열의 0~i 까지의 부분 문자열 중에서 
// prefix == suffix가 될 수 있는 부분 문자열 중에서 가장 긴 것의 길이를 저장하는 배열을 만들고 이용
// 기존 코드로는 앞에서부터만 체크하니, 전체 문자열 돌면서 서브스트링 만들기

// 언어 : JAVA , (성공/실패) : 1/3 , 메모리 : 76672 KB , 시간 : 300 ms
// substring()함수 대신 index 기반 사용, 메모리 : 64988 KB , 시간 : 328 ms

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int result = 0;
		int len = str.length();

		// 모든 부분문자열 탐색
		for (int i = 0; i < len; i++) {
			String subStr = str.substring(i, len);
			result = Math.max(result, getPi(subStr));
		}

		System.out.println(result);
	}

	static int getPi(String str) {
		int n = str.length();
		int max_len = 0;
		int[] pi = new int[n];

		// KMP 알고리즘을 위한 Prefix Function 계산
		// j: 문자열의 접두사 접미사를 비교하는 데 사용 j는 접미사의 끝부분
		// pi[i]는 문자열의 처음부터 i번째 문자까지의 부분 문자열에서 접두사와 일치하는 가장 긴 접미사의 길이
		for (int i = 1, j = 0; i < str.length(); i++) {
			while (j > 0 && str.charAt(i) != str.charAt(j)) {
				// 일치하는 접두사를 찾기 위해 j를 이동(j를 이전까지 일치한 부분을 기준으로 다시 돌아가서 일치할 수 있는 부분을 찾아봄)
				j = pi[j - 1];
			}
			if (str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
			} else {
				pi[i] = 0;
			}

			max_len = Math.max(max_len, pi[i]);
		}
		return max_len;
	}
}

//		모든 접미사를 비교
//		for (int i = 0; i < str.length(); i++) {
//			for (int j = i + 1; j < str.length(); j++) {
//				// 방법1. 메모리 초과
//				String str1 = str.substring(i); // 첫번째 접미사
//				String str2 = str.substring(j); // 두번째 접미사
//				
//				// 공통 접미사의 길이를 찾는다.
//				int len = 0;
//				방법2. 인덱스로 방식 바꿈(시간초과)
//				while(i + len < str.length() && j + len < str.length() && str.charAt(i + len) == str.charAt(j + len) ) {
//					len++;
//				}
//				
//				max_len = Math.max(max_len, len);
//			}
//		}