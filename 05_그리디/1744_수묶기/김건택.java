// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14232 KB , 시간 : 104 ms
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Gold4_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(br.readLine());
            return;
        }

        PriorityQueue<Integer> plus = new PriorityQueue<>(((o1, o2) -> o2 - o1)); // 양수 내림차순 정렬
        PriorityQueue<Integer> minus = new PriorityQueue<>(); // 음수 내림차순 정렬

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) plus.add(num);
            else minus.add(num);
        }

        int sum = 0;

        while (!plus.isEmpty()) {

            if (plus.peek() == 1) { // 수가 1이라면 바로 더하기
                plus.poll();
                sum++;
                continue;
            }
            int a = plus.poll(); // 1이 아닌 첫번쨰 수
            if (plus.isEmpty()) { // 1이 아닌 수를 뽑았는데 마지막이었다면 더하기
                sum += a;
                break;
            }

            if (plus.peek() == 1) { // 두번째 뽑은 수가 1이라면 첫번째 뽑은 수와 1 더하기
                plus.poll();
                sum += a+1;
                continue;
            }
            int b = plus.poll(); // 1이 아닌 두번째 수

            sum += a*b; // 두 수를 곱해서 더하기
        }

        while (!minus.isEmpty()) {

            int a = minus.poll(); // 첫번째 수 뽑기
            if (minus.isEmpty()) { // 뽑은 후 마지막이었다면 바로 더하기
                sum += a;
                break;
            }
            int b = minus.poll(); // 두번째 수 뽑기
            sum += a*b;
        }

        System.out.println(sum);
    }
}
