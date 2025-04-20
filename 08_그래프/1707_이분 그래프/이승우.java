
//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 276180 KB , 시간 : 1132 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class 이분그래프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) { // 테스트 케이스
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());

            List<Integer>[] list = new ArrayList[V + 1];

            for (int v = 1; v <= V; v++) {
                list[v] = new ArrayList<>();
            }

            for (int e = 1; e <= E; e++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());

                // 순서 없는 그래프이므로 양쪽 진입 가능
                list[u].add(v);
                list[v].add(u);
            }

            int[] colors = new int[V + 1];
            boolean isBipartite = true;

            for (int i = 1; i <= V; i++) {
                if (colors[i] == 0) { // 아직 확인을 안했는지 확인을
                    Deque<Integer> queue = new ArrayDeque<>();
                    queue.add(i);
                    colors[i] = 1;

                    while (!queue.isEmpty()) {
                        int curr = queue.poll();
                        for (int next : list[curr]) { // 현 위치에서 다음 위치 확인
                            if (colors[next] == 0) { // 다음 경로가 아직 확인을 안했는지 확인을
                                colors[next] = 3 - colors[curr]; // 다음 경로는 다른 색으로 칠하기
                                queue.add(next);
                            } else if (colors[next] == colors[curr]) { // 이미 색이 칠해져있는데 같은 색일 경우 불가능한 경우임(이분 그래프가 아니라는
                                                                       // 의미)
                                isBipartite = false;
                                break;
                            }
                        }
                        if (!isBipartite)
                            break;
                    }
                }
                if (!isBipartite)
                    break;
            }

            sb.append(isBipartite ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }
}