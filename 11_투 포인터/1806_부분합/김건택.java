// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 23844 KB , 시간 : 244 ms

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gold4_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // j 가 앞으로 한 칸 씩 간다.
        // 합이 S 이상이면 i 가 뒤따라 간다.
        int i = 0, j = 0;
        int sum = arr[0];
        int minLength = Integer.MAX_VALUE;

        while(true) {
            // 부분합이 S 이상이라면 최소 길이 비교하고, i 를 증가
            if(sum >= S) {
                int length = j - i + 1;
                minLength = Math.min(length, minLength);

                // i 전진
                sum -= arr[i];
                i++;
            } else {
                if(j == N-1) break; // j 가 끝까지 갔으면 종료

                // j 전진
                j++;
                sum += arr[j];
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLength);
        }

    }
}
