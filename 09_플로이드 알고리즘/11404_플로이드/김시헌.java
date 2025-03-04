// 언어 : JAVA , (성공/실패) : 1/6 , 메모리 : 42392 KB , 시간 : 308 ms

import java.io.*;
import java.util.*;

public class Gold_11404 {   // 플로이드
    static int N, M;
    static int[][] map;
    static int INF = 10000100;    // 100001로 해준게 문제였다..

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(map[i], INF);
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a][b] = Math.min(map[a][b], c);
        }

        // 플로이드-워셜: k 지점 경유 => Dij = min(Dij, Dik + Dkj)
        // 시간복잡도 => O(n^3)

        for (int k = 1; k < N+1; k++) {
            for (int i = 1; i < N+1; i++) {
                for (int j = 1; j < N+1; j++) {
                    if (i == j) continue;
                    if (map[i][k] == INF && map[k][j] == INF) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (i==j || map[i][j] == INF) sb.append("0");
                else sb.append(map[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
