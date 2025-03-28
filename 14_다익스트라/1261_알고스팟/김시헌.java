// 언어 : JAVA , (성공/실패) : 1/6 , 메모리 : 12352 KB , 시간 : 92 ms

import java.io.*;
import java.util.*;

public class Main {    // 알고스팟
    static int M, N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            for(int j = 1; j <= M; j++)
                map[i][j] = line.charAt(j-1) - '0';
        }

        System.out.println(bfs01(1, 1));
    }

    static int bfs01(int startY, int startX){
        Queue<Node> q = new PriorityQueue<>();  // 다익스트라 대신 우선순위큐로 풀이
        q.offer(new Node(startY, startX, 0));
        visited[startY][startX] = true;

        while (!q.isEmpty()){
            Node node = q.poll();

            if (node.y == N && node.x == M) {   // (N,M)에 도달하면 종료
                return node.temp;
            }

            for(int d = 0; d < 4; d++){
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];

                if (ny <= 0 || nx <= 0 || ny > N || nx > M || visited[ny][nx]) continue;

                if(map[ny][nx] == 0) q.offer(new Node(ny, nx, node.temp));
                else q.offer(new Node(ny, nx, node.temp + 1));      // 이동할 곳이 벽이면, 격파한 벽 수에 1 추가해서 큐에 넣기

                visited[ny][nx] = true;     // 방문처리
            }
        }

        return 0;
    }

    static class Node implements Comparable<Node>{
        int y, x, temp;

        Node(int y, int x, int temp){
            this.y = y; this.x = x; this.temp = temp;
        }

        @Override
        public int compareTo(Node node){
            return this.temp - node.temp;   // node의 격파한 벽 temp값 기준으로 오름차순 정렬
        }
    }
}
