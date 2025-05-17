
//언어 : JAVA , (성공/실패) : 1/6 , 메모리 : 18020 KB , 시간 : 188 ms
import java.io.*;
import java.util.*;

public class 평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] weight = new int[N];
        int[] value = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            int wi = weight[i];
            int vi = value[i];

            for (int w = K; w >= wi; w--) {
                dp[w] = Math.max(dp[w], dp[w - wi] + vi);
            }
        }

        System.out.println(dp[K]);
    }
}