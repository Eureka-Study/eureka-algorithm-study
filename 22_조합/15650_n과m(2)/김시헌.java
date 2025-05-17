// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11568 KB , 시간 : 64 ms

import java.io.*;
import java.util.*;

public class Main {     // N과 M (2)
    static int N, M;
    static int[] src;
    static int[] tgt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        tgt = new int[M];

        for (int i = 0; i < N; i++) {
            src[i] = i+1;   // src 배열 채우기 (오름차순)
        }

        comb(0,0);  // 인덱스 0부터 시작
    }

    static void comb(int srcIdx, int tgtIdx) {

        if (tgtIdx == M) {  // tgt 배열 하나 채워지면 출력
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                sb.append(tgt[i] + " ");
            }
            System.out.println(sb);
            return;
        }

        if (srcIdx == N) return;    // src 배열도 N까지 했다면 끝내기

        tgt[tgtIdx] = src[srcIdx];  // tgt 배열을 src의 idx 값으로 채우기
        comb(srcIdx + 1, tgtIdx + 1);   // 선택 후 다음 인덱스로 넘어감
        comb(srcIdx + 1, tgtIdx);             // 선택하지 않는 꼴 (tgtIdx는 그대로니까)
    }
}
