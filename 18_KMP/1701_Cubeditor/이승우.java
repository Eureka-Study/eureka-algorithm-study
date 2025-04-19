
// 언어 : JAVA , (성공/실패) : 1/5 , 메모리 : 26976 KB , 시간 : 168 ms
import java.io.*;
import java.util.*;

public class cubeditor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int len = str.length();
        String[] suffixes = new String[len];

        for (int i = 0; i < len; i++) {
            suffixes[i] = str.substring(i); // 원래 문자에서 한자씩 지워서 배열에 담기
        }

        Arrays.sort(suffixes);

        int maxLen = 0;
        for (int i = 0; i < len - 1; i++) {
            int lcp = commonPrefix(suffixes[i], suffixes[i + 1]); // 같은 부분 글자 수 구하기
            maxLen = Math.max(maxLen, lcp);
        }

        System.out.println(maxLen);
    }

    private static int commonPrefix(String a, String b) {
        int minLength = Math.min(a.length(), b.length()); // a, b 중 작은 크기
        int i = 0;
        while (i < minLength && a.charAt(i) == b.charAt(i)) { // 작은 크기를 벗어나지않고 a와 b가 동일한 글자인지 확인
            i++;
        }
        return i;
    }
}