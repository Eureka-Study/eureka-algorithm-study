// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 283396 KB , 시간 : 2200 ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int[][] lines = new int[N][2];
        for (int i = 0; i < N; i++) { // 선들의 x, y 좌표를 입력 받아 2차원 배열에 저장한다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines[i][0] = x;
            lines[i][1] = y;
        }

        // 선들의 x 좌표 기준으로 오름차순 정렬한다. x 좌표가 같을 경우 y 좌표 기준으로 오름차순 정렬한다.
        Arrays.sort(lines, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        int length = 0; // 선의 총 길이

        // 첫 번째 선의 x, y 좌표
        int x = lines[0][0];
        int y = lines[0][1];

        for (int i = 1; i < N; i++) {
            // 새로 입력 받은 좌표
            int newX = lines[i][0];
            int newY = lines[i][1];

            if (newX <= y) { // 기존 선과 새로운 선이 겹치는 경우
                if (newY >= y) {
                    y = newY;
                }
            } else { // 기존 선과 겹치지 않아 새로운 선이 시작되는 경우
                length = length + (y - x);
                x = newX;
                y = newY;
            }
        }

        length = length + (y - x);

        bw.write(length + "\n");
        bw.flush();
        bw.close();
        br.close();

    }
}
