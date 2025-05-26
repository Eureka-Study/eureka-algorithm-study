package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Gold3_2457_greedy_retry {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Flower[] flowers = new Flower[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int fromMonth = Integer.parseInt(st.nextToken());
            int fromDay = Integer.parseInt(st.nextToken());
            int toMonth = Integer.parseInt(st.nextToken());
            int toDay = Integer.parseInt(st.nextToken());

            flowers[i] = new Flower(fromMonth, fromDay, toMonth, toDay);
        }

        // 꽃이 지는 날을 기준으로 오름차순 정렬
        Arrays.sort(flowers, new Comparator<Flower>() {

            @Override
            public int compare(Flower o1, Flower o2) {
                if (o1.toMonth == o2.toMonth) {
                    return o1.toDay - o2.toDay;
                } else {
                    return o1.toMonth - o2.toMonth;
                }
            }

        });

        for (Flower f : flowers) {
            System.out.println(f);
        }

        System.out.println();

        ArrayDeque<Flower> stack = new ArrayDeque<Flower>();

        stack.push(flowers[0]);

        int i = 1;
        for ( ; i < N; i++) {
            Flower f = stack.peek();
            if (flowers[i].fromMonth < 3) {
                if (flowers[i].toMonth > 2) {
                    if (flowers[i].toMonth > f.toMonth || flowers[i].toMonth == f.toMonth && flowers[i].toDay > f.toDay) {
                        stack.pop();
                    }
                    stack.push(flowers[i]);
                    break;
                }
            }
        }

        System.out.println(stack.peek());
        System.out.println();

        for (int j = i + 1; j < N; j++) {
            Flower f = stack.peek();

            if (f.toMonth == 12) break;

            if (flowers[j].fromMonth < f.toMonth || (flowers[j].fromMonth == f.toMonth && flowers[j].fromDay <= f.toDay)) {
                if (flowers[j].toMonth > f.toMonth || (flowers[j].toMonth == f.toMonth && flowers[j].toDay > f.toDay)) {
                    while (flowers[j].fromMonth < f.fromMonth || (flowers[j].fromMonth == f.fromMonth && flowers[j].fromDay <= f.fromDay)) {
                        stack.pop();
                        f = stack.peek();
                    }
                    stack.push(flowers[j]);
                }
            }
        }

        List<Flower> flowerList = new ArrayList<Flower>(stack);
        for (Flower f : flowerList) {
            System.out.println("[" + f.fromMonth + ", " + f.fromDay + ", " + f.toMonth + ", " + f.toDay + "]");
        }

        System.out.println(stack.size());

    }

    static class Flower {
        int fromMonth;
        int fromDay;
        int toMonth;
        int toDay;

        Flower(int fromMonth, int fromDay, int toMonth, int toDay) {
            this.fromMonth = fromMonth;
            this.fromDay = fromDay;
            this.toMonth = toMonth;
            this.toDay = toDay;
        }

        @Override
        public String toString() {
            return "[" + fromMonth + ", " + fromDay + ", " + toMonth + ", " + toDay + "]";
        }
    }
}
