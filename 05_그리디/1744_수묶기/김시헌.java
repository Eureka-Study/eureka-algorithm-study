// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11580 KB , 시간 : 68 ms

import java.io.*;
import java.util.*;

public class Main {    // 수 묶기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> positive = new ArrayList<>();    // 양수 담는 배열
        ArrayList<Integer> zero = new ArrayList<>();        // 0 담는 배열
        ArrayList<Integer> negative = new ArrayList<>();    // 음수 담는 배열

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) positive.add(num);
            else if (num == 0) zero.add(num);
            else negative.add(num);
        }
        Collections.sort(positive, Collections.reverseOrder());     // 양수는 내림차순 정렬 ( 5, 4, 3, ... )
        Collections.sort(negative);                                 // 음수는 오름차순 정렬 ( -5, -4, -3, ... )

        int posLen = positive.size();
        int zeroLen = zero.size();
        int negLen = negative.size();

        int ans = 0;

        if (negLen % 2 == 0) {  // 음수 짝수개
            for (int i = 0; i < negLen; i = i + 2) {
                ans += negative.get(i) * negative.get(i+1); // 모든 음수들 순서대로 두개씩 곱하기
            }

        } else {    // 음수 홀수개
            if (zeroLen >= 1) {     // 0이 한개 이상이라면
                for (int i = 0; i < negLen; i = i + 2) {
                    if (i == negLen - 1) continue;          // 홀수개라서 남은 음수 한개는 0이랑 곱했다고 처리
                    ans += negative.get(i) * negative.get(i+1); // 나머지 모든 음수들 순서대로 두개씩 곱하기
                }

            } else {    // 0이 아예 없다면
                for (int i = 0; i < negLen; i = i + 2) {
                    if (i == negLen - 1) ans += negative.get(i);    // 마지막 남은 음수 한개는 그냥 빼야지 뭐
                    else ans += negative.get(i) * negative.get(i+1);    // 나머지 모든 음수들 순서대로 두개씩 곱하기
                }
            }
        }

        if (posLen % 2 == 0) {  // 양수 짝수개
            for (int i = 0; i < posLen; i = i + 2) {
                ans += Math.max(positive.get(i) * positive.get(i+1), positive.get(i) + positive.get(i+1));  // 1*1 방지용
            }

        } else {    // 양수 홀수개
            for (int i = 0; i < posLen; i = i + 2) {
                if (i == posLen - 1) {
                    ans += positive.get(i); // 남은 양수 한개는 그냥 더함
                } else {
                    ans += Math.max(positive.get(i) * positive.get(i+1), positive.get(i) + positive.get(i+1));  // 1*1 방지용
                }
            }
        }

        System.out.println(ans);
    }
}
