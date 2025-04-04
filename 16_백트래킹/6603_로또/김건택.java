package boj;

import java.io.*;
import java.util.StringTokenizer;

public class Silver2_6603 {
    static int[] nums;
    static int[] lotto = new int[6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if (k == 0) break;

            nums = new int[k];
            for (int i = 0; i < k; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            comb(0, 0);

            System.out.println();
        }

    }

    public static void comb(int srcIdx, int tgtIdx) throws IOException {

        if( tgtIdx == lotto.length ) {
            StringBuilder sb = new StringBuilder();
            for (int n : lotto) {
                sb.append(n).append(" ");
            }
            System.out.println(sb);
            return;
        }

        if( srcIdx == nums.length ) return;

        lotto[tgtIdx] = nums[srcIdx];
        comb(srcIdx + 1, tgtIdx + 1);
        comb(srcIdx + 1, tgtIdx);
    }
}
