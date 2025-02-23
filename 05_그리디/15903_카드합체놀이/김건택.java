// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 15216 KB , 시간 : 148 ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>(); // 작은 거 2개를 뽑기 위한 우선 순위 큐 선언

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(st.nextToken())); // 카드 입력
        }

        for (int i = 0; i < m; i++) {
            long x = pq.poll(); // 제일 작은 거
            long y = pq.poll(); // 2번째 작은 거
            long sum = x + y;
            pq.add(sum);
            pq.add(sum);
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}