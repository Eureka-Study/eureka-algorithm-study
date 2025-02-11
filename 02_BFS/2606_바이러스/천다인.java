//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 1442 KB , 시간 : 100 ms
import java.io.*;
import java.util.*;

class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int compNum = Integer.parseInt(br.readLine());
        int linkNum = Integer.parseInt(br.readLine());

        graph = new ArrayList[compNum + 1];
        for (int i = 1; i <= compNum; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < linkNum; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[compNum + 1];

        System.out.println(bfs(1));
    }

    static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        int infectedCount = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    infectedCount++;
                }
            }
        }
        return infectedCount;
    }
}