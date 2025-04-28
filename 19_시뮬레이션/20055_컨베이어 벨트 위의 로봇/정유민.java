//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 13564 KB , 시간 : 216 ms

// 1번이 올리는 위치 , N번이 내리는 위치
// 로봇은 스스로 이동가능
// 올리는 위치에 올라가면 그 칸의 내구도 즉시 1만큼 감소

// 로봇이 회전하는 방향으로 한 칸 이동할 수 있다면 이동, 만약 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아있으면 이동가능
// 올리는 위치의 칸 내구도 0이 아니어야 올리는 위치에 로봇을 올린다.
// 내구도가 0인 칸의 개수가 K개 이상이면 과정 종료

// 위 세줄 한 번 하는게 한칸.


// 로봇이 한칸 이동하는 robotMove()
// 컨베이어 벨트가 이동하는 move()
// 로봇이 올라가는 onBelt()

// 컨베이어 벨트 한칸 씩 옮긴다.move() (내구도가 0인 칸의 개수가 k개면 종료)
// 로봇 이동할 수 있으면 이동한다.(이동하려는 칸에 로봇 없고, 그 칸의 내구도가 1이상남아있다면) robotMove()
// 로봇 올릴수 있으면(올리는 칸이 내구도가 0이 아니면) 올린다 onBelt()

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정유민 {
    static int[] belt;
    static boolean[] robot;
    static int n,k;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        belt = new int[2 * n];
        robot = new boolean[2 * n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < belt.length; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        while (true) {
            cnt++;

            move();
            robotMove();
            onBelt();


            if (check() >= k) {
                System.out.println(cnt);
                break;
            }
        }
    }

    private static int check() {
        int zeroCnt = 0;
        for (int i : belt) {
            if (i == 0) {
                zeroCnt++;
            }
        }
        return zeroCnt;
    }

    static void move() {
        // 벨트 회전
        int temp = belt[belt.length - 1];
        for (int i = belt.length - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = temp;

        // 로봇도 같이 회전
        for (int i = robot.length - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false; // 로봇은 올리는 위치에 자동으로 생성되지 않음

        robot[n - 1] = false; // 내리는 위치에서 반드시 내려야 함
    }


    // 로봇 움직인다.(이동하려는 칸에 로봇 없고, 그 칸의 내구도가 1이상남아있다면) robotMove()
    static void robotMove() {
        for (int i = n - 1 - 1; i >= 0; i--) {
            if (robot[i] && !robot[i + 1] && belt[i + 1] > 0) {
                robot[i] = false;
                robot[i + 1] = true;
                belt[i + 1]--;
            }
        }

        robot[n - 1] = false; // 내리는 위치에서 내려야 함
    }

    static void onBelt() {
        if (!robot[0] && belt[0] > 0) {
            robot[0] = true;
            belt[0]--;
        }
    }
}
