// 언어 : JAVA , (성공/실패) : 0/2 , 메모리 :  KB , 시간 :  ms

import java.io.*;
import java.util.*;

public class Silver_11725 {     // 트리의 부모 찾기
    static int N;
    static int[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new int[N+1];   // 각 노드의 번호에 부모노드 번호를 넣기
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            if (node1 == 1) graph[node2] = 1;           // 둘 중에 하나라도 1인 노드가 있으면
            else if (node2 == 1) graph[node1] = 1;      // 그 외의 노드의 부모를 1로 지정
            else {                                      // 둘 다 1이 아니면
                if (graph[node1] == 0) graph[node1] = node2;    // node1 부모칸이 비어있으면 node2가 부모
                if (graph[node2] == 0) graph[node2] = node1;   // node2 부모칸이 비어있으면 node1이 부모
            }
        }

        StringBuilder sb = new StringBuilder(N-1);
        for (int i = 2; i <= N; i++) {
            sb.append(graph[i]);
            if (i != N) sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }

}
