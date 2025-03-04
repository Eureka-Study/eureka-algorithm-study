// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 17976 KB , 시간 : 188ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt(); // 컴퓨터 수
        int N = sc.nextInt(); // 네트워크 수

        List<Integer>[] network = new ArrayList[C + 1];

        for (int i = 1; i <= C; i++) {
            network[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            network[a].add(b);
            network[b].add(a);
        }

        boolean[] visited = new boolean[C + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(1);
        visited[1] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int n : network[cur]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.offer(n);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}