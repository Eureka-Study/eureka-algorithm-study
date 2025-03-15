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

            if(nowPoint[0] == (M - 1) && nowPoint[1] == (N - 1)){ // 현재 위치가 최종 위치면 끝내기
                System.out.println(walk[nowPoint[1]][nowPoint[0]]);
                return;
            }

            int newCount = walk[nowPoint[1]][nowPoint[0]] + 1; // 한칸 이동이 된것이므로 현 위치에서 + 1

            for(int[] d : dXY ){
                int[] newPoint = {nowPoint[0] + d[0], nowPoint[1] + d[1]}; // 다음 위치

                if(newPoint[0] >= 0 && newPoint[0] < M // 위치가 지금 정상 범위 안 인지 확인
                && newPoint[1] >= 0 && newPoint[1] < N // 위치가 지금 정상 범위 안 인지 확인
                && maze[newPoint[1]][newPoint[0]] == 1 // 미로에서 갈 수 있는 경로 인지 확인
                && walk[newPoint[1]][newPoint[0]] == 0){ // 이미 지나간 경료인지 확인
                    queue.offer(new int[]{newPoint[0], newPoint[1]}); // 정상적이라면 수행
                    walk[newPoint[1]][newPoint[0]] = newCount;
                }
            }
        }
        System.out.println(walk[N - 1][M - 1]);

    }
}