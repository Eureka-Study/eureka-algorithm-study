// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16552 KB , 시간 : 132 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int N;
    static char[][] map;
    static char[][] colorBlindMap;
    static boolean[][] mapVisited;
    static boolean[][] colorBlindMapVisited;

    static int count = 0;
    static int blindCount = 0;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        colorBlindMap = new char[N][N];
        mapVisited = new boolean[N][N];
        colorBlindMapVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = line.charAt(j);
                if (c == 'R' || c == 'G') { // 색약인 map에는 R과 G 둘 다 R로 저장되게 했다.
                    colorBlindMap[i][j] = 'R';
                } else {
                    colorBlindMap[i][j] = 'B';
                }
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bfs(i, j);
                blindBfs(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count + " ").append(blindCount);
        System.out.println(sb);
    }

    // 일반인
    static void bfs(int y, int x) {
        if (mapVisited[y][x]) return;

        Queue<int[]> queue = new ArrayDeque<>();

        char color = map[y][x];
        queue.offer(new int[]{y,x});
        mapVisited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || mapVisited[ny][nx] || map[ny][nx] != color) continue;

                queue.offer(new int[]{ny,nx});
                mapVisited[ny][nx] = true;
            }
        }
        count++;
    }

    // 색약인
    static void blindBfs(int y, int x) {
        if (colorBlindMapVisited[y][x]) return;

        Queue<int[]> queue = new ArrayDeque<>();

        char color = colorBlindMap[y][x];
        queue.offer(new int[]{y,x});
        colorBlindMapVisited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || colorBlindMapVisited[ny][nx] || colorBlindMap[ny][nx] != color) continue;

                queue.offer(new int[]{ny,nx});
                colorBlindMapVisited[ny][nx] = true;
            }
        }
        blindCount++;
    }
}