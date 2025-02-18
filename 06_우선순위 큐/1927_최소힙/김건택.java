// 언어 : JAVA , (성공/실패) : 2/2 , 메모리 : 31352 KB , 시간 : 316 ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            long x = Long.parseLong(br.readLine());

            if (x == 0) {
                if (pq.isEmpty()) {
                    bw.write(String.valueOf(0) + "\n");
                } else {
                    bw.write(String.valueOf(pq.poll()) + "\n");
                }
            } else {
                pq.add(x);
            }
        }

        bw.flush();
    }
}