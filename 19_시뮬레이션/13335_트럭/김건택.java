// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14388 KB , 시간 : 116 ms
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver1_13335 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] truck = new int[n];
        int[] bridge = new int[w];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            truck[i] = Integer.parseInt(st.nextToken());
        }

        bridge[0] = truck[0];
        int weightSum = truck[0];
        int nextIdx = 1;
        int time = 1;

        while(true) {
            // 한 칸 앞으로
            weightSum -= bridge[w-1];
            for (int i = w-1; i > 0; i--) {
                bridge[i] = bridge[i-1];
            }
            bridge[0] = 0;

            // 다음 트럭이 올라와도 최대하중을 넘지 않으면 올라오고, 다음 올라올 트럭을 가르킨다.
            if (nextIdx < n && weightSum + truck[nextIdx] <= L) {
                bridge[0] = truck[nextIdx];
                weightSum += truck[nextIdx];
                nextIdx++;
            }

            time++;

            // weightSum == 0 -> 다리 위에 트럭이 없다 -> 종료
            if (weightSum == 0) break;
        }

        System.out.println(time);
    }
}