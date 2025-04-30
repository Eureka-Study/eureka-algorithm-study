
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 91352 KB , 시간 : 428 ms
import java.io.*;
import java.util.*;

public class 줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] pos = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            pos[Integer.parseInt(st.nextToken())] = i; // 숫자가 몇번째에 위치하는지
        }

        int maxLen = 1, curr = 1;
        for (int v = 2; v <= N; v++) {
            if (pos[v] > pos[v - 1]) { // ex) 2가 1보다 뒤에있는지 확인
                curr++;
            } else {
                curr = 1;
            }
            if (curr > maxLen) { // 연속적인 오름차순이 몇개가 최대인지 확인
                maxLen = curr;
            }
        }

        System.out.println(N - maxLen); // 배열 최대깂 - 움직이지않아도 되는 배열 개수
    }
}