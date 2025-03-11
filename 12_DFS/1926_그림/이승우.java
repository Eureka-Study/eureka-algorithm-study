import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 이승우{
    public static void main(String[] args) throws IOException {
        final int[][] dXY = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        int[][] img = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++){
                img[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<int[]> deque = new ArrayDeque<>();
        boolean[][] isSelected = new boolean[n][m];

        int count = 0, dim = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(img[i][j] != 1 || isSelected[i][j]) continue;

                deque.offer(new int[]{j, i});
                isSelected[i][j] = true;
                count++;

                int newDim = 1;

                while(!deque.isEmpty()){
                    int[] nowPoint = deque.pollLast();

                    for(int[] d : dXY){
                        int[] newPoint = new int[]{nowPoint[0] + d[0], nowPoint[1] + d[1]};

                        if(newPoint[0] >= 0 && newPoint[0] < m
                        && newPoint[1] >= 0 && newPoint[1] < n
                        && img[newPoint[1]][newPoint[0]] == 1 && !isSelected[newPoint[1]][newPoint[0]]){
                            deque.offer(newPoint);
                            isSelected[newPoint[1]][newPoint[0]] = true;
                            newDim++;
                        }
                    }
                }

                dim = Math.max(dim, newDim);
            }
        }

        StringBuilder sb = new StringBuilder(3);

        sb.append(count)
          .append("\n")
          .append(dim);

        System.out.println(sb);
    }
}