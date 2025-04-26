
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16128 KB , 시간 : 188 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 계란으로계란치기 {
    static int N;
    static int maxDestroy = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] list = new int[N][2];

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            list[n] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
        }

        playing(list, 0, 0);

        System.out.println(maxDestroy);
    }

    static void playing(int[][] list, int hand, int destroy) {
        if (hand >= N) { // 마지막 순서 체크
            maxDestroy = Math.max(maxDestroy, destroy); // 혹시 모를 상황에 한번 더 체크
            return;
        }
        if (list[hand][0] <= 0) { // 현 계란이 깨졌을 경우 칠 수 없으므로 즉시 다음으로 넘어가기
            playing(list, hand + 1, destroy);
            return;
        }

        int newDestroy;
        for (int n = 0; n < N; n++) {
            if (n == hand || list[n][0] <= 0) // 자기 자신 그리고 이미 깨진 계란은 넘어가기
                continue;

            // 부디친 계란에게 서로 데미지 주기
            list[n][0] -= list[hand][1];
            list[hand][0] -= list[n][1];
            // 혹시 깨졌을 경우 카운트 추가
            newDestroy = destroy;
            if (list[n][0] <= 0)
                newDestroy++;
            if (list[hand][0] <= 0)
                newDestroy++;
            playing(list, hand + 1, newDestroy); // 계란끼리 부디쳤으므로 다음 계란으로 넘어가기

            // 경우의 수 중 이 계란을 말고 다른 계란을 친 경우도 분명 존재할 것이기 때문에 원상복구 후 다음 계란으로 넘어가기
            list[n][0] += list[hand][1];
            list[hand][0] += list[n][1];
        }

        // 마지막으로 최종 확인
        maxDestroy = Math.max(maxDestroy, destroy);
    }
}
