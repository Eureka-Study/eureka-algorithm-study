// 언어 : JAVA , (성공/실패) : 0/2 , 메모리 :  KB , 시간 :  ms
import java.io.*;
import java.util.*;

public class Main {
    static int N, d, k, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

//        int[] dishes = new int[N];
//
//        for (int i = 0; i < N; i++) {
//            dishes[i] = Integer.parseInt(br.readLine());
//        }
//
//        int max = 0;
//
//        for (int i = 0; i < N; i++) {
//            Set<Integer> set = new HashSet<>();
//            for (int j = 0; j < k; j++) {
//                set.add(dishes[(i+j) % N]);
//                set.add(c);
//            }
//            if (set.size() > max) {
//                max = set.size();
//            }
//        }
//
//        System.out.println(max);

        int[] dishes = new int[N];
        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }

        // 초밥 종류 개수 저장
        int[] sushiCount = new int[d + 1];
        int kind = 0; // 현재 선택한 초밥 종류 수
        int maxKind = 0;

        // 초기 k개 초밥 추가
        for (int i = 0; i < k; i++) {
            if (sushiCount[dishes[i]] == 0) kind++;
            sushiCount[dishes[i]]++;
        }

        // 쿠폰 초밥 포함
        if (sushiCount[c] == 0) kind++;
        sushiCount[c]++;

        maxKind = kind;

        for (int i = 0; i < N; i++) {
            int removeIdx = i; // 제거할 초밥
            int addIdx = (i + k) % N; // 새로 추가할 초밥

            // 초밥 제거
            sushiCount[dishes[removeIdx]]--;
            if (sushiCount[dishes[removeIdx]] == 0) kind--;

            // 초밥 추가
            if (sushiCount[dishes[addIdx]] == 0) kind++;
            sushiCount[dishes[addIdx]]++;

            // 최대 초밥 종류 업데이트
            maxKind = Math.max(maxKind, kind);
        }

        System.out.println(maxKind);
    }
}
