//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14748 KB , 시간 : 116 ms
import java.io.*;
import java.util.*;
class Node {
    int x, y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Main {
    static int n, m;
    static int[][] maze;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.print(bfs(0, 0));
    }
    static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>(); // 방문할 노드를 순서대로 저장하는 큐
        queue.add(new Node(x, y)); // 루트 노드 추가
        visited[x][y] = true; // 방문 표시

        while(!queue.isEmpty()) { // 큐가 비어있지 않은 동안 반복
            Node node = queue.poll(); // 현재 방문할 노드를 큐에서 꺼냄
            int curX = node.x; // 현재 위치의 x좌표
            int curY = node.y; // 현재 위치의 y좌표

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (maze[nx][ny] == 1 && !visited[nx][ny]) {
                        queue.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                        maze[nx][ny] = maze[curX][curY] + 1;
                    }
                }
            }
        }
        return maze[n - 1][m - 1];
    }
}
// (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수 구하기
// 1: 이동할 수 있는 칸 / 0: 이동할 수 없는 칸