// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 123296 KB , 시간 : 364 ms

import java.io.*;
import java.util.*;

public class Main {    // 줄 세우기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] childrenList = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            childrenList[i] = Integer.parseInt(st.nextToken());
        }

        // 힌트: 다른 줄세우기 문제와 다르게, 이번 조건은 "맨앞 / 맨뒤 로의 이동"만 가능한 점
        //      이 점을 생각하여, 연속적으로 가장 길게 증가하는 수열을 구하고, 해당 부분을 제외하고 나머지를 옮겨줘야함
        //      즉, "아이들 이동"에 초점을 맞추기보다, "이동해야할 아이들 개수"에 초점을 맞춰야한다

        int[] dp = new int[N+1];
        int maxCon = 0; // 최대 연속 길이

        for (int i = 0; i < N; i++) {  // children 배열을 쭉 순회하면서, 현재 값의 연속되었다 치고 dp에 1 더해준다
            int c = childrenList[i];
            dp[c] = dp[c-1] + 1;

            maxCon = Math.max(maxCon, dp[c]);   // 이번 dp값이 더 크면 maxCon 경신
        }

        System.out.println(N - maxCon);
    }
}
