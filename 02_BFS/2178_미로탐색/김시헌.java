// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 12116 KB , 시간 : 80 ms

import java.io.*;
import java.util.*;

public class Main {  // 미로 탐색
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visit = new boolean[N+1][M+1];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char chr = line.charAt(j);
                map[i + 1][j + 1] = chr - '0';  // char -> int 변환
            }
        }

        bfs(1,1);   // (1,1) 부터 이동 시작

        System.out.println(ans);

    }

    static void bfs(int startY, int startX) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(startY, startX, 1));
        visit[startY][startX] = true;

        //   하 우 좌 상 (밑으로 & 오른쪽으로 가는게 최선이라 이렇게 배치해봄)
        int[] dy = { 1, 0, 0, -1 };
        int[] dx = { 0, 1, -1, 0 } ;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.y == N && node.x == M) {   // 만약 이동한 좌표가 (N,M)이면 break, 정답=cnt
                ans = node.cnt;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];

                if (ny <= 0 || nx <= 0 || ny > N || nx > M || visit[ny][nx]) continue;
                if (map[ny][nx] == 0) continue;
                q.offer(new Node(ny, nx, node.cnt + 1));
                visit[ny][nx] = true;
            }
        }
    }

    static class Node {
        int y, x, cnt;      // 현재 노드에 왔을때의 이동칸수를 cnt에 저장한다.
        Node(int y, int x, int cnt) {
            this.y = y; this.x = x; this.cnt = cnt;
        }
    }
}
