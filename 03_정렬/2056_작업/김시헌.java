// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 60140 KB , 시간 : 396 ms

import java.io.*;
import java.util.*;

public class Gold_2056 {    // 작업
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Work[] workList = new Work[N+1];

        // 이전 행위가 이후에 영향 미치므로 dp로 점화식짜서 해결하는 것이 적합해보임
        int[] dp = new int[N+1];    // i번째 작업 끝냈을때 걸린시간을 담는 배열

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            dp[i] = time;
            int preNum = Integer.parseInt(st.nextToken());
            int[] preWorks = new int[preNum];
            if (preNum != 0) {
                for (int j = 0; j < preNum; j++) {
                    preWorks[j] = Integer.parseInt(st.nextToken());
                }
            }
            workList[i] = new Work(i, time, preWorks);
        }

        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            if (workList[i].preWorks.length == 0) dp[i] = workList[i].time; // 선후작업 없으면 냅둠 (코드 작성해논거 사실 무의미)
            else {
                int time = workList[i].time;            // i작업의 시간
                int[] preWorks = workList[i].preWorks;  // i작업의 선후관계
                int preWorkTime = 0;                    // 선후작업들의 최대시간 담아놀 변수
                for (int prework : preWorks) {
                    if (dp[prework] > preWorkTime) preWorkTime = dp[prework]; // 최대시간 갱신
                }
                dp[i] = time + preWorkTime;         // 작업후 시간 기록
            }
            if (dp[i] > maxTime) maxTime = dp[i];   // 마지막 작업이 선후관계 없을때 대비용 maxTime
        }

        System.out.println(maxTime);
    }

    static class Work {
        int idx, time;
        int[] preWorks;

        Work(int idx, int time, int[] preWorks) {
            this.idx = idx; this.time = time;
            this.preWorks = preWorks;
        }
    }
}
