//스스로 풀지 못하여 제출하지 않았습니다.

import java.util.Scanner;
//Bottom-up 방식
public class 정유민 {
    public static void main(String[] args) {
        //최소 연산 횟수를 얻어내는 고정된 규칙이 없기 때문에
        // X에서 가능한 모든 연산 과정을 수행하고, 이 중에서 최소 연산 횟수를 출력하는 것이 최선
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int[] dp = new int[x + 1];
        for (int i = 2; i <= x; i++) {

            // 1을 빼는 경우
            dp[i] = dp[i - 1] + 1;

            // 2로 나누어 떨어지는 경우
            if (i % 2 == 0)
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);

            // 3로 나누어 떨어지는 경우
            if (i % 3 == 0)
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }
        System.out.println(dp[x]);
    }
}

//https://bada744.tistory.com/61