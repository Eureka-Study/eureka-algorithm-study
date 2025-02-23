// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15912 KB , 시간 : 144ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 정유민 {
    static int t,m,n,k;
    static int[][] map;
    static boolean[][] visit;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int cnt=0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());//가로 길이 (열 개수)
            n = Integer.parseInt(st.nextToken());//세로 길이 (행 개수)
            k = Integer.parseInt(st.nextToken());//배추 개수

            map = new int[n][m];
            visit = new boolean[n][m];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1; // 배추 위치에 1 표시, 없는 곳은 0
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 1 || visit[i][j])continue;  // 배추가 안 심어져 있거나, bfs로 방문됐을 시 패스!
                    cnt++;
                    bfs(i,j);
                }
            }
            System.out.println(cnt); //각 테케 당 최소의 지렁이 마리 수
        }
    }
    static void bfs(int y, int x) {
        Deque<Node> queue = new ArrayDeque<>();

        queue.offer(new Node(y, x));
        visit[y][x]=true; // queue 에서 꺼낸거 방문 췍

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) { //사방탐색을 한다.
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visit[ny][nx] || map[ny][nx]!=1) { // 땅 안넘어가는지, 방문했었는지, 배추 있는지 확인
                    continue;
                }
                visit[ny][nx]=true; //인접한 배추들도 방문 췍
                queue.offer(new Node(ny, nx));
            }
        }
    }

    static class Node{
        int y,x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
