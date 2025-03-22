// 언어 : JAVA , (성공/실패) : 1/5 , 메모리 : 61492 KB , 시간 : 432 ms

import java.io.*;
import java.util.*;

public class Silver_20922 {     // 겹치는 건 싫어
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;      // 시작 인덱스
        int max = 1;    // 현재 최장 연속부분수열 길이

        HashMap<Integer, Integer> map = new HashMap<>();    // 숫자, 빈도
        for (int i = 0; i < N; i++) {
            int num = arr[i];

            if (map.containsKey(num)) {         // 만약 이미 map에 num이 있다면
                map.put(num, map.get(num) + 1); // 빈도 1 더하기
            } else {
                map.put(num, 1);                // 없으면 빈도 1로 하고 추가
            }

            if (map.get(num) > K) {             // 확인해보니 빈도가 K를 초과했다면
                int len = i-s;                  // 현재 연속부분수열의 길이를 측정하고
                if (len > max) max = len;       // 만약 현재 최장길이를 초과한다면 갱신

                while(arr[s] != num) {          // s번째 숫자 부터 i번째 숫자인 num이 아닐때까지
                    map.put(arr[s],map.get(arr[s])-1);  // s번째 숫자 빈도 1씩 줄여주기
                    s++;    // 인덱스 증가
                }
                s++;    // 인덱스 증가하고 시작 인덱스 맞춰줌
                map.put(num, map.get(num) - 1);     // num의 빈도 하나 낮춰줌
            }
            if(i == N-1) {          // 맨 끝
                int len = i-s+1;    // 길이 측정
                if(len > max)
                    max = len;      // 갱신
            }
        }

        System.out.println(max);
    }
}
