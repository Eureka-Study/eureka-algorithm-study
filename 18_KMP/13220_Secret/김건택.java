package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Gold5_13220_KMP {

    static int[] getPi(int[] p) {
        int m = p.length;
        int[] pi = new int[m];
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j - 1];
            }
            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    public static List<Integer> kmp(int[] s, int[] p) {
        List<Integer> result = new ArrayList<>();
        int[] pi = getPi(p);
        int n = s.length, m = p.length, j = 0;

        for (int i = 0; i < n; i++) {
            while (j > 0 && s[i] != p[j]) {
                j = pi[j - 1];
            }
            if (s[i] == p[j]) {
                if (j == m - 1) {
                    result.add(i - m + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] pw1 = new int[N * 2];
        int[] pw2 = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            pw1[i] = n;
            pw1[i + N] = n;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pw2[i] = Integer.parseInt(st.nextToken());
        }


    }
}
