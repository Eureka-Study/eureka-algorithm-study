//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16228 KB , 시간 : 128 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 사과나무 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] binary = new int[2 * N];
        char[] c = br.readLine().toCharArray();
        for (int i = 0; i < 2 * N; i++) {
            binary[i] = c[i] - '0';
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        Stack<Integer> stack = new Stack<>();
        int[][] tree = new int[N][2];
        int[] belongsToNode = new int[2 * N]; // x, y가 어느 노드인지 빠르게 파악하기 위해

        int size = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (binary[i] == 0) { //깊이 들어갈 때
                stack.push(size);
                tree[size][0] = i;
                belongsToNode[i] = size;
                size++;
            } else { // 나올 때
                int node = stack.pop();
                tree[node][1] = i;
                belongsToNode[i] = node;
            }
        }

        int nodeX = belongsToNode[x]; // x가 속한 node
        int nodeY = belongsToNode[y]; // y가 속한 node
        int xStart = tree[nodeX][0];
        int xEnd = tree[nodeX][1];
        int yStart = tree[nodeY][0];
        int yEnd = tree[nodeY][1];

        int resultStart = -1;
        int resultEnd = -1;
        int minRange = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int start = tree[i][0];
            int end = tree[i][1];

            if (start <= xStart && end >= xEnd && start <= yStart && end >= yEnd) {// 현 노드가 x, y의 상위 노드인지 체크
                int range = end - start;
                if (range < minRange) { // 상위 노드일 경우 이전에 체크했던 노드보다 더 가까운 노드인지 체크
                    minRange = range;
                    resultStart = start;
                    resultEnd = end;
                }
            }
        }
        System.out.println((resultStart + 1) + " " + (resultEnd + 1));
    }
}
