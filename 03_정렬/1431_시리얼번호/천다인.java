// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14292 KB , 시간 : 108 ms
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        String[] serials = new String[n];
        for (int i = 0; i < n; i++) {
            serials[i] = br.readLine();
        }

        Arrays.sort(serials, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length(); // 길이가 짧은 것이 먼저
            }

            int sumA = sum(a);
            int sumB = sum(b);
            if (sumA != sumB) {
                return sumA - sumB; // 숫자의 합이 작은 것이 먼저
            }

            return a.compareTo(b); // 사전순 정렬
        });

        for (String s : serials) {
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }

    // 숫자 합을 계산하는 메서드
    static int sum(String str) {
        int sum = 0;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += c - '0'; // 숫자로 변환하여 합산
            }
        }
        return sum;
    }
}