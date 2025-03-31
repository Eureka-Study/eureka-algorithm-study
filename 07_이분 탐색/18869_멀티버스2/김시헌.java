// 언어 : JAVA , (성공/실패) : 0/1 , 메모리 :  KB , 시간 :  ms

import java.io.*;
import java.util.*;

public class Main {   // 멀티버스2
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        String[] spaces = new String[M]; // M개의 우주, 그리고 각 우주별 N개의 행성번호 배열 (행성크기별로 정렬)

        int ans = 0;

        for (int i = 0; i < M; i++) {
            int[][] planets = new int[N][2];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                planets[j][0] = Integer.parseInt(st.nextToken());   // 행성 크기
                planets[j][1] = j;  // 행성 번호
            }

            Arrays.sort(planets, (p1, p2) -> {
                return p1[0] - p2[0];
            });

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(planets[j][1]).append(" ");
            }
            String sortedPlanets = String.valueOf(sb);

            spaces[i] = sortedPlanets;

            for (int j = 0; j < i; j++) {
                if (spaces[i].equals(spaces[j])) ans++;
            }

        }

        System.out.println(ans);
    }
}
