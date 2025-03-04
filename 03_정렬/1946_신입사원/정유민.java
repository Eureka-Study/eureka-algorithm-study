// 언어 : JAVA , (성공/실패) : 0/3 , 메모리 : 299504 KB , 시간 : 776ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정유민 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int docs = Integer.parseInt(st.nextToken()); // 서류 등수
                int itv = Integer.parseInt(st.nextToken());  // 면접 등수

                arr[docs - 1] = itv; // 서류 등수를 인덱스로 하여 면접 등수를 저장
                //0 based 니까 1 빼준다.
            }

            // 서류 등수 1등 지원자는 무조건 합격이므로 count 1
            int count = 1;
            int rating = arr[0]; // 첫 번째 지원자의 면접 등수를 최소값으로 만든다

            // 두 번째 지원자부터 면접 등수를 비교하여 합격자 수 세기
            for (int i = 1; i < n; i++) {
                if (rating > arr[i]) { // 서류 1등의 면접 등수보다 더 높으면 합격 !
                    count++;
                    rating = arr[i]; //최소값을 다시 셋팅
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
