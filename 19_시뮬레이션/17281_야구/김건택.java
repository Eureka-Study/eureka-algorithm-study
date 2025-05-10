// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 63952 KB , 시간 : 540 ms

package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Gold4_17281 {
    static int inning;
    static int[] hitters = {1,2,3,4,5,6,7,8,9};
    static boolean[] select = new boolean[9];
    static int[] order = new int[9];

    static int maxScore = 0;

    static int[][] hit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inning = Integer.parseInt(br.readLine());

        hit = new int[inning][10];

        StringTokenizer st;
        for (int i = 0; i < inning; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                hit[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[3] = hitters[0];   // hitters 배열이 {1,2,…,9} 이므로 hitters[0] == 1
        select[0] = true;        // hitters[0] 사용 처리

        ordering(0);


        System.out.println(maxScore);

    }

    static void ordering(int oIdx) {

        if (oIdx == order.length) {
            if (order[3] == 1) {
//                System.out.println(Arrays.toString(order));
                startGame();
            }
            return;
        }

        if (oIdx == 3) {
            ordering(oIdx + 1);
            return;
        }

        for (int i = 0; i < hitters.length; i++) {
            if (select[i]) continue;

            order[oIdx] = hitters[i];

            select[i] = true;
            ordering(oIdx + 1);
            select[i] = false;
        }
    }

//    static void startGame() {
//        int score = 0;
//        int turn = 0;
//
//
//        for (int j = 0; j < inning; j++) {
//            ArrayDeque<Integer> base = new ArrayDeque<>();
//
//            for (int i = 0; i < 3; i++) {
//                base.offer(0);
//            }
//
//            int outCount = 0;
//
//            while (outCount < 3) {
//                int hits = hit[j][order[turn]];
//
//                switch (hits) {
//                    case 1:
//                        base.offer(1);
//                        if (base.peek() == 1) score++;
//                        base.poll();
//                        break;
//                    case 2:
//                        base.offer(1);
//                        base.offer(0);
//                        for (int i = 0; i < 2; i++) {
//                            if (base.peek() == 1) score++;
//                            base.poll();
//                        }
//                        break;
//                    case 3:
//                        base.offer(1);
//                        base.offer(0);
//                        base.offer(0);
//                        for (int i = 0; i < 3; i++) {
//                            if (base.peek() == 1) score++;
//                            base.poll();
//                        }
//                        break;
//                    case 4:
//                        base.offer(1);
//                        base.offer(0);
//                        base.offer(0);
//                        base.offer(0);
//                        for (int i = 0; i < 4; i++) {
//                            if (base.peek() == 1) score++;
//                            base.poll();
//                        }
//                        break;
//                    case 0:
//                        outCount++;
//                        break;
//                }
//                turn = (turn + 1) % 9;
//            }
//        }
//
//        maxScore = Math.max(maxScore, score);
//    }

    static void startGame() {
        int score = 0;
        int turn = 0;

        for (int j = 0; j < inning; j++) {
            // 이닝마다 베이스 초기화: bases[1]=1루, bases[2]=2루, bases[3]=3루
            boolean[] bases = new boolean[4];
            int outCount = 0;

            while (outCount < 3) {
                int hits = hit[j][ order[turn] ];

                if (hits == 0) {
                    // 아웃 처리
                    outCount++;
                } else {
                    // 기존 주자들(3루→1루 순서) 이동 및 득점 처리
                    for (int b = 3; b >= 1; b--) {
                        if (bases[b]) {
                            int nb = b + hits;
                            if (nb > 3) {
                                score++;
                            } else {
                                bases[nb] = true;
                            }
                            bases[b] = false;
                        }
                    }
                    // 타자 올리기 (홈런일 땐 nb>3 으로 득점)
                    if (hits > 3) {
                        score++;
                    } else {
                        bases[hits] = true;
                    }
                }

                // 다음 타자
                turn = (turn + 1) % 9;
            }
        }

        maxScore = Math.max(maxScore, score);
    }

}
