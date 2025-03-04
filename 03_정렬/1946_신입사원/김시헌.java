// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 311632 KB , 시간 : 2732 ms

import java.io.*;
import java.util.*;

public class Main {  // 신입 사원
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());    // 테케 개수

        int[] ans = new int[T]; // 테케 개수만큼 정답 담는 배열

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());    // 지원자 수
            int[][] list = new int[N][2];   // 지원자들 성적 배열

            StringTokenizer st;
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                list[j][0] = Integer.parseInt(st.nextToken());  // 서류석차
                list[j][1] = Integer.parseInt(st.nextToken());  // 면접석차
            }

            Arrays.sort(list, (o1, o2) -> o1[0] - o2[0]);   // 람다식을 활용한 2차원배열 정렬 (서류성적 기준 오름차순)

            int count = 1;      // 선발 가능 신입사원 (서류 1등은 일단 합격이니까 count를 1로 초기화)
            int interviewBest = list[0][1]; // 면접 최고석차 (일단 서류 1등의 면접성적으로 초기화)
            for (int j = 0; j < N; j++) {
                int interview = list[j][1]; // 현재 후보자의 면접석차
                if (interview < interviewBest) {   // 만약 면접석차가 현재까지의 최고 면접석차보다 더 좋으면
                    count += 1;     // 카운트 늘리고
                    interviewBest = interview;  // 최고 면접석차 갱신해주고
                }
                if (interview == 1) break;      // 만약 면접석차 1위면, 뒤에꺼 볼필요도 없으니 break
            }

            ans[i] = count;     // 정답배열의 현재 테케 위치에 카운트값 넣어주면 for문 끝~!
        }

        for (int i = 0; i < T; i++) {
            bw.write(String.valueOf(ans[i]));   // 출력할거 담아주고
            bw.newLine();   // 개행하고
        }
        bw.flush(); // 출력 배출
        bw.close();
        br.close();

    }
}
