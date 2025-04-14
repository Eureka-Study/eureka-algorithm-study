
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 69480 KB , 시간 : 792 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리와쿼리 {
    static List<Integer>[] tree;
    static boolean[] isSelected;
    static int[] treeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken()),
                Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];

        for (int n = 1; n <= N; n++) {
            tree[n] = new ArrayList<>(); // 트리 초기화
        }

        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");

            int U = Integer.parseInt(st.nextToken()), V = Integer.parseInt(st.nextToken());

            tree[U].add(V);
            tree[V].add(U);
        }

        isSelected = new boolean[N + 1];
        treeSize = new int[N + 1];
        dfs(R);

        StringBuilder sb = new StringBuilder(2 * Q); // 2배 길이를 가지므로 2배 지정

        for (int q = 0; q < Q; q++) {
            int U = Integer.parseInt(br.readLine());

            sb.append(treeSize[U]).append("\n");
        }

        System.out.print(sb);

    }

    static int dfs(int node) {
        isSelected[node] = true; // 이 노드가 돌았음을 체크
        int count = 1;

        for (int nextNode : tree[node]) { // 다음 노드 찾기
            if (!isSelected[nextNode]) // 다음 노드를 탐색했는지 파악
                count += dfs(nextNode); // 탐색안했다면 상위 노드에서 하위 노드로 가는 것이 맞기 때문에 하위 노드의 하위 노드를 파악
        }
        treeSize[node] = count; // 현 노드의 하위 노드 개수 저장
        return count; // 상위 노드로 현 노드의 하위 노드 개수 반환
    }
}