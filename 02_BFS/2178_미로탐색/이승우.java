// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 17316 KB , 시간 : 164 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이승우{
    public static void main(String[] args) throws Exception{
        final int[][] dXY = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M =  Integer.parseInt(st.nextToken());

        int[][] maze = new int[N][M];
        int[][] walk = new int[N][M];

        for(int n = 0; n < N; n++){
            String[] load = br.readLine().split("");
            for(int m = 0; m < M; m++){
                maze[n][m] = Integer.parseInt(load[m]);
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        walk[0][0] = 1;

        while(!queue.isEmpty()){
            int[] nowPoint = queue.poll();

            if(nowPoint[0] == (M - 1) && nowPoint[1] == (N - 1)){
                System.out.println(walk[nowPoint[1]][nowPoint[0]]);
                return;
            }

            int newCount = walk[nowPoint[1]][nowPoint[0]] + 1;;

            for(int[] d : dXY ){
                int[] newPoint = {nowPoint[0] + d[0], nowPoint[1] + d[1]};

                if(newPoint[0] >= 0 && newPoint[0] < M
                && newPoint[1] >= 0 && newPoint[1] < N
                && maze[newPoint[1]][newPoint[0]] == 1
                && walk[newPoint[1]][newPoint[0]] == 0){
                    queue.offer(new int[]{newPoint[0], newPoint[1]});
                    walk[newPoint[1]][newPoint[0]] = newCount;
                }
            }
        }
        System.out.println(walk[N - 1][M - 1]);

    }
}