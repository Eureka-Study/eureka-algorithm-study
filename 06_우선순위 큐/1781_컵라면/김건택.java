// 언어 : JAVA , (성공/실패) : 0/0 , 메모리 :  KB , 시간 :  ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] problems = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            problems[i][0] = Integer.parseInt(st.nextToken());
            problems[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(problems, (o1, o2) -> o1[0] - o2[0]); // 데드라인 기준으로 오름차순 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] problem : problems) {
            pq.add(problem[1]);
            // 현재 문제의 마감일 보다 크면 컵라면 수가 적은 것 부터 삭제
            if (pq.size() > problem[0]) {
                pq.poll();
            }
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        System.out.println(sum);
    }
}