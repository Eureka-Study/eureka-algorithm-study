
//언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 19196 KB , 시간 : 128 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기 {
    static char[] list;
    static StringBuilder sb = new StringBuilder();
    static char[] gathers = new char[] { 'a', 'e', 'i', 'o', 'u' };
    static int L, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        list = new char[C];
        st = new StringTokenizer(br.readLine(), " ");

        for (int c = 0; c < C; c++) {
            list[c] = st.nextToken().toCharArray()[0];
        }

        Arrays.sort(list);// 사전 순으로 암호가 만들어져야한다.

        password("", 0, 0);

        System.out.println(sb.toString().trim());
    }

    static void password(String word, int start, int vowelCount) { // 암호, 현재 보고있는 글자, 모음 개수
        if (word.length() >= L) { // 암호가 만들어졌으면 확인
            int consonantCount = word.length() - vowelCount; // 현 글자 길이 중 모음 개수를 빼면 자음 개수가 나온다.
            if (vowelCount >= 1 && consonantCount >= 2) {// 최소 한개의 모음과 2개의 자음으로 구성되야한다.
                sb.append(word).append("\n");
            }
            return;
        }
        // 아직 암호가 미완성일 경우
        for (int i = start; i < C; i++) {
            StringBuilder sb = new StringBuilder(word); // 매번 초기화를 시켜 전 글자가 들어간 버전(이전에 재귀한 것으로 파악 완료)과 안들어간 버전(이제 시작)으로
                                                        // 분리하여 계산
            sb.append(list[i]);// 현 글자를 넣어본다.
            int added = isGather(list[i]) ? 1 : 0; // 모음인지 파악
            password(sb.toString(), i + 1, vowelCount + added); // 현글자가 들어간 버전으로 재귀한다
        }
    }

    static boolean isGather(char c) { // 이 글자가 모음인지 파악하는 매서드
        for (char g : gathers) {
            if (c == g) {
                return true;
            }
        }
        return false;
    }
}