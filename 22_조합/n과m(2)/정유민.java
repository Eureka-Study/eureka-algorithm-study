//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11560 KB , 시간 : 68 ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n과m2 {

    static int[] src;
    static int[] tgt;
    static StringBuilder sb= new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        src = new int[n];
        for (int i = 0; i < src.length; i++) {
            src[i] = i+1;
        }
        tgt = new int[m];

        comb(0, 0);
        System.out.println(sb);
    }

    private static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            for (int i = 0; i < tgt.length; i++) {
                sb.append(tgt[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        if(srcIdx == src.length) {
            return;
        }
        tgt[tgtIdx] = src[srcIdx];
        comb(srcIdx+1, tgtIdx+1);
        comb(srcIdx+1, tgtIdx);

    }
}
