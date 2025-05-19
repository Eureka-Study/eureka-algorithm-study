// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14208 KB , 시간 : 100 ms
package boj.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver3_15650_comb_retry {
    static int[] src;
    static int[] tgt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        src = new int[N];
        tgt = new int[M];

        for (int i = 1; i <= N; i++) {
            src[i-1] = i;
        }

        comb(0, 0);

        System.out.println(sb.toString());

    }

    static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            for (int i : tgt) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        if (srcIdx == src.length) return;

        tgt[tgtIdx] = src[srcIdx];
        comb(srcIdx + 1, tgtIdx + 1);
        comb(srcIdx + 1, tgtIdx);

    }
}
