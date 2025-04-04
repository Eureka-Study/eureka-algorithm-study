//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14212 KB , 시간 : 100 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] list = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int n = 0; n < N; n++){
                list[n] = Integer.parseInt(st.nextToken());
            }

            int money = Integer.parseInt(br.readLine());
            int[] dp = new int[money + 1];
            dp[0] = 1; // 0을 만드는 가지수 1가지(아무것도 안넣기)

            for(int coin : list){
                for(int i = coin; i <= money; i++){
                    dp[i] += dp[i - coin]; // 현 금액에서 coin을 뺀 금액의 가지수를 추가(이전 금액에서 coin을 추가하면 현 금액이 되기에 모든 가지수가 전부 올라갈 수 있기 때문)
                }
            }

            sb.append(dp[money])
              .append("\n");
        }

        System.out.println(sb);
    }
}