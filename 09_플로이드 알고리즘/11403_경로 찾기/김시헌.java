// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 13180 KB , 시간 : 124 ms

import java.io.*;
import java.util.*;

public class Main {     // 경로 찾기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드-워셜 알고리즘 => k 지점 경유: Dij = min(Dij, Dik + Dkj) => 시간복잡도 O(N^3) 예상
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][j] == 1) continue;
                    if (graph[i][k] == 1 && graph[k][j] == 1) graph[i][j] = 1;  // 이 문제는 최단경로 계산은 필요없고, 걍 되면 1로 갱신만 해주면 됨
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
