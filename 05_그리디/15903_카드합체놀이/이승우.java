// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 15368KB , 시간 : 148ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Card {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");

        /**
         * 우선순위 큐 <== 중복 허용 함
         * 
         * 처음에 이 점을 몰라서 다른 방법을 찾으려고 노력했으나 접근 자체가 어려웠다.
         * 
         * 후에 우선순위 큐가 중복을 허용한다는 것을 알고 도입
         */
        PriorityQueue<Long> queue = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            queue.add(Long.parseLong(st.nextToken()));
        }

        for(int i = 0; i < m; i++){
            // 우선순위이기 때문에 poll 시 가장 작은 값이 도출
            long one = queue.poll();
            long two = queue.poll();

            long newInt = one + two;

            queue.add(newInt);
            queue.add(newInt);
        }

        long answer = 0;

        for(long i : queue){
            answer += i;
        }

        System.out.println(answer);
    }
}
