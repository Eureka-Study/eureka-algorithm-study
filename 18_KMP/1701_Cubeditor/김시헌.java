// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 86904 KB , 시간 : 272 ms

import java.io.*;
import java.util.*;

public class Main {    // Cubeditor
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int len = str.length();
        int ans = 0;

        for (int i = 0; i < len; i++) {
            String subStr = str.substring(i, len);  // 큰거부터 작은거 0~len, 1~len, ... , len~len 모든 부분문자열 수행
            ans = Math.max(ans, KMP(subStr));
        }

        System.out.println(ans);
    }

    static int KMP(String subStr) {
        int len = subStr.length();
        int[] pi = new int[len];
        int maxPi = 0;
        int prefixIdx = 0;

        for (int i = 1; i < len; i++) {     // i = suffixIdx

            while (prefixIdx > 0 && subStr.charAt(i) != subStr.charAt(prefixIdx)) {
                prefixIdx = pi[prefixIdx - 1];
            }

            if (subStr.charAt(i) == subStr.charAt(prefixIdx)) {
                prefixIdx++;
                pi[i] = prefixIdx;
                maxPi = Math.max(maxPi, pi[i]);
            }
        }

        return maxPi;
    }
}
