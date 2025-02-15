// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 46324 KB , 시간 : 208 ms

import java.io.*;
import java.util.*;

public class Silver_7562 {  // 나이트의 이동
    static int T, L, x1, y1, x2, y2;
    static int ans;
    static boolean[][] board;
    //                상좌하 상좌상 상우상 상우하 하우상 하우하 하좌하 하좌상
    static int[] dy = { -1,  -2,  -2,  -1,   1,   2,   2,    1 };
    static int[] dx = { -2,  -1,   1,   2,   2,   1,  -1,   -2 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            L = Integer.parseInt(br.readLine());
            board = new boolean[L][L];

            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());      // 시작 x좌표
            y1 = Integer.parseInt(st.nextToken());      // 시작 y좌표
            st = new StringTokenizer(br.readLine());
            x2 = Integer.parseInt(st.nextToken());      // 목표 x좌표
            y2 = Integer.parseInt(st.nextToken());      // 목표 y좌표

            ans = 0;        // 정답 초기화
            bfs(y1, x1);    // bfs 시작

            bw.write(String.valueOf(ans));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void bfs(int y, int x) {
        if (y == y2 && x == x2) return; // 출발지와 목적지가 같으면 리턴

        Queue<int[]> queue = new ArrayDeque<>();    // 큐 선언
        int count = 0;      // 큐에 넣어줄 현재 이동횟수를 설정
        queue.offer(new int[]{y, x, count});    // 시작 y좌표, 시작 x좌표, 현재 이동횟수 (0) 큐에 넣기

        while (!queue.isEmpty()) {
            int[] moved = queue.poll();
            int tempY = moved[0];
            int tempX = moved[1];
            int tempCount = moved[2];

            for (int d = 0; d < 8; d++) {   // 8가지의 이동
                int ny = tempY + dy[d];
                int nx = tempX + dx[d];
                int nCount = tempCount + 1;

                if (ny < 0 || nx < 0 || ny >= L || nx >= L || board[ny][nx]) continue;
                if (ny == y2 && nx == x2) {   // 출발지와 목적지가 같으면 리턴
                    ans = nCount;
                    return;
                }
                queue.offer(new int[]{ny, nx, nCount}); // 다음 y좌표, 다음 x좌표, 현재 이동횟수 큐에 넣기
                board[ny][nx] = true;   // 해당 좌표 방문처리
            }
        }

    }
}
