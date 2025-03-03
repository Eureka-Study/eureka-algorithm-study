// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 12640 KB , 시간 : 84 ms

import java.io.*;
import java.util.*;

public class Main {   // 적록색약
    static int N;
    static char[][] map1, map2;
    static boolean[][] visit;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
    // static int[] cnt1 = {0, 0, 0};   // R, G, B
    // static int[] cnt2 = {0, 0, 0};   // R, R, B
    static int cnt1 = 0;
    static int cnt2 = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map1 = new char[N][N];
        map2 = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char point = line.charAt(j);
                map1[i][j] = point;
                if (point == 'G') map2[i][j] = 'R';     // 적록색약은 G -> R로 취급
                else map2[i][j] = point;
            }
        }

        // 일반인
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    char color = map1[i][j];
                    bfs(i,j, map1, color);
                    cnt1++;
                }
            }
        }

        // 적록색약 (다른점은 map2, cnt2 차이밖에 없음. 차라리 함수로 만들었으면 코드 줄은 줄였겠다.)
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    char color = map2[i][j];
                    bfs(i,j, map2, color);
                    cnt2++;
                }
            }
        }

        bw.write(String.valueOf(cnt1) + " " + String.valueOf(cnt2));
        bw.flush();
        bw.close();
        br.close();
    }

    // 일반적인 bfs와 다르게, map과 bfs를 돌리는 타겟색상(RGB)가 인자로 들어간다
    static void bfs(int startY, int startX, char[][] map, char tgtColor) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startY, startX));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) continue;
                if (map[ny][nx] == tgtColor) {      // 다음 노드의 색상이 타겟색상과 같다면
                    queue.offer(new Node(ny, nx));
                    visit[ny][nx] = true;
                }
            }
        }
    }

    static class Node {
        int y,x;

        Node(int y, int x) {
            this.y = y; this.x = x;
        }
    }
}
