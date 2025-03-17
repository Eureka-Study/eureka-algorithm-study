// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 340328 KB , 시간 : 2544 ms

import java.io.*;
import java.util.*;

public class Main {    // 선 긋기
    static int N;
    static int[][] lines;
    static int length = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lines = new int[N][2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lines, (o1, o2) -> { // 오름차순 정렬: 맨 왼쪽 점(선)부터 시작, 겹침 방지
            if (o1[0] == o2[0]) {       // left값 같으면
                return o1[1] - o2[1];   // right값 작은순으로
            }
            return o1[0] - o2[0];
        });

        int curMin = lines[0][0];
        int curMax = lines[0][1];
        int curLength = curMax - curMin;
        for (int i = 1; i < N; i++) {       // 경우의수 3가지: cur (1)밖에, (2)겹쳐서, (3)안에(이건 스킵)
            if (lines[i][0] >= curMax) {    // (1) 밖에
                length += curLength;        // 기존 cLen을 길이에 더해주고
                curMin = lines[i][0];       // cMin 갱신
                curMax = lines[i][1];       // cMax 갱신
                curLength = curMax - curMin;    // cLen 갱신

            } else if (lines[i][1] > curMax) {  // (2) 겹쳐서
                curMax = lines[i][1];           // cMax 갱신
                curLength = curMax - curMin;    // cLen 갱신
            }
        }
        length += curLength;    // 마지막 cLen 처리

        System.out.println(length);
    }
}
