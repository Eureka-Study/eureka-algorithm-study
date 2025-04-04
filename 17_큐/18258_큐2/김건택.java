// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 332120 KB , 시간 : 1264 ms
package boj;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Silver4_18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push" :
                    int n = Integer.parseInt(st.nextToken());
                    dq.offer(n);
                    break;
                case "pop" :
                    Integer pop = dq.poll();
                    if (pop == null) {
                        bw.write("-1\n");
                        break;
                    }
                    bw.write(pop + "\n");
                    break;
                case "size" :
                    bw.write(dq.size() + "\n");
                    break;
                case "empty" :
                    if (dq.isEmpty()) {
                        bw.write(1 + "\n");
                    } else {
                        bw.write(0 + "\n");
                    }
                    break;
                case "front" :
                    if (dq.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(dq.peekFirst() + "\n");
                    }
                    break;
                case "back" :
                    if (dq.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(dq.peekLast() + "\n");
                    }
                    break;
            }

        }
        bw.flush();
        bw.close();
        br.close();
    }
}
