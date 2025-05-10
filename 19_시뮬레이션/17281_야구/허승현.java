import java.io.*;
import java.util.*;


//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15528KB , 시간 : 480ms

public class Main {
    static int N;
    static int[][] innings; // 결과:[이닝][타자]
    static boolean[] used = new boolean[9]; // 타생성 시 몇 번 선수가 이미 타순에 배치되었는지 표시
    static int[] order = new int[9]; // 실제 타순
    static int maxScore = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        innings = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                innings[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 1번 선수(인덱스0)를 4번 타순(인덱스3)에 고정
        used[0] = true;
        order[3] = 0;
        dfs(0);
        System.out.println(maxScore);
    }

    // idx: 타순 인덱스(0~8)
    static void dfs(int idx) {
        if (idx == 9) {
            simulate();
            return;
        }
        if (idx == 3) {  // 4번 타자 자리 건너뛰기
            dfs(idx + 1);
            return;
        }
        for (int i = 1; i < 9; i++) {
            if (!used[i]) {
                used[i] = true;
                order[idx] = i;
                dfs(idx + 1);
                used[i] = false;
            }
        }
    }
    
    // 비트 연산을 이용한 주자 이동 & 득점 처리
    // bases는 3비트만 사용
    // 비트 0번째에 1: 1루에 주자가 있음 0: 주자 없음
    // 비트 1: 2루
    // 비트 2: 3루
    static void simulate() {
        int score = 0, batter = 0; // batter: 다음 타자의 order 인덱스
        for (int inning = 0; inning < N; inning++) {
            int outs = 0, bases = 0;
            while (outs < 3) {
                int hit = innings[inning][order[batter]];
                switch (hit) {
                    case 0: // 아웃
                        outs++;
                        break;
                    case 1: // 1루타
                        score += (bases >> 2) & 1; // 3루주자(비트2) 득점 처리
                        bases = ((bases << 1) | 1) & 0b111; // 주자 전부 1루씩 전진(bases << 1) + 타자 1루 진입( | 1) 3비트 마스크( & ob111)
                        break;
                    case 2: // 2루타
                        score += (bases >> 1) & 1; // 2루 비트 추출
                        score += (bases >> 2) & 1; // 3루 비트 추출
                        bases = ((bases << 2) | 2) & 0b111; // 주자 전부 2칸 전진하고 타자 2루(비트 1) 진입
                        break;
                    case 3: // 3루타
                    	score += Integer.bitCount(bases); // 비트마스크에 설정된 1비트(주자 수)만큼 점수
                        bases = 0b100; // 타자만 3루
                        break;
                    case 4: // 홈런
                    	score += Integer.bitCount(bases) + 1; // 기존 주자 수 + 타자 득점
                        bases = 0; // 주자 아무도 없음
                        break;
                }
                batter = (batter + 1) % 9;
            }
        }
        if (score > maxScore) maxScore = score;
    }
}

