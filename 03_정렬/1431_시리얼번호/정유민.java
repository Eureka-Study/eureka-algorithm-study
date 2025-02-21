// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 14272 KB , 시간 : 108ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            list.add(br.readLine());
        }

        list.sort((x, y) -> {
            if (x.length() != y.length()) {
                return x.length() - y.length();
            }else{
                for (int i = 0; i < x.length(); i++) {
                    int xTotal = totalMachine(x);
                    int yTotal = totalMachine(y);

                    if (xTotal != yTotal) {
                        return Integer.compare(xTotal, yTotal);
                    }
                }
            }
            return x.compareTo(y);
        });

        for (String s : list) {
            System.out.println(s);
        }
    }

    static int totalMachine(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            if (0 <= num && num < 10) {
                total += num;
            }
        }
        return total;
    }
}