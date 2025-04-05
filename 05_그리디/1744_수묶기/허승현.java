import java.io.*;
import java.util.*;

// 문제 해결
// 두 개를 묶을 수 있는데 묶으면 곱으로 계산한다.
// 오름차순으로 정렬해서 음수는 가장 작은 수를 시작으로 묶는다.
// 양수는 가장 큰 것부터 묶는다.
// => 근데 양수 중에 1은 곱하지 말고 더하는 게 수열의 합을 최대화하는 방법이니 1은 더한다.
// 0은 음수 중에 가장 작지 않은 수랑 곱해서 수열의 합을 최대화한다.
// => 0을 음수 쪽에 넣어서 수 묶기를 하면 알아서 수열의 합이 최대화가 된다
// => 다 묶고 나서 홀수 개가 남으면 그냥 더한다

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14284 KB , 시간 : 104ms
public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> plus = new ArrayList<Integer>();
		ArrayList<Integer> minus = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num <= 0) {
				minus.add(num);
			} else {
				plus.add(num);
			}

		}
		
		
		// 정렬
		minus.sort(Comparator.naturalOrder()); // 오름차순
		plus.sort(Comparator.reverseOrder()); // 내림차순

		// 수열의 합 계산
		int result = 0;
		// 음수 부분
		for (int i = 0; i < minus.size(); i += 2) {
			if (i + 1 < minus.size()) {
				result += minus.get(i) * minus.get(i + 1);
			} else {
				// 하나 남은 경우 그냥 더함
				result += minus.get(i);
			}
		}
		// 양수 부분
		for (int i = 0; i < plus.size(); i += 2) {
			if (i + 1 < plus.size()) {
				if (plus.get(i) == 1 || plus.get(i + 1) == 1) { // 수를 묶는 데 1개라도 1이라면 더하는 것이 최대화 방법
					result += plus.get(i) + plus.get(i + 1);
				} else {
					result += plus.get(i) * plus.get(i + 1);
				}
			} else {
				// 하나 남은 경우 그냥 더함
				result += plus.get(i);
			}

		}
		
		System.out.println(result);

	}
}