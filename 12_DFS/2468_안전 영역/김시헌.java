// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 33592 KB , 시간 : 208 ms

import java.io.*;
import java.util.*;

public class Main {  // 안전 영역
    static int N;
    static int[][] area;
    static boolean[][] visit;
    static int maxHeight = 0;
    static int minHeight = 101;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
    static int idx;
    static int ans = 1; // "아무 지역도 물에 잠기지 않을 수도 있다"

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        area = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int height = Integer.parseInt(st.nextToken());
                if (height > maxHeight) maxHeight = height;
                if (height < minHeight) minHeight = height;
                area[i][j] = height;
            }
        }

        visit = new boolean[N][N];

        idx = minHeight;            // 수위는 최저높이부터 시작
        while (idx < maxHeight) {   // 최대높이 전까지
            visit = new boolean[N][N];
            int count = 0;  // 이번 수위에서의 안전지역 개수

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j] && area[i][j] > idx) { // 아직 방문도 안했고 수위보다 높으면
                        bfs(i, j);  // bfs 돌리기
                        count++;
                    }
                }
            }

            if (count > ans) ans = count;

            idx++;
        }

        System.out.println(ans);
    }

    static void bfs(int startY, int startX) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(startY, startX));
        visit[startY][startX] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || area[ny][nx] <= idx) continue;

                q.offer(new Node(ny, nx));
                visit[ny][nx] = true;
            }
        }
    }

    static class Node {
        int y, x;
        Node(int y, int x) {
            this.y = y; this.x = x;
        }
    }
}
