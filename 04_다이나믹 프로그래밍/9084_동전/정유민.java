// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11732 KB , 시간 : 68 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//  m -> 금액에서  n ->  동전   m-n 를 만드는 방법의 수
//
public class 동전 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] coin = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int targetMoney = Integer.parseInt(br.readLine());
            int[] dp = new int[targetMoney + 1];
            dp[0]=1;

            for (int i : coin) {
                for (int j = i; j <= targetMoney; j++) {
                    dp[j] += dp[j - i];
                }
            }
            System.out.println(dp[targetMoney]);
        }
    }
}
