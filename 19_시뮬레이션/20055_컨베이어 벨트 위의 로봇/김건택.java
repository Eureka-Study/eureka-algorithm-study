// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 23172 KB , 시간 : 284 ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gold5_20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] belt = new int[2 * N];
        boolean[] robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) {
            belt[i]= Integer.parseInt(st.nextToken());
        }

        int step = 0;

        while (true) {
            step++;

            // 1 단계
            int last = belt[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; i--) {
                belt[i] = belt[i - 1];
            }
            belt[0] = last;

            for (int i = N - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;

            robot[N - 1] = false;

            // 2 단계
            for (int i = N - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && belt[i] > 0) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    belt[i]--;
                }
            }
            robot[N - 1] = false;

            // 3 단계
            if (belt[0] > 0 && !robot[0]) {
                robot[0] = true;
                belt[0]--;
            }

            // 4 단계
            // count 가 K 이상이 되면 종료
            int count = 0;
            for (int b : belt) {
                if (b == 0) count++;
            }

            if (count >= K) break;

        }

        System.out.println(step);
    }
}
