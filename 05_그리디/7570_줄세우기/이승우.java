import java.io.*;
import java.util.*;

public class 줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] pos = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            pos[Integer.parseInt(st.nextToken())] = i;
        }

        int maxLen = 1, curr = 1;
        for (int v = 2; v <= N; v++) {
            if (pos[v] > pos[v - 1]) {
                curr++;
            } else {
                curr = 1;
            }
            if (curr > maxLen) {
                maxLen = curr;
            }
        }

        System.out.println(N - maxLen);
    }
}