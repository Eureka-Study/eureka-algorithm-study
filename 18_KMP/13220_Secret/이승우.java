
//언어 : JAVA , (성공/실패) : 1/3, 메모리 : 35872 KB , 시간 : 388 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Secret {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] ai = new int[N], bi = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int n = 0; n < N; n++) {
            ai[n] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int n = 0; n < N; n++) {
            bi[n] = Integer.parseInt(st.nextToken());
        }

        int[] pi = new int[N];
        for (int i = 1, j = 0; i < N; i++) {
            while (j > 0 && ai[i] != ai[j]) {
                j = pi[j - 1];
            }
            if (ai[i] == ai[j]) {
                j++;
            }
            pi[i] = j;
        }

        for (int i = 0, j = 0; i < 2 * N; i++) {
            int b = bi[i % N];
            while (j > 0 && b != ai[j]) {
                j = pi[j - 1];
            }
            if (b == ai[j]) {
                j++;
            }
            if (j == N) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");

    }
}
