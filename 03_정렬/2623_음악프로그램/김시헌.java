// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 13956 KB , 시간 : 104 ms

import java.io.*;
import java.util.*;

public class Main {    // 음악프로그램 (위상정렬 알고리즘 사용)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N+1];    // 가수별로 ArrayList 한개씩
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] degree = new int[N+1];    // 가수의 차수 설정 => 내가 후순위일때마다 차수 1개 추가 (1순위면 차수=0)

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int[] singers = new int[num];   // 이번 조감독의 가수들 리스트
            for (int j = 0; j < num; j++) {
                singers[j] = Integer.parseInt(st.nextToken());
            }
            int n = 0;
            while (n <= num-1) {    // 조감독 리스트에서 후순위 가수들을 선순위 가수들 graph에 넣어주기
                int bigger = singers[n];
                for (int j = n+1; j < num; j++) {
                    graph[bigger].add(singers[j]);
                    degree[singers[j]]++;
                }

                n++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) q.offer(i); // 차수 0인 선수위 가수들 큐에 먼저 넣어주고 시작
        }

        StringBuilder sb = new StringBuilder();
        int check = 0;  // 순서 성립 여부 확인용

        while (!q.isEmpty()) {
            int node = q.poll();
            sb.append(node).append("\n");
            check++;

            for (int i = 0; i < graph[node].size(); i++) {
                int singer = graph[node].get(i);
                if (degree[singer] == 1) {  // 다음으로 차수가 1인 가수들 큐에 넣어주기
                    q.offer(singer);
                } else {    // 차수가 1이 아니면 차수를 감소시켜주기 (이렇게 해서 차수가 1이 되면 다음번엔 if문에 걸리겠지)
                    degree[singer]--;
                }
            }
        }

        if (check == N) System.out.println(sb);     // 확인이랑 N이랑 같으면 sb 출력
        else System.out.println(0); // 아니면 0 출력
    }
}
