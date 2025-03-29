// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 11608 KB , 시간 : 68 ms

import java.io.*;
import java.util.*;

public class Main {  // 지름길 (리팩토링 버전)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] dp = new int[D+1];  // 0에서 각 포인트까지 걸리는 거리 저장할 dp
        for (int i = 0; i <= D; i++) {
            dp[i] = i;        // 초기세팅은 지름길 없이 쌩으로 가는것 (ex. dp[50]은 0부터 50까지니까 걸리는 거리 = 50)
        }

        Shortcut[] list = new Shortcut[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            // (리팩토링) start, end 같은 지름길이 있는지 체크 굳이 안해도 될듯
            list[i] = new Shortcut(start, end, dist);
        }

        for (int i = 1; i <= D; i++) {
            dp[i] = Math.min(dp[i], dp[i-1] + 1);   // 1) 이번 인덱스의 초기dp값 vs 갱신되고있는dp값 비교해서 작은거 선택

            for (int j = 0; j < list.length; j++) { // 2) 지름길 리스트에서
                if (list[j].end == i) {             // 이번 인덱스가 end 값인 것이 있다면
                    dp[i] = Math.min(dp[i], dp[list[j].start] + list[j].dist);  // (1)에서 선택한 dp값과 지름길 start지점까지 기록했던 dp값에 dist를 더한값 비교해서 작은거 선택
                }
            }

        }

        System.out.println(dp[D]);

    }

    static class Shortcut {
        int start, end, dist;
        Shortcut(int start, int end, int dist) {
            this.start = start; this.end = end; this.dist = dist;
        }
    }
}


/*
import java.io.*;
import java.util.*;

public class Silver_1446 {  // 지름길 (초기버전. 테케와 testcase.ac는 다 통과했으나, 제출땐 실패)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] dp = new int[D+1];  // 0에서 각 포인트까지 걸리는 거리 저장할 dp
        for (int i = 0; i <= D; i++) {
            dp[i] = i;        // 초기세팅은 지름길 없이 쌩으로 가는것 (ex. dp[50]은 0부터 50까지니까 걸리는 거리 = 50)
        }

        ArrayList<Shortcut> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            // start, end 같은 지름길이 있는지 체크
            boolean isAdd = true;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).start == start && list.get(j).end == end) {
                    if (list.get(j).dist < dist) {  // 이번께 거리 더 긴거면 추가 안할거임
                        isAdd = false;  // 추가 안할거라는 플래그
                    }
                }
            }

            if (isAdd && end <= D && dist < (end-start)) {
                list.add(new Shortcut(start, end, dist));
            }
        }

        // for (int i = 0; i < list.size(); i++) {
        //     System.out.println(list.get(i).start + "," + list.get(i).end + "," + list.get(i).dist);
        // }
        // System.out.println();

        list.sort( (o1, o2) -> o1.end - o2.end);

        // for (int i = 0; i < list.size(); i++) {
        //     System.out.println(list.get(i).start + "," + list.get(i).end + "," + list.get(i).dist);
        // }

        for (int i = 1; i <= D; i++) {
            boolean isEnd = false;  // 이번 인덱스가 end인게 있는지 체크하는 플래그
            int start = 0;
            int dist = 0;

            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).end == i) {
                    isEnd = true;
                    start = list.get(j).start;
                    dist = list.get(j).dist;
                }
            }

            if (isEnd) {
                dp[i] = Math.min(dp[i-1] + 1, dp[start] + dist);
            } else {
                dp[i] = dp[i-1] + 1;
            }

        }

        System.out.println(dp[D]);

    }

    static class Shortcut {
        int start, end, dist;
        Shortcut(int start, int end, int dist) {
            this.start = start; this.end = end; this.dist = dist;
        }
    }
}
 */