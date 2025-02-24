// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 24816 KB , 시간 : 252 ms

import java.io.*;
import java.util.*;

public class Main {  // 최소 힙
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pqueue = new PriorityQueue<>();  // PQ 기본적으로 최소힙(오름차순 정렬 poll)

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());  // x를 num 이라고 선언하고 인풋받기
            if (num == 0) {     // 만약 x 가 0이면
                if (pqueue.isEmpty()) { // PQ 비었으면
                    bw.write('0');
                    bw.newLine();
                } else {
                    bw.write(String.valueOf(pqueue.poll()));
                    bw.newLine();
                }
            } else {
                pqueue.offer(num);
            }
        }

        // 출력
        bw.flush();
        bw.close();
        br.close();
    }
}
