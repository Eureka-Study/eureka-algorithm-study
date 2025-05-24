import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class 최소편집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int N = A.length(), M = B.length();

        int[] prev = new int[M + 1];
        int[] cur = new int[M + 1];

        for (int m = 0; m <= M; m++) {
            prev[m] = m;
        }

        for (int n = 1; n <= N; n++) {
            cur[0] = n;
            for (int m = 1; m <= M; m++) {
                if (A.charAt(n - 1) == B.charAt(m - 1)) {
                    cur[m] = prev[m - 1];
                } else {
                    int deleteCost = prev[m] + 1;
                    int insertCost = cur[m - 1] + 1;
                    int replaceCost = prev[m - 1] + 1;
                    cur[m] = Math.min(deleteCost, Math.min(insertCost, replaceCost));
                }
            }
            prev = cur.clone();
        }

        System.out.println(prev[M]);
    }
}