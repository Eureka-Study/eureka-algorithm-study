// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 50632 KB , 시간 : 272ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정유민 {
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int n,m;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        int totCnt = 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    totCnt++;
                    int dfsCnt = dfs(i, j,1);
                    if(max< dfsCnt)
                        max = dfsCnt;
                }
            }
        }
        System.out.println(totCnt);
        System.out.println(max);
    }

    private static int dfs(int y, int x, int cnt) {

        visit[y][x]=true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >=n || nx <0 || nx>=m || visit[ny][nx] || map[ny][nx]==0) continue;
            cnt = dfs(ny,nx,++cnt);
        }
        return cnt;
    }
}
