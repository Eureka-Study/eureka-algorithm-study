// 언어 : JAVA , (성공/실패) : 2/0 , 메모리 : 17912KB , 시간 : 156ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class color {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[][] map = new String[N][N]; // 일반인
        String[][] rbMap = new String[N][N]; //적록색약
        boolean[][] isSelected = new boolean[N][N]; //일반인
        boolean[][] rbIsSelected = new boolean[N][N]; //적록색약

        int rgb = 0, rb = 0;

        for(int i = 0; i < N; i++){
            map[i] = br.readLine().split(""); //일반인
            for(int j = 0; j < N; j++){
                rbMap[i][j] = map[i][j].equals("G") ? "R" : map[i][j]; // 적록색약인은 초록과 빨강을 구별 못하기 때문에 R로 고정
            }
        }

        rgb = BFS(map, isSelected); //일반인

        rb = BFS(rbMap, rbIsSelected); //적록색약

        StringBuilder sb = new StringBuilder(3);//3번만 추가하기 때문에 3으로 고정

        sb.append(rgb)
          .append(" ")
          .append(rb);

        System.out.println(sb);
    }

    static int BFS(String[][] map, boolean[][] isSelected){
        Deque<int[]> deque = new ArrayDeque<>();
        int N = map.length;
        int count = 0;
        int[][] dXY = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 같은 색상이 인접한지 확인용

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(isSelected[i][j]){ // 이미 확인했으면 넘김
                    continue;
                }

                deque.offer(new int[]{j, i}); // 신규영역 데크에 넣기
                isSelected[i][j] = true;
                count++; //새로운 영역이라 추가

                /**
                 * 같은색이면 같은 영역이기 때문에 전부 돈다.
                 */
                while (!deque.isEmpty()) {
                    int[] nowXY = deque.poll();

                    for(int[] d : dXY){
                        int[] newXY = new int[] {nowXY[0] + d[0], nowXY[1] + d[1]};

                        if(newXY[0] >= 0 && newXY[0] < N && newXY[1] >= 0 && newXY[1] < N &&
                           map[nowXY[1]][nowXY[0]].equals(map[newXY[1]][newXY[0]]) && !isSelected[newXY[1]][newXY[0]]){
                            deque.offer(new int[]{newXY[0], newXY[1]});
                            isSelected[newXY[1]][newXY[0]] = true;
                        }
                    }
                }
            }
        }
        return count;
    }
}
