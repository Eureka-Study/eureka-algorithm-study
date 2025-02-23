// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14248 KB , 시간 : 108 ms

import java.io.*;
import java.util.*;

public class Main {  // 유기농 배추
    static int T, M, N, K;
    static int[][] map;
    static boolean[][] visit;
    static ArrayList<Node> list;    // 지렁이 위치 Node들 담을 리스트
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {       // 각 테케별로
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());   // 가로길이 (col 수)
            N = Integer.parseInt(st.nextToken());   // 세로길이 (row 수)
            K = Integer.parseInt(st.nextToken());   // 지렁이 좌표 수

            map = new int[N][M];
            visit = new boolean[N][M];
            list = new ArrayList<>();

            for (int j = 0; j < K; j++) {    // map에 지렁이 넣는 인풋 받기
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
                list.add(new Node(y, x));   // 지렁이 위치 Node 리스트에 담기
            }

            cnt = 0;    // 각 테케별로 cnt 0으로 초기화하고 bfs 돌리기

            for (int j = 0; j < list.size(); j++) {
                Node worm = list.get(j);
                if (!visit[worm.y][worm.x]) {
                    bfs(worm.y, worm.x);
                    cnt += 1;   // bfs 한번 다 돌고나면 count에 1 더하기
                }
            }

            // 출력
            bw.write(String.valueOf(cnt));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int startY, int startX) {
        // 상 하 좌 우
        int[] dy = { -1, 1, 0, 0 };
        int[] dx = { 0, 0, -1, 1 };

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startY, startX));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx]) continue;
                if (map[ny][nx] == 0) continue;

                // 범위도 안벗어나고 해당 좌표도 1이면 (지렁이가 있는 것이면)
                queue.offer(new Node(ny, nx));
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
