
// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 54512KB , 시간 : 524ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 중량제한 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        List<int[]>[] list = new ArrayList[N + 1];

        for (int n = 1; n <= N; n++) {
            list[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");

            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken()),
                    C = Integer.parseInt(st.nextToken());

            // A -> B, B -> A로 양방향으로 갈 수 있기 때문에 둘 다 넣는다.
            list[A].add(new int[] { B, C });
            list[B].add(new int[] { A, C });
        }

        st = new StringTokenizer(br.readLine(), " ");

        // factory1에서 factory2로 이동하는 최대 중량 찾기
        int factory1 = Integer.parseInt(st.nextToken()), factory2 = Integer.parseInt(st.nextToken()),
                max = Integer.MAX_VALUE; // 최대 중량을 찾는 것이지만 다리의 중량이 더 작으면 최대 중량이 작아지므로 처음은 최대값으로 잡아야한다.

        boolean[] visible = new boolean[N + 1];// 해당 지역을 돌았는지 확인하기위해
        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2) -> n2[1] - n1[1]); // 지역을 오갈 때 최대한 중량을 높여야하므로 내림차순
        queue.offer(new int[] { factory1, Integer.MAX_VALUE }); // 처음 시작은 factory1지역에서 int 최대값으로 시작

        while (!queue.isEmpty()) { // 다 돌 때까지
            int[] q = queue.poll(); // 해당 섬에 도달했을 때의 현 상태 파악

            if (visible[q[0]]) // 이미 돈 부분이면 건너뛰기
                continue;

            max = Math.min(max, q[1]); // 해당 섬에 도달했을 때의 중량과 이전에 계산한 최소 중량 중 작은 값으로 변경
            visible[q[0]] = true; // 해당 섬 클리어

            if (q[0] == factory2) // 이 섬이 factory2이면 목적지에 도달한 것이다.
                break;

            for (int[] l : list[q[0]]) {
                if (!visible[l[0]]) { // 다음 섬으로 이동인데 그 섬이 아직 확인되지못한 섬인지 확인
                    queue.offer(l);
                }
            }
        }

        System.out.println(max);
    }
}
