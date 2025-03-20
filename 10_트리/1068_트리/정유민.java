import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리 {
    static int n;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1)
                matrix[parent][j] = 1;
        }

        int r = Integer.parseInt(br.readLine());
        removeNode(r);

        int leafCnt = 0;
        for (int i = 0; i < n; i++) {
            if (matrix[i] == null)
                continue;

            boolean isLeaf = true;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    isLeaf = false;
                    break;
                }
            }
            if (isLeaf)
                leafCnt++;
        }

        System.out.println(leafCnt);
    }

    static void removeNode(int r) {
        for (int i = 0; i < n; i++) {
            if (matrix[i] != null)
                matrix[i][r] = -1;
        }
        for (int j = 0; j < n; j++) {
            if (matrix[r][j] == 1)
                removeNode(j); //자식노드 제거
        }
        matrix[r] = null;
    }
}
