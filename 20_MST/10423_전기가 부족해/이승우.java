
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 51324 KB , 시간 : 508 ms
import java.io.*;
import java.util.*;

public class 전기가부족해 {
    static int[] parent;
    static boolean[] hasPower;

    public static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb)
            return false;
        if (hasPower[ra] && hasPower[rb])
            return false;
        parent[rb] = ra;
        hasPower[ra] = hasPower[ra] || hasPower[rb];
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        hasPower = new boolean[N + 1];
        for (int i = 1; i <= N; i++) // 초기화
            parent[i] = i;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int p = Integer.parseInt(st.nextToken());
            hasPower[p] = true; // 발전소 위치 체크
        }

        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] e = new int[3];
            e[0] = Integer.parseInt(st.nextToken());
            e[1] = Integer.parseInt(st.nextToken());
            e[2] = Integer.parseInt(st.nextToken());
            edges[i] = e; // 연결 예상 경로 및 비용
        }

        Arrays.sort(edges, (n1, n2) -> n1[2] - n2[2]); // 비용별로 오름차순 정렬

        long ans = 0;
        int used = 0, target = N - K;
        for (int[] e : edges) {
            if (union(e[0], e[1])) { // 가능 여부 확인
                ans += e[2];
                if (++used == target) // 다 안돌아도 연결이 다 끝났으므로 더 이상 볼 필요 없음
                    break;
            }
        }

        System.out.println(ans);
    }
}