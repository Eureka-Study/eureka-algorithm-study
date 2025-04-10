// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 83020 KB , 시간 : 520 ms

import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dp;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());   // 루트
        Q = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            list.get(U).add(V);
            list.get(V).add(U);
        }

        StringBuilder sb = new StringBuilder();
        dp = new int[N+1];
        visit = new boolean[N+1];

        dfs(R); // 루트 R 부터 dfs로 트리구성 시작

        for (int i = 0; i < Q; i++) {   // 답
            int subRoot = Integer.parseInt(br.readLine());

            sb.append(dp[subRoot]).append("\n");
        }

        System.out.println(sb);
    }

    static int dfs(int node) {
        int cnt = 1;    // 서브트리 시작, node 루트로 자기자신 포함시키기
        visit[node] = true; // 방문처리 (다음에 node가 또 등장하면, 걔는 parent 취급 받을 것)

        ArrayList<Integer> children = list.get(node);
        for (int i = 0; i < children.size(); i++) {
            int child = children.get(i);

            if (!visit[child]) {
                cnt += dfs(child);  // 방문 안했다면 child 이므로, dfs로 서브트리 돌려
            }
        }
        dp[node] = cnt;     // dp 갱신

        return cnt;
    }
}