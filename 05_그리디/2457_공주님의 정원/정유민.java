import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공주님의정원 {
    public static void main(String[] args) throws IOException {

        // 3월 1일 ~ 11월 30일

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start= Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
            int end= Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
            if(start <= 301 || end>1130)continue;
            arr[count][0] = start;
            arr[count][1] = end;
            count++;


        }

        Arrays.sort(arr, 0, count, (a,b)->{
            if(a[0] != b[0]) return a[0]-b[0];
            return b[1]-a[1]; // 같은 시작일일때 가장 늦게까지 피어 있는 꽃을 골아야지 더 넓은 범위를 커버 가능
        });


        // 필요 없는 꽃 거르고 정렬 끝 / 꽃 범위 체크 시작

        int ans = 0;
        int idx = 0;
        int cur = 301;
        int need = 1201;

        /*
        정원에 3월 1일부터 12월 1일까지 빈틈없이 꽃이 피어 있도록 최소한의 꽃을 고른다고 상상해보세요.

처음에는 3월 1일(301) 시점에 이미 피어있는 꽃들 중에서 가장 늦게까지 피어 있는 꽃을 하나 선택합니다. 그러면 그 꽃이 지는 날(예를 들면 5월 20일)이 바로 새 기준(cur)이 되고, 그날까지 정원은 끊김없이 꽃으로 덮이게 됩니다.

다음에는 ‘새 기준’ 이전 혹은 그날에 피기 시작한 꽃들 중에서 다시 가장 멀리까지 피는 꽃을 골라 선택 횟수를 하나 늘리고, 그 꽃이 지는 날을 또 새로운 기준으로 삼습니다. 이런 방식으로 기준을 계속 옮겨가며, 기준이 12월 1일(1201)에 도달할 때까지 반복합니다.

만약 어떤 시점에서 기준(cur)까지 피어있는 꽃이 하나도 없다면 그 사이에 빈틈이 생기는 것이므로, 곧바로 불가능(0)을 반환하고 과정을 종료합니다. 최종적으로 12월 1일까지 성공적으로 덮었다면 지금까지 고른 꽃의 개수(cnt)를 결과로 출력합니다.
         */
        while (cur < need) {
            int maxEnd = cur;
            while (idx < count && arr[idx][0] <= cur) {
                maxEnd = Math.max(maxEnd, arr[idx][1]);
                idx++;
            }

            if(maxEnd == cur) {
                System.out.println(0);
                return;
            }

            cur= maxEnd;
            ans++;
        }
        System.out.println(ans);
    }
}
