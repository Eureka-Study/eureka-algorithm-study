// 언어 : JAVA , (성공/실패) : 1/4 , 메모리 : 22324 KB , 시간 : 184 ms

import java.io.*;
import java.util.*;

public class Main {    // 부분합
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] list = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        int len = N+1;

        // 1. 0번부터 시작해서 합 S까지인 길이 찾기 -> 초기 len값 설정 & 답 0인거 거르기
        int partSum = 0;
        for (int i = 0; i < N; i++) {
            partSum += list[i];
            if (partSum >= S) {
                len = i+1;
                break;
            }
        }

        if (partSum < S) {  // S가 made가 안되면, 0 출력
            System.out.println(0);

        } else {
            // 2. 초기 len값부터 투포인터로 사이클 돌기 시작
            int sIdx = 0;
            int eIdx = len-1;

            while (eIdx < N) {

                partSum -= list[sIdx];      // sIdx의 값 빼기
                sIdx++;                     // sIdx 한칸 이동

                if (partSum >= S) {         // 2-1. 그랬는데도 부분합이 S 이상이다?
                    len--;                  // 그럼 len 하나 줄이고
                    continue;               // 넘어가

                } else {                    // 2-2. 부분합 S 미만
                    eIdx++;                 // 그럼 eIdx 한칸 이동
                    if (eIdx >= N) break;       // N 됐으면 그만

                    partSum += list[eIdx];  // 부분합에 이동한 eIdx의 값 더해주기
                }
            }

            System.out.println(len);
        }
    }
}