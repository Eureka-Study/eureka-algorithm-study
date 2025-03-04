import java.io.*;
import java.util.StringTokenizer;

public class 플로이드 {
    // Integer.MAX_VALUE는 알고리즘에 의해 범위를 넘어설 수 있으므로 사용해서는 안됨
    private static final int MAX = 999_999_999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] city = new int[n+1][n+1];

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                city[i][j] = (i==j) ? 0 : MAX;
            }
        }

        for (int i = 0; i < m; i++) {
            stz = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());

            // 문제 조건에 의해 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있으므로
            // 최소의 가중치를 갖는 노선 저장
            city[a][b] = Math.min(city[a][b], c);

        }

        for (int k = 1; k < n+1; k++) {
            for (int i = 1; i < n+1; i++) {
                for (int j = 1; j < n+1; j++) {
                    city[i][j] = Math.min(city[i][j],
                            city[i][k] + city[k][j]);
                }
            }
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                // 갈 수 없는 경우 0을 출력
                int w = city[i][j] < MAX ? city[i][j] : 0;
                bw.write(w + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}