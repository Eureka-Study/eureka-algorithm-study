
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 63192 KB , 시간 : 504 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 야구 {
    static int N;
    static int[][] performance;
    static int[] order = new int[9];
    static boolean[] used = new boolean[9];
    static int maxScore = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        performance = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                performance[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[3] = 0;
        used[0] = true;
        permute(0);
        System.out.println(maxScore);
    }

    static void permute(int pos) {
        if (pos == 9) {// 모든 선수의 순서를 결정
            simulate(); // 현 순서대로 시뮬레이션 돌려서 점수 파악
            return;
        }
        if (pos == 3) { // 4번 타자는 1번 선수 고정
            permute(pos + 1);
            return;
        }
        for (int player = 1; player < 9; player++) {// 각 선수가 post 번째 타자로 결정하여
            if (!used[player]) {
                used[player] = true;
                order[pos] = player;
                permute(pos + 1);// 현 선수를 post 번째로 두고 다음 타자를 찾으러간다.
                used[player] = false; // 현 선수 대신 다른 선수를 새워본다.
            }
        }
    }

    static void simulate() {
        int score = 0;
        int idx = 0;
        for (int inning = 0; inning < N; inning++) {// 시뮬레이션 시작
            boolean[] base = new boolean[4];// 각 루에 사람이 있는지
            int outs = 0;
            while (outs < 3) { // 아웃이 3번이면 경기가 종료된다.
                int hitter = order[idx];
                int result = performance[inning][hitter];
                idx = (idx + 1) % 9;

                if (result == 0) {// 0은 아웃
                    outs++;
                } else { // 치긴 쳤다.
                    for (int b = 3; b >= 1; b--) { // 각 루에 사람을 친 것에 따라 이동 시켜야한다.
                        if (base[b]) {// 현 루에 사람이 있는지 파악
                            int nb = b + result; // 친만큼 이동이되므로 더한다
                            if (nb >= 4) {// 4 이상이라면 한바퀴를 돈 것
                                score++;
                            } else { // 그게 아니라면 아직 루에 있으므로 그 위치에 사람을 둔다.
                                base[nb] = true;
                            }
                            base[b] = false;// 이동을 했으므로 이전에 있었던 루를 사람 없는 것으로 만든다.
                        }
                    }
                    // 방금까지는 루에 있었던 사람들을 움직인 것
                    // 여기는 친 사람을 이동시킨다.
                    if (result >= 4) {// 홈런을 친 경우는 루에 둘 필요가 없으므로 점수만 추가
                        score++;
                    } else { // 홈런이 아니라면 무조건 루에 있어야한다.
                        base[result] = true;
                    }
                }
            }
        }
        maxScore = Math.max(maxScore, score); // 시뮬레이션 종료 이전에 실시한 결과 값이랑 비교하여 큰 값을 저장
    }
}
