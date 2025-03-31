import java.io.*;
import java.util.*;

// 문제 정의
// 집합 S에 속한 문자열 N개
// 각 문자열이 집합 S에 있는 어떤 문자열의 접두사인지 확인해야 함
// 출력: 접두사인 경우의 수

// 일단 문자열을 정렬해서, 같은 접두사를 가진 문자열들이 연속적으로 나오게 해보자
// query보다 크거나 같은 첫 번째 문자열을 이분탐색으로 찾고 그 문자열이 query로 시작하는지 확인

// PseudoCode
// S집합의 문자열 정렬
// 이분탐색으로 S집합 내 query가 등장하는 위치 찾기
//	이분탐색:
//		S가 query보다 사전순으로 이전이면 음수:
//			left = mid + 1
//			반대의 경우:
//				right = mid
// 시작 위치 찾았으면 startwith로 접두사 여부 확인
				
// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 30644 KB , 시간 : 436 ms

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // S 집합 문자열 수
        int M = Integer.parseInt(st.nextToken()); // 검사할 문자열 수

        List<String> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }

        Collections.sort(list); // 문자열 사전순 정렬

        int count = 0;
        for (int i = 0; i < M; i++) {
            String query = br.readLine();
            int idx = startIndex(list, query);
            if (idx < list.size() && list.get(idx).startsWith(query)) {
                count++;
            }
        }

        System.out.println(count);
    }

    // list내 query 이상이 처음 나오는 위치를 구함(정렬을 했기 때문에 이분탐색으로 가능)
    private static int startIndex(List<String> list, String target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid).compareTo(target) < 0) { // target이 사전순으로 뒤에 있는 경우
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
