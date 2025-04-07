// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 376760 KB , 시간 : 2308 ms

import java.io.*;
import java.util.*;

public class Silver_18258 {     // 큐2
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> q = new ArrayDeque<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<String> orders = new ArrayList<>();
            while (st.hasMoreTokens()) {    // push X 면 orders에 두개 들어가고, 아니면 한개 들어감
                orders.add(st.nextToken());
            }

            if (orders.size() == 2) {                       // orders.get(0) == 명렁어 (push)
                int num = Integer.parseInt(orders.get(1));  // orders.get(1) == 숫자 (X)
                q.offer(num);

            } else {
                String order = orders.get(0);
                if (order.equals("pop")) {
                    if (q.isEmpty()) bw.write("-1");
                    else bw.write(String.valueOf(q.poll()));

                } else if (order.equals("size")) {
                    bw.write(String.valueOf(q.size()));

                } else if (order.equals("empty")) {
                    if (q.isEmpty()) bw.write("1");
                    else bw.write("0");

                } else if (order.equals("front")) {
                    if (q.isEmpty()) bw.write("-1");
                    else bw.write(String.valueOf(q.peek()));

                } else if (order.equals("back")) {
                    if (q.isEmpty()) bw.write("-1");
                    else bw.write(String.valueOf(q.peekLast()));

                }
                bw.newLine();
            }
        }
        bw.close();
        br.close();
    }
}
