
// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 25372KB , 시간 : 300ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 물대기 {
    static class water implements Comparable<water> {
        int next, cost;

        water(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(water o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] field = new int[N];
        int[][] bridge = new int[N][N];

        for (int n = 0; n < N; n++) {
            field[n] = Integer.parseInt(br.readLine()); // 우물 파는 비용
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                bridge[i][j] = Integer.parseInt(st.nextToken()); // 물을 끌어오는 비용
            }
        }

        // 최소비용으로 모든 논에 물을 넣어야하므로 우선순위 큐를 통해 정렬
        PriorityQueue<water> queue = new PriorityQueue<>();
        boolean[] visible = new boolean[N]; // 이미 물이 있는지 확인하기위해
        int answer = 0;

        for (int n = 0; n < N; n++) {
            queue.offer(new water(n, field[n])); // 일단 우물을 파는 것에 대한 비용을 전부 넣는다. / 이게 가장 최소 비용일수도 있기 때문
        }

        while (!queue.isEmpty()) { // 전부 확인할 때까지
            water w = queue.poll(); // 현재 있는 것 중에 가장 비용이 작은 것을 빼낸다.

            if (visible[w.next]) // 이미 물이 된 곳이면 건너뛰기
                continue;

            visible[w.next] = true; // 물 넣기
            answer += w.cost; // 물을 넣는 비용 청구

            for (int n = 0; n < N; n++) {
                if (!visible[n]) { // 현 논에서 물이 없는 논으로 물을 끌어오는 비용을 넣어본다.
                    queue.offer(new water(n, bridge[w.next][n]));
                }
            }
        }

        System.out.println(answer);
    }
}
