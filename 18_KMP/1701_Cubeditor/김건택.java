package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gold2_1701_retry {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int answer = 0;
        for (int i = 0; i < input.length(); i++) {
            String sub = input.substring(i); // 접미사 추출
            answer = Math.max(answer, getMaxLPS(sub)); // 각 접미사에 대해 LPS 계산
        }

        System.out.println(answer);
    }

    static int getMaxLPS(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        int maxLen = 0;

        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                maxLen = Math.max(maxLen, len);
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return maxLen;
    }
}
