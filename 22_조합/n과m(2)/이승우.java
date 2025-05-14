
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16244 KB , 시간 : 132 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NM2 {
    static int N, M;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        num("", 1, 0); // n = 1

        System.out.println(sb);
    }

    static void num(String s, int n, int m) {
        if (m == M) {
            sb.append(s.trim())
                    .append("\n");
            return;
        }
        for (int i = n; i <= N; i++) {
            num(s + i + " ", i + 1, m + 1);
        }
    }
}
