// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 13024 KB , 시간 : 104 ms

import java.io.*;
import java.util.*;

public class Main {     // 카드 합체 놀이
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pqueue = new PriorityQueue<>();     // 문제 특성상 Integer 대신 Long 사용
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pqueue.offer(Long.parseLong(st.nextToken()));
        }

        // 풀이
        for (int i = 0; i < M; i++) {
            long firstPoll = pqueue.poll();     // PQ에서 첫번째로 작은 값 뽑기
            long secondPoll = pqueue.poll();    // PQ에서 두번째로 작은 값 뽑기

            pqueue.offer(firstPoll + secondPoll);   // 그 두개의 합을 PQ에 넣어주기
            pqueue.offer(firstPoll + secondPoll);   // 또 넣어주기
        }

        long ans = 0;   // 정답인 합 역시 long으로 선언
        while (!pqueue.isEmpty()) {
            ans += pqueue.poll();
        }

        // 출력
        System.out.println(ans);
    }

}
