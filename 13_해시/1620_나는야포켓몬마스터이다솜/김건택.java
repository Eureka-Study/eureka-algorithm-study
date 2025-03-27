// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 57636 KB , 시간 : 528 ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> nameNumMap = new HashMap<>(); // 이름 번호 맵
        Map<Integer, String> numNameMap = new HashMap<>(); // 번호 이름 맵

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();

            nameNumMap.put(name, i);
            numNameMap.put(i, name);
        }

        for (int i = 1; i <= M; i++) {
            String question = br.readLine();
            // 문제를 char 배열로 바꾸었을 때 0번째가 isDigit 이라면 이름을 답해야 하는 문제
            if (Character.isDigit(question.toCharArray()[0])) {
                int n = Integer.parseInt(question);
                bw.write(numNameMap.get(n) + "\n");
            } else {
                bw.write(nameNumMap.get(question) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}