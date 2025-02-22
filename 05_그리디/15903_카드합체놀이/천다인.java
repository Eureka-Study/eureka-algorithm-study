//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15404 KB , 시간 : 140 ms
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>(); // 우선순위 큐(최소 힙) 사용

        int n = Integer.parseInt(st.nextToken()); // 카드 개수
        int m = Integer.parseInt(st.nextToken()); // 합체 횟수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            long x = pq.poll(); // 가장 작은 값
            long y = pq.poll(); // 두 번째로 작은 값
            long sum = x + y;
            pq.add(sum);
            pq.add(sum);
        }

        // 모든 카드의 합 구하기
        long result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }
        System.out.print(result);
    }
}