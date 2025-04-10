
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15488 KB , 시간 : 156 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경로찾기 {
    static int N;
    static int[][] list;
    static int[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        list = new int[N][N];
        answer = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                list[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (list[i][j] == 1 && answer[i][j] != 1) // 지나갈 수 있는 통로이고 이미 지나간 곳이 아니라면 탐색
                    graph(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j])
                        .append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);

    }

    static void graph(int startNode, int endNode) {
        answer[startNode][endNode] = 1; // 여기에 진입을 했다는 것은 startNode가 endNode로 진입이 가능하다는 의미

        for (int node = 0; node < N; node++) { // endNode에서 갈 수 있는 영역 체크(startNode가 직접적으로는 못가더라도 간접적으로 갈 수 있으므로 확인)
            if (list[endNode][node] == 1 && answer[startNode][node] != 1) { // 지나갈 수 있는 영역이고 진입한적이 없다면 진입
                graph(startNode, node);
            }
        }
    }
}
