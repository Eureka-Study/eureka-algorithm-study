
// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 23156KB , 시간 : 324ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String boom = br.readLine();
        int N = s.length(), L = boom.length();

        char[] answer = new char[N];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            answer[idx++] = s.charAt(i); // 테이블에 차곡차곡 쌓기

            if (idx >= L && s.charAt(i) == boom.charAt(L - 1)) { // 현 문자가 폭발 문자열의 마지막과 같은지 확인
                boolean match = true;

                for (int j = 0; j < L; j++) { // 폭발이 가능한지 확인하기위해 문자가 일치한지 확인
                    if (answer[idx - L + j] != boom.charAt(j)) { // 하나라도 일치하지않으면 다른 것이기 때문에 빠져나가기
                        match = false;
                        break;
                    }
                }

                if (match) { // 일치할 경우 제거가 된것이므로 제거된 길이만큼 뺀다.
                    idx -= L;
                }
            }
        }

        if (idx == 0) {
            System.out.println("FRULA"); // 전부 제거가 될 경우
        } else {
            System.out.println(new String(answer, 0, idx)); // 남아있을 경우
        }
    }
}