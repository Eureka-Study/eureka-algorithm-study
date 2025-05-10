// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11680 KB , 시간 : 72 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// tgt 조건 => 최소 1개의 모음과 최소 2개의 자음이 있어야 한다.
public class 암호만들기 {

    static char[] src;
    static char[] tgt;
    static HashSet<Character> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        src = new char[c];
        tgt = new char[l];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < src.length; i++) {
            src[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(src);

        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        comb(0, 0);
        System.out.println(sb);
    }

    public static void comb(int srcIdx, int tgtIdx) {

        // 기저조건 -> 다 돌고 자음 모음 개수 확인해서 판단. 어짜피 정렬은 돼 있음
        if (tgtIdx == tgt.length) {
            int moum = 0;
            int zaum = 0;

            for (char c : tgt) {
                if(set.contains(c)) {
                    moum++;
                }else zaum++;
            }

            if (moum > 0 && zaum > 1) {
                for (char c : tgt) {
                    sb.append(c);
                }
                sb.append("\n");
            }

            return; // 조합 경우의 수 완성
        }


        if (srcIdx == src.length) {
            return; //기저조건 한개 더
        }
        tgt[tgtIdx] = src[srcIdx]; // 선택
        comb(srcIdx + 1, tgtIdx + 1); //위 선택을 받아들이고 다음 자리로 재귀호출
        comb(srcIdx + 1, tgtIdx); //위 선택을 받아들이지 않고 srcIdx 만 증가 효과(이전 선택을 srcIdx 다음 수로 덮어쓰는 경우)
    }
}
