// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11540 KB , 시간 : 64 ms

import java.io.*;
import java.util.*;

public class Main {  // 로또
    static int[] src, tgt;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            if (K == 0) break;
            src = new int[K];
            for (int i = 0; i < K; i++) {
                src[i] = Integer.parseInt(st.nextToken());
            }

            tgt = new int[6];
            sb = new StringBuilder();

            comb(0, 0);

            System.out.println(sb);
        }
    }

    static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == tgt.length) {     // 6자리 다 채웠으면 출력
            for (int num : tgt) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        if (srcIdx == src.length) return;

        tgt[tgtIdx] = src[srcIdx];  // tgtIdx 번째 숫자로 src[srcIdx] 숫자를 선택
        comb(srcIdx + 1, tgtIdx + 1);   // src[srcIdx] 숫자를 선택 안하고, tgt 자릿수도 다음 자리 과정으로
        comb(srcIdx + 1, tgtIdx);   // src[srcIdx] 숫자를 선택 안하고, src[srcIdx + 1] 선택으로
    }
}
