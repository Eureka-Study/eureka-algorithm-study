//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 36576KB , 시간 : 420ms

import java.io.*;
import java.util.*;

public class Main {
	// 수열을 비교할 때, 불일치가 발생했을 때 얼마나 점프할 수 있는지 알려줌
    static int[] getPi(int[] pattern) {
        int n = pattern.length;
        int[] pi = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1]; // 불필요한 비교를 건너뜀
            }
            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }
        return pi; 
    }
    
    // text안에 pattern이 한 번이라도 존재하면 true를 리턴
    static boolean kmp(int[] text, int[] pattern) {
        int[] pi = getPi(pattern);
        int j = 0;
        for (int i = 0; i < text.length; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j - 1]; // 실패했을 때 이전에 일치했던 접두사 길이로 점프
            }
            if (text[i] == pattern[j]) {
                j++;
                if (j == pattern.length) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[] a = new int[N];
        int[] b = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) a[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) b[i] = Integer.parseInt(st.nextToken());

        // a를 두 번 이어 붙인 배열 생성
        int[] aa = new int[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            aa[i] = a[i % N];
        }

        // KMP로 b가 aa의 부분 배열인지 확인
        if (kmp(aa, b)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}