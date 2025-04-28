// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11932 KB , 시간 : 84 ms

import java.io.*;
import java.util.*;

public class Main {     // 트럭
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 트럭 개수
        int W = Integer.parseInt(st.nextToken());   // 다리 길이
        int L = Integer.parseInt(st.nextToken());   // 최대 하중
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] bridge = new int[W];

        bridge[0] = A[0];   // 0번트럭 넣고 시작
        int truckIdx = 1;   // 다음트럭은 1번
        int weightSum = A[0];   // 현재 하중합 = 0번트럭 무게

        int time = 1;       // 시간은 1초 지났음

        while (true) {

            if (bridge[W-1] != 0) {         // 다리끝(W-1번 위치)에 트럭 있으면
                weightSum -= bridge[W-1];   // 트럭 탈출시킴
            }

            for (int i = W-1; i >= 1; i--) {    // 다리 W-1번 위치 ~ 1번 위치까지
                bridge[i] = bridge[i-1];        // 이전 칸의 것으로 1칸씩 옮겨주기
            }
            bridge[0] = 0;

            if (truckIdx < N && weightSum + A[truckIdx] <= L) { // 다음트럭 입차시 하중합이 L 이하면 (물론 트럭번호는 N보다 작아야)
                bridge[0] = A[truckIdx];        // 다리 0번위치를 다음트럭으로 채우기
                weightSum += A[truckIdx];       // 하중합 갱신
                truckIdx++;                     // 트럭번호 +1
            }

            time++;     // 시간 +1

            if (truckIdx >= N-1 && weightSum == 0) break;   // 트럭번호가 끝번 이상이며, 하중합이 0이면(다리가 비었으면) 끝내기
        }

        System.out.println(time);
    }
}
