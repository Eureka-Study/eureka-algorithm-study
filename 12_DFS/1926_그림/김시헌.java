// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 54988 KB , 시간 : 276 ms

import java.io.*;
import java.util.*;

public class Main {  // 그림
    static int N, M;
    static int[][] map;
    static boolean[][] visit;

    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    static int count = 0;
    static int maxSize = 0;
    static int tempSize = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 풀이
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] == 1) {   // 방문한적 없고, 해당 노드 값이 1이면
                    tempSize = 0;   // 사이즈변수 0으로 초기화하고
                    dfs(i, j);      // dfs 돌기
                    count++;        // 그림 개수 추가
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n").append(maxSize);
        System.out.println(sb);
    }

    static void dfs(int y, int x) {
        plus();     // 일단 dfs에 들어왔다는건, 그림 개수 1개 추가라는 것
        // System.out.println(y + "," + x + " size=" + tempSize);
        if (tempSize > maxSize) maxSize = tempSize; // 현재 사이즈가 maxSize보다 크면 갱신해주기

        visit[y][x] = true;     // 방문처리

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] == 0) continue;

            dfs(ny, nx);
        }
    }

    static void plus() {    // 사이즈 증가시키는 메소드
        tempSize++;
    }
}
