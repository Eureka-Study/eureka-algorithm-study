// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 24504 KB , 시간 : 244 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Boj6198_옥상정원꾸미기 {

    static int[] building;
    static long cnt; // int 의 범위를 넘어서는, 32억이 최대이다..

    public static void main(String[] args) throws IOException {

        //옥상에서 오른쪽 건물을 봤을때, 옥상정원을 확인할 수 있는 총 수.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        building = new int[N];

        for (int i = 0; i < N; i++) {
            int build = Integer.parseInt(br.readLine());
            building[i] = build;
        }
        Deque<Integer> stack = new ArrayDeque<>();

        for (int h : building) {
            while (!stack.isEmpty() && stack.peek() <= h) {
                stack.pop();
            }
            cnt += stack.size();
            stack.push(h);
        }
        System.out.println(cnt);
    }
}
