// 언어 : JAVA , (성공/실패) : 0/2 , 메모리 : 42596 KB , 시간 : 364 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] distance;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            distance[a][b] = Math.min(distance[a][b], cost); // 출발지와 도착지가 같은데 비용이 덜 드는 간선을 선택
        }

        // 플로이드 알고리즘
        for (int k = 1; k <= N; k++) { // 경유지
            for (int i = 1; i <= N; i++) { // 출발지
                for (int j = 1; j <= N; j++) { // 도착지
                    // k 를 경유할때 i 에서 k 로 가는 비용과 k 에서 j 로 가는 비용이 INF 가 아니라면
                    if (distance[i][k] != INF && distance[k][j] != INF) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (distance[i][j] == INF) sb.append("0 ");
                else sb.append(distance[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }
}
