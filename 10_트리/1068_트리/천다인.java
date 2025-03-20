//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14320 KB , 시간 : 108 ms
import java.io.*;
import java.util.*;
class Main {
    static List<Integer>[] tree;
    static int root, deleteNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree[parent].add(i);
            }
        }

        deleteNode = Integer.parseInt(br.readLine());

        if (deleteNode == root) {
            System.out.println(0);
            return;
        }
        System.out.print(countLeafNodes(root));
    }

    static int countLeafNodes(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        int leafCount = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == deleteNode) continue;

            List<Integer> children = tree[current];
            int childCount = 0;

            for (int c : children) {
                if (c != deleteNode) {
                    queue.add(c);
                    childCount++;
                }
            }

            if (childCount == 0) {
                leafCount++;
            }
        }
        return leafCount;
    }
}