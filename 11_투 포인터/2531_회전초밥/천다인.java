//언어 : JAVA , (성공/실패) : 0/1 , 메모리 : 22720 KB , 시간 : 240 ms
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 벨트 위 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 초밥 번호

        int[] belt = new int[N]; // 벨트 위 초밥
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        Map<Integer, Integer> sushiCount = new HashMap<>(); // 초밥 개수 카운트
        int uniqueSushi = 0;

        // 초기 윈도우 설정
        for (int i = 0; i < k; i++) {
            sushiCount.put(belt[i], sushiCount.getOrDefault(belt[i], 0) + 1);
            if (sushiCount.get(belt[i]) == 1) {
                uniqueSushi++;
            }
        }

        // 쿠폰 초밥 고려
        int maxSushi = sushiCount.containsKey(c) ? uniqueSushi : uniqueSushi + 1;

        // 슬라이딩 윈도우를 이용한 최대 초밥 가짓수 탐색
        for (int i = 0; i < N; i++) {
            // 왼쪽 초밥 제거
            int left = belt[i];
            sushiCount.put(left, sushiCount.get(left) - 1);
            if (sushiCount.get(left) == 0) {
                sushiCount.remove(left);
                uniqueSushi--;
            }

            // 오른쪽 초밥 추가
            int right = belt[(i + k) % N];
            sushiCount.put(right, sushiCount.getOrDefault(right, 0) + 1);
            if (sushiCount.get(right) == 1) {
                uniqueSushi++;
            }

            // 쿠폰 초밥 포함 여부 확인 후 최댓값 갱신
            maxSushi = Math.max(maxSushi, sushiCount.containsKey(c) ? uniqueSushi : uniqueSushi + 1);
        }

        System.out.println(maxSushi);
    }
}