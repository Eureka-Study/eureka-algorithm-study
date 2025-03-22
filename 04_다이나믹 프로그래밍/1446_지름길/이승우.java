//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14472 KB , 시간 : 108 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 지름길 {
    static class Shortcut {
        int start, end, cost;

        public Shortcut(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Shortcut> shortcuts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (end <= D && end > start){
                shortcuts.add(new Shortcut(start, end, cost));
            }
        }

        int[] dp = new int[D + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= D; i++) {
            if (i > 0) dp[i] = Math.min(dp[i], dp[i - 1] + 1);

            for (Shortcut s : shortcuts) {
                if (s.start == i && dp[s.end] > dp[i] + s.cost) {
                    dp[s.end] = dp[i] + s.cost;
                }
            }
        }

        System.out.println(dp[D]);
    }
}