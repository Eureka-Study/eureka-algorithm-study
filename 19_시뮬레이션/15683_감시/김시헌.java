import java.io.*;
import java.util.*;

public class Main {   // 감시
    static int N, M;
    static int[][] office;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 풀이
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = office[i][j];
                if (num == 0 || num == 6 || num == 7) continue;

                switch (num) {      // CCTV 종류에 따라 최대로 비추는 방향 정해서 office 7로 채우기
                    case 1:
                        cctv1(i, j);
                        break;
                    case 2:
                        cctv2(i, j);
                        break;
                    case 3:
                        cctv3(i, j);
                        break;
                    case 4:
                        cctv4(i, j);
                        break;
                    case 5:
                        cctv5(i, j);
                        break;
                }
            }
        }

        // 답 출력
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (office[i][j] == 0) ans++;
            }
        }
        System.out.println(ans);
    }

    static void cctv1(int startY, int startX) {
        int[] v = new int[4];
        // v0 상
        v[0] = up(startY, startX);

        // v1 하
        v[1] = down(startY, startX);

        // v2 좌
        v[2] = left(startY, startX);

        // v3 우
        v[3] = right(startY, startX);

        int max = 0;
        for (int i = 0; i < 4; i++) {
            if (v[i] > max) max = v[i];
        }

        if (max == v[0]) {
            upRec(startY, startX);
        } else if (max == v[1]) {
            downRec(startY, startX);
        } else if (max == v[2]) {
            leftRec(startY, startX);
        } else {
            rightRec(startY, startX);
        }
    }

    static void cctv2(int startY, int startX) {

        // v1 열 감시
        int v1 = down(startY, startX) + up(startY, startX);

        // v2 행 감시
        int v2 = right(startY, startX) + left(startY, startX);

        if (v1 >= v2) {
            downRec(startY, startX);
            upRec(startY, startX);

        } else {
            leftRec(startY, startX);
            rightRec(startY, startX);
        }
    }

    static void cctv3(int startY, int startX) {
        int[] v = new int[4];

        // v0 상우
        v[0] = up(startY, startX) + right(startY, startX);

        // v1 상좌
        v[1] = up(startY, startX) + left(startY, startX);

        // v2 하좌
        v[2] = down(startY, startX) + left(startY, startX);

        // v3 하우
        v[3] = down(startY, startX) + right(startY, startX);

        int max = 0;
        for (int i = 0; i < 4; i++) {
            if (v[i] > max) max = v[i];
        }

        if (max == v[0]) {
            upRec(startY, startX);
            rightRec(startY, startX);
        } else if (max == v[1]) {
            upRec(startY, startX);
            leftRec(startY, startX);
        } else if (max == v[2]) {
            downRec(startY, startX);
            leftRec(startY, startX);
        } else {
            downRec(startY, startX);
            rightRec(startY, startX);
        }
    }

    // 화살표 없는쪽
    static void cctv4(int startY, int startX) {
        int[] v = new int[4];

        // v0 상-하좌우
        v[0] = down(startY, startX) + left(startY, startX) + right(startY, startX);

        // v1 하-상좌우
        v[1] = up(startY, startX) + left(startY, startX) + right(startY, startX);

        // v2 좌-상하우
        v[2] = up(startY, startX) + down(startY, startX) + right(startY, startX);

        // v3 우-상하좌
        v[3] = up(startY, startX) + down(startY, startX) + left(startY, startX);

        int max = 0;
        for (int i = 0; i < 4; i++) {
            if (v[i] > max) max = v[i];
        }

        if (max == v[0]) {
            downRec(startY, startX);
            leftRec(startY, startX);
            rightRec(startY, startX);
        } else if (max == v[1]) {
            upRec(startY, startX);
            leftRec(startY, startX);
            rightRec(startY, startX);
        } else if (max == v[2]) {
            upRec(startY, startX);
            downRec(startY, startX);
            rightRec(startY, startX);
        } else {
            upRec(startY, startX);
            downRec(startY, startX);
            leftRec(startY, startX);
        }

    }

    // 상하좌우 모두로 뻗어나가는 단 한가지 방식
    static void cctv5(int startY, int startX) {
        upRec(startY, startX);
        downRec(startY, startX);
        leftRec(startY, startX);
        rightRec(startY, startX);
    }

    /** 체크하는 메소드들 */
    static int up(int startY, int startX) {
        int v = 0;
        for (int i = startY; i >= 0; i--) { // 위로
            if (office[i][startX] == 6) break;
            if (office[i][startX] == 0) v++;
        }

        return v;
    }

    static int down(int startY, int startX) {
        int v = 0;
        for (int i = startY; i < N; i++) {  // 아래로
            if (office[i][startX] == 6) break;
            if (office[i][startX] == 0) v++;
        }
        return v;
    }

    static int left(int startY, int startX) {
        int v = 0;
        for (int i = startX; i >= 0; i--) { // 왼쪽으로
            if (office[startY][i] == 6) break;
            if (office[startY][i] == 0) v++;
        }
        return v;
    }

    static int right(int startY, int startX) {
        int v = 0;
        for (int i = startX; i < M; i++) {  // 오른쪽으로
            if (office[startY][i] == 6) break;
            if (office[startY][i] == 0) v++;
        }
        return v;
    }

    /** office에 기록하는 메소드들 */
    static void upRec(int startY, int startX) {
        for (int i = startY; i >= 0; i--) {
            if (office[i][startX] == 6) break;
            if (office[i][startX] == 0) office[i][startX] = 7;
        }
    }

    static void downRec(int startY, int startX) {
        for (int i = startY; i < N; i++) {
            if (office[i][startX] == 6) break;
            if (office[i][startX] == 0) office[i][startX] = 7;
        }
    }

    static void leftRec(int startY, int startX) {
        for (int i = startX; i >= 0; i--) {
            if (office[startY][i] == 6) break;
            if (office[startY][i] == 0) office[startY][i] = 7;
        }
    }

    static void rightRec(int startY, int startX) {
        for (int i = startX; i < M; i++) {
            if (office[startY][i] == 6) break;
            if (office[startY][i] == 0) office[startY][i] = 7;
        }
    }
}
