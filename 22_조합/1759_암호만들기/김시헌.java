// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 12060 KB , 시간 : 80 ms

import java.io.*;
import java.util.*;

public class Main {     // 암호 만들기
    static int L, C;
    static char[] arr;
    static char[] result;
    static final Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u')); // 모음 set

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        result = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);  // 사전순 정렬
        dfs(0, 0);
    }

    static void dfs(int srcIdx, int tgtIdx) {
        if (tgtIdx == L) {
            if (isValid(result)) {
                System.out.println(new String(result));
            }
            return;
        }

        for (int i = srcIdx; i < C; i++) {
            result[tgtIdx] = arr[i];
            dfs(i + 1, tgtIdx + 1);
        }
    }

    static boolean isValid(char[] comb) {   // 최소 1개 vowel, 최소 2개 conso
        int vowelCount = 0;
        int consoCount = 0;

        for (char c : comb) {
            if (vowels.contains(c)) vowelCount++;
            else consoCount++;
        }

        return vowelCount >= 1 && consoCount >= 2;
    }
}
