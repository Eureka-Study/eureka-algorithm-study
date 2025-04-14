
// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 18368 KB , 시간 : 192 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class 음악프로그램 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        List<Integer>[] prerequisite = new ArrayList[N + 1];
        int[] prerequisiteCount = new int[N + 1];

        for (int n = 1; n <= N; n++) {
            prerequisite[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int count = Integer.parseInt(st.nextToken());

            int[] list = new int[count];

            for (int c = 0; c < count; c++) {
                list[c] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {

                    if (!prerequisite[list[i]].contains(list[j])) { // 먼저 하는 가수 뒤에 속해있지않으면 수행
                        prerequisite[list[i]].add(list[j]); // 먼저 하는 가수 안에 뒤에 해야하는 가수 넣기
                        prerequisiteCount[list[j]]++; // 순위 체크용
                    }
                }
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (prerequisiteCount[i] == 0) // 0이면 반드시 먼저 수행할 사람이 존재하지않기 때문에 우선적으로 담는다.
                deque.offer(i);
        }

        boolean[] visible = new boolean[N + 1]; // 이미 출연을 했는지 확인
        StringBuilder sb = new StringBuilder();

        while (!deque.isEmpty()) {
            int now = deque.poll();
            if (visible[now]) // 이미 불렀는데 또 나왔는지 체크
                continue;
            visible[now] = true; // 출연 성공

            sb.append(now)
                    .append("\n");

            for (int i : prerequisite[now]) { // 이 사람 뒤에 출연하기로 한 사람의 순위 줄이기
                prerequisiteCount[i]--;
            }

            for (int i = 1; i <= N; i++) {
                if (prerequisiteCount[i] == 0 && !visible[i]) { // 0순위 그리고 출연을 안하신 분 넣기
                    deque.offer(i);
                }
            }
        }

        System.out.print(sb.toString().split("\n").length < N ? 0 : sb);
    }
}
