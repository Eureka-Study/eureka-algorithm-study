// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 52128 KB , 시간 : 328ms


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class 정유민 {
    static int[][] map;
    static boolean[][] visit;
    static int board;
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            board = sc.nextInt();
            map = new int[board][board];
            visit = new boolean[board][board];

            int startY = sc.nextInt();
            int startX = sc.nextInt();
            int targetY = sc.nextInt();
            int targetX = sc.nextInt();

            System.out.println(bfs(startY, startX, targetY, targetX));
        }

        sc.close();
    }

    static int bfs(int startY, int startX, int targetY, int targetX) {
        if (startY == targetY && startX == targetX) return 0;

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startY, startX, 0));
        visit[startY][startX] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int d = 0; d < 8; d++) {
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= board || nx >= board || visit[ny][nx]) continue;

                if (ny == targetY && nx == targetX) return node.move + 1;

                queue.offer(new Node(ny, nx, node.move + 1));
                visit[ny][nx] = true;
            }
        }
        return -1; // 사실상 의미없음
    }

    static class Node {
        int y, x, move;

        Node(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }
}
