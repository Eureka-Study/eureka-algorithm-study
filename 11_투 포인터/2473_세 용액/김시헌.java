// 언어 : JAVA , (성공/실패) : 1/3 , 메모리 : 14592 KB , 시간 : 752 ms

import java.io.*;
import java.util.*;

public class Main {    // 세 용액
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] liquid = new long[N];
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquid);

        long ans = Long.MAX_VALUE;
        long[] ansList = new long[3];

        for (int i = 0; i < N-2; i++) {
            long a = liquid[i];
            for (int j = i+1; j < N-1; j++) {
                long b = liquid[j];

                // 3번째 용액은 이분탐색으로 찾기
                int min = j+1;
                int max = N-1;

                long sum = 0;
                while (min <= max) {
                    int mid = (min + max) / 2;
                    long c = liquid[mid];
                    sum = a + b + c;

                    if (Math.abs(sum) < ans) {
                        ans = Math.abs(sum);
                        ansList = new long[]{a, b, c};
                    }

                    if (sum > 0) {
                        max = mid - 1;
                    } else if (sum < 0) {
                        min = mid + 1;
                    } else {    // sum == 0
                        System.out.println(a + " " + b + " " + c);
                        return;
                    }
                }
            }
        }

        System.out.println(ansList[0] + " " + ansList[1] + " " + ansList[2]);
    }
}
