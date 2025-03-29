//언어 : JAVA , (성공/실패) : 1/4, 메모리 : 72228 KB , 시간 : 780 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 컵라면 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int[]> problems = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int deadLine = Integer.parseInt(st.nextToken());
            int noodles = Integer.parseInt(st.nextToken());
            problems.add(new int[]{deadLine, noodles});
        }
        problems.sort(Comparator.comparingInt(o -> o[0])); // 데드 라인으로 sort 기준으로 잡아 작은 것부터 우선적으로 처리해야하기 때문
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] problem : problems) {
            int deadLine = problem[0];
            int noodles = problem[1];

            pq.add(noodles); //일단 넣기
            
            if(pq.size() > deadLine){ //만약 데드 라인보다 사이즈가 클 경우 우선순위 큐에 의해 가장 작은 값이 우선적으로 제거
                pq.poll();
            }
        }

        int count = 0;
        while (!pq.isEmpty()) {
            count += pq.poll(); // 큐에 넣어있는 것은 문제를 푼 것이므로 컵라면 개수 세기
        }

        System.out.println(count);
    }
}
