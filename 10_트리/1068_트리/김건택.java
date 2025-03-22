// 언어 : JAVA , (성공/실패) : 1/3 , 메모리 : 14264 KB , 시간 : 104 ms

mport java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] tree = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = -1;
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i; // 루트 노드 저장
            } else {
                tree[parent].add(i);
            }
        }

        int target = Integer.parseInt(br.readLine());

        if (target == root) {
            System.out.println(0);
            return;
        }



        int count = 0;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            // 노드를 실제로 삭제하지 않기 때문에 count 를 증가시키기 전에 현재 노드가 leaf 인지 확인해야함
            boolean isLeaf = true;

            for (int next : tree[node]) {
                if (next == target) continue; // 삭제된 노드는 건너뛴다.
                queue.add(next);
                isLeaf = false; // 자식이 하나라도 있으면 리프가 아님
            }

            if (isLeaf) count++;
        }

        System.out.println(count);
    }
}
