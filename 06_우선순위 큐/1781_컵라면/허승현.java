import java.io.*;
import java.util.*;

// 문제 정의
// 하루에 한 문제만 풀 수 있으며, 각 문제를 풀 때 받을 수 있는 컵라면의 수를 최대화하자

// 문제 아이디어
// 일단 모든 문제를 데드라인 기준으로 오름차순 정렬해야 시간 낭비 없이 처리 가능
// 컵라면 수를 기준으로 최소 힙을 만들어서 현재까지 선택한 문제들의 컵라면 수 저장
// 현재 데드라인까지 푼 문제 수가 데드라인 이하이면 문제를 풀 수 있음
// 만약 푼 문제 수가 데드라인을 초과하면, 가장 적은 컵라면 수 문제 버리기

// Pseudo Code
// List<Problem> problems 입력 받은 문제 리스트;
// Collections.sort(problems, (a,b) -> a.deadline - b.deadline)
// 우선순위 큐로 최소힙
// for 문제 순회:
//	일단 힙에 넣고
//	if 힙의 크기가 데드라인을 초과한다면:
//		가장 적은 컵라면 수 버리기
//	return 힙에 남은 컵라면 수 모두 더함

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 72648 KB , 시간 : 808 ms
public class Main {

	static class Problem {
		int deadline;
		int ramen;

		public Problem(int deadline, int ramen) {
			this.deadline = deadline;
			this.ramen = ramen;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 문제 수

		List<Problem> problems = new ArrayList<Main.Problem>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int ramen = Integer.parseInt(st.nextToken());
			problems.add(new Problem(deadline, ramen));
		}

		// 데드라인 기준 오름차순 정렬
		Collections.sort(problems, (a, b) -> a.deadline - b.deadline);

		// 해결한 문제 중 컵라면 수 기준 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (Problem p : problems) {
			pq.add(p.ramen);
			// 만약 현재까지 푼 문제 수가 데드라인을 초과하면
			if (pq.size() > p.deadline) {
				pq.poll();// 가장 컵라면 수가 적은 문제 버림
			}
		}

		// 우선순위 큐에 남아 있는 컵라면 수 모두 더함
		int result = 0;
		while (!pq.isEmpty()) {
			result += pq.poll();
		}
		System.out.println(result);
	}

}
