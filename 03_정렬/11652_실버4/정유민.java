// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 32408 KB , 시간 : 328ms

import java.io.*;
import java.util.*;

public class 정유민 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeMap<Long, Integer> map = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            long card = Long.parseLong(br.readLine());
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        long answer = 0;
        int max = 0;

        for (var entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                answer = entry.getKey();
            }
        }

        System.out.println(answer);
    }
}
