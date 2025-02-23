// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14408 KB , 시간 : 108 ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        String[] guitars = new String[N];

        for (int i = 0; i < N; i++) {
            guitars[i] = br.readLine();
        }

        // 실패 코드
//        Arrays.sort(guitars, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                if (o1.length() < o2.length()) {
//                    return -1;
//                }
//                else if (o1.length() == o2.length()) {
//                    int sum1 = 0, sum2 = 0;
//
//                    for (char c : o1.toCharArray()) {
//                        if (Character.isDigit(c)) {
//                            sum1 += c - '0';
//                        }
//                    }
//
//                    for (char c : o2.toCharArray()) {
//                        if (Character.isDigit(c)) {
//                            sum2 += c - '0';
//                        }
//                    }
//
//                    if (sum1 < sum2) {
//                        return -1;
//                    }
//                    else if (sum1 > sum2) {
//                        return 1;
//                    }
//                }
//                return o1.compareTo(o2);
//            }
//        });

        Arrays.sort(guitars, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                }

                int sum1 = 0, sum2 = 0;
                for (char c : o1.toCharArray()) {
                    if (Character.isDigit(c)) {
                        sum1 += c - '0';
                    }
                }
                for (char c : o2.toCharArray()) {
                    if (Character.isDigit(c)) {
                        sum2 += c - '0';
                    }
                }

                if (sum1 != sum2) {
                    return sum1 - sum2;
                }

                return o1.compareTo(o2);
            }
        });

        for (String guitar : guitars) {
            bw.write(guitar + "\n");
        }

        bw.flush();
        bw.close();
    }
}
