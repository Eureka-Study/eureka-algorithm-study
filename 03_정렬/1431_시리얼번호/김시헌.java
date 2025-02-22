// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11708 KB , 시간 : 68 ms

import java.io.*;
import java.util.*;

public class Main {  // 시리얼 번호
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] serial = new String[N];

        for (int i = 0; i < N; i++) {
            serial[i] = br.readLine();
        }

        Arrays.sort(serial, new Comparator<String>() {  // Comparator 사용해서 정렬
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) return -1;   // 1. 길이가 짧은게 먼저
                else if (o1.length() == o2.length()) {      // 2. 길이 같다면
                    int sum1 = sum(o1);
                    int sum2 = sum(o2);

                    if (sum1 == sum2) return o1.compareTo(o2);  // 2-2. 숫자들 합 같으면 사전순으로
                    else {
                        if (sum1 < sum2) return -1;         // 2-1. 숫자들 합 작은게 먼저
                        else return 1;
                    }
                } else return 1;
            }
        });

        for (int i = 0; i < N; i++) {
            bw.write(serial[i]);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int sum(String o) {  // 숫자들 합 구하는 메소드
        int sum = 0;

        for (int i = 0; i < o.length(); i++) {  // 시리얼넘버 길이동안 한문자씩
            if (o.charAt(i) >= '0' && o.charAt(i) <= '9')   // 숫자인지 체크
                sum += o.charAt(i) - '0';   // 숫자면 해당 숫자 sum에 더하기
        }
        return sum;
    }
}
