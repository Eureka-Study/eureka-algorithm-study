// 언어 : JAVA , (성공/실패) : 0/7 , 메모리 : 초과 KB , 시간 : ? ms

import java.io.*;
import java.util.*;

public class Main {    // 합이 0
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> A = new ArrayList<>(N);
        Map<Integer, Integer> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (map.containsKey(num)) {
                map.replace(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
                A.add(num);
            }
        }

        Collections.sort(A);

        long ans = 0;
        int num1 = 0, num2 = 0, num3 = 0;
        long val1 = 0, val2 = 0, val3 = 0;

        // (0, 0, 0) 경우 먼저 따로 처리
        if (map.containsKey(0)) {
            long val0 = map.get(0);
            ans += val0 * (val0 - 1) * (val0 - 2) / 6;
        }

        for (int i = 0; i < A.size(); i++) {
            num1 = A.get(i);
            if (num1 >= 0) break;   // num1부터 0 이상인거는 제껴

            val1 = map.get(num1);
            if (val1 > 1) {   // ex. (-4, -4, 8) 경우
                int counter = -(num1 * 2);
                if (map.containsKey(counter)) {
                    ans += (val1 * (val1-1) / 2) * map.get(counter);  // pC2 * qC1
                }
            }

            for (int j = i + 1; j < A.size(); j++) {
                num2 = A.get(j);
                val2 = map.get(num2);

                int sum = num1 + num2;
                num3 = -sum;

                if (num3 < num2) break; // ex. (-4, 3, 1) 경우

                if (map.containsKey(num3)) {
                    val3 = map.get(num3);

                    if (num3 == num2) {     // ex. (-4, 2, 2) 경우
                        ans += val1 * (val3 * (val3-1) / 2);  // pC1 * rC2

                    } else {                // ex. (-4, 1, 3) 경우
                        ans += val1 * val2 * val3;            // pC1 * qC1 * rC1
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
