
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14480 KB , 시간 : 112 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 뱀 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()), K = Integer.parseInt(br.readLine());

        int[][] field = new int[N + 1][N + 1];
        Map<Integer, Character> rotation = new HashMap<>();

        field[1][1] = 2; // 뱀은 2로 표시한다.

        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            field[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1; // 사과 위치 표시는 1
        }

        int L = Integer.parseInt(br.readLine());

        for (int l = 0; l < L; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().toCharArray()[0];

            rotation.put(X, C); // 언제 뱀이 방향을 바꾸는지 파악
        }

        int x = 1, y = 1, directionality = 1, time = 0;
        Deque<int[]> snake = new ArrayDeque<>(); // 뱀 위치 파악용

        snake.offerLast(new int[] { y, x }); // 뱀의 첫 위치는 1, 1

        while (true) { // 다른식으로 표현을 하려했으나 떠오르지않아서 true로 표시 (snake.isEmpty()도 생각했으나 true랑 다를게 없음)
            time++;

            // 이동 방향에 맞게 위치를 이동시킨다.
            if (directionality == 1) // 오른쪽
                x++;
            else if (directionality == 2) // 위
                y++;
            else if (directionality == 3) // 왼쪽
                x--;
            else if (directionality == 4) // 아래
                y--;

            char c = rotation.getOrDefault(time, '0'); // 해당 초가 끝난 뒤 방향을 바꾸므로 rotation에 해당 시간이 있는지 확인
            if (c == 'D') { // 오른쪽으로 회전
                if (directionality < 4) // 4일 경우는 다시 오른쪽으로 가야하므로 1으로 초기화
                    directionality++;
                else
                    directionality = 1;
            } else if (c == 'L') { // 왼쪽으로 회전
                if (directionality == 1) // 1일 경우 아래를 바라봐야하므로 4으로 초기화
                    directionality = 4;
                else
                    directionality--;
            }

            if (x <= 0 || x > N ||
                    y <= 0 || y > N ||
                    field[y][x] == 2) { // 좌표에 벗어났는지(벽에 부딪쳤을 경우), 자기자신의 몸과 부딪쳤을 경우 종료
                break;
            } else if (field[y][x] == 1) { // 사과가 있을 경우 뱀의 길이가 증가
                field[y][x] = 2;
            } else { // 사과가 없을 경우 꼬리부분을 poll하여 없앤다.
                int[] tail = snake.pollFirst();
                field[y][x] = 2;
                field[tail[0]][tail[1]] = 0;
            }

            snake.offerLast(new int[] { y, x }); // 이동한 곳이 최종 뱀 머리부분이므로 넣는다.
        }

        System.out.println(time); // 끝날 때까지의 게임 시간 출력

    }
}
