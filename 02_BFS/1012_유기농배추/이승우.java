// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15816KB , 시간 : 156ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Napa{
    public static void main(String[] args) throws Exception{
        int[][] dxy = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //배추흰지렁이가 이동할 수 있는 경로
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); //배추흰지렁이가 필요한 개수를 담는 문자열

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int M = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            int[][] field = new int[N][M]; // 밭의 크기
            Deque<int[]> deque = new ArrayDeque<>(); // 지렁이의 경로를 담기위한 그릇
            boolean[][] isSelected = new boolean[N][M]; //지렁이가 지나갔는지 확인을 위한 배열
            int count = 0; //배추흰지렁이 개수

            for(int k = 0; k < K; k++){ //배추를 밭에 심기
                st = new StringTokenizer(br.readLine(), " ");
                int X = Integer.parseInt(st.nextToken()), Y = Integer.parseInt(st.nextToken());

                field[Y][X] = 1;
            }

            for(int n = 0; n < N; n++){ //배추 위치 찾기
                for(int m = 0; m < M; m++){
                    if(field[n][m] == 0 || isSelected[n][m]){ //만약 배추가 없거나 이미 탐색을 했다면 넘어가기
                        continue;
                    }

                    deque.offer(new int[]{m, n}); //현 위치를 담기(배추의 위치)
                    isSelected[n][m] = true;
                    count++;//배추가 있으므로 배추흰지렁이를 추가

                    // 하나의 배추흰지렁이가 갈 수 있는 모든 경로 탐색
                    while(!deque.isEmpty()){
                        int[] point = deque.pollLast();

                        for(int[] d : dxy){
                            if( point[1] + d[1] >= 0 && point[1] + d[1] < N && point[0] + d[0] >= 0 && point[0] + d[0] < M &&
                                field[point[1] + d[1]][point[0] + d[0]] == 1 && !isSelected[point[1] + d[1]][point[0] + d[0]]){
                                    deque.offer(new int[] {point[0] + d[0], point[1] + d[1]});
                                    isSelected[point[1] + d[1]][point[0] + d[0]] = true;
                            }
                        }
                    }

                }
            }
            //하나의 테스트 캐이스가 끝났기에 추가
            sb.append(count)
              .append("\n");
        }

        System.out.print(sb);
    }
}