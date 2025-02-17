// 언어 : JAVA , (성공/실패) : 1/5 , 메모리 : 14124 KB , 시간 : 100 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            int[] answer = fibonacci(n);

            sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int[] fibonacci(int n) {

        if (n == 0) return new int[]{1, 0};
        else if (n == 1) return new int[]{0, 1};

        int[][] result = new int[n+1][2];

        result[0][0] = 1;
        result[0][1] = 0;
        result[1][0] = 0;
        result[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            result[i][0] = result[i - 1][0] + result[i - 2][0];
            result[i][1] = result[i - 1][1] + result[i - 2][1];
        }

        return result[n];
    }
}
