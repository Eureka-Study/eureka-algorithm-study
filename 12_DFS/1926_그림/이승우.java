// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 58240 KB , 시간 : 416 ms
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
                if(img[i][j] != 1 || isSelected[i][j]) continue; // 이미 지나갔거나 그림이 아니라면 건너뛰기

                deque.offer(new int[]{j, i}); // 그림이므로 넣기
                isSelected[i][j] = true;
                count++; // 최초의 그림이므로 + 1

                int newDim = 1;

                while(!deque.isEmpty()){
                    int[] nowPoint = deque.pollLast();

                    for(int[] d : dXY){ // 경로 탐색
                        int[] newPoint = new int[]{nowPoint[0] + d[0], nowPoint[1] + d[1]}; // 다음 경로

                        if(newPoint[0] >= 0 && newPoint[0] < m // 정상 범위 인지
                        && newPoint[1] >= 0 && newPoint[1] < n // 정상 범위 인지
                        && img[newPoint[1]][newPoint[0]] == 1 && !isSelected[newPoint[1]][newPoint[0]]){ // 그림 인지, 이미 돈 상태인지 확인
                            // 통과하였다면 정상적으로 수행해도 되는 구문들
                            deque.offer(newPoint);
                            isSelected[newPoint[1]][newPoint[0]] = true;
                            newDim++; // 그림의 넓이 추가
                        }
                    }
                }

                dim = Math.max(dim, newDim); // 과거 그림의 넓이와 현 그림의 넓이 중 누가 더 큰지 확인
            }
        }

        StringBuilder sb = new StringBuilder(3);

        sb.append(count)
          .append("\n")
          .append(dim);

        System.out.println(sb);
    }
}