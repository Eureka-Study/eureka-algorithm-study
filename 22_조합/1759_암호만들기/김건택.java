// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14212 KB , 시간 : 108 ms
package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Gold5_1759_comb {
    static char[] src;
    static char[] tgt;
    static int L, C;
    static Set<Character> vowels = new HashSet<Character>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        src = new char[C];
        tgt = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            src[i] = st.nextToken().charAt(0);
        }

        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        Arrays.sort(src);

        comb(0, 0);

        System.out.println(sb.toString());
    }
    static void comb(int srcIdx, int tgtIdx) {

        if (tgtIdx == tgt.length) {
            int vCount = 0;
            int cCount = 0;

            for (char c : tgt) {
                if (vowels.contains(c)) vCount++;
                else cCount++;
            }

            if (vCount >=1 && cCount>=2) {
                for (char c : tgt) sb.append(c);
                sb.append("\n");
            }

            return;
        }

        if (srcIdx == src.length) return;

        tgt[tgtIdx] = src[srcIdx];
        comb(srcIdx + 1, tgtIdx + 1);
        comb(srcIdx + 1, tgtIdx);
    }
}
