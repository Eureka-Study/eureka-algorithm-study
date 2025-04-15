
// 언어 : JAVA , (성공/실패) : 2/2 , 메모리 : 51552 KB , 시간 : 432 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 네트워크연결 {

    public static class Node implements Comparable<Node> {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());

        List<Node>[] graph = new ArrayList[N + 1];

        for (int n = 1; n <= N; n++) {
            graph[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()),
                    cost = Integer.parseInt(st.nextToken());

            // 단방향이 아닌 양방향이라 어느쪽이든 접근할 수 있게 양쪽 다 넣는다.
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 가장 저렴하게 연결을 해야하므로 넣는 순간 가장 저렴한 것을 뽑으면 가장 적은 비용이 나온다.
        pq.add(new Node(1, 0)); // 메인 컴퓨터를 넣는다.(메인은 1이라고 고정, 아무런 연결을 안했기 때문에 0)

        int total = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.to]) // 이미 연결이 되었는지 확인
                continue;
            visited[current.to] = true;

            total += current.cost;

            for (Node next : graph[current.to]) { // 연결 시도
                if (!visited[next.to])
                    pq.add(next);
            }
        }

        System.out.println(total);
    }
}
