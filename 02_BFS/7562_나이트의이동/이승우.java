// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 78916KB , 시간 : 352ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class nate{
    public static void main(String[] args) throws IOException {
        int[][] move = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());

        for(int i = 0; i < test; i++){
            int l = Integer.parseInt(br.readLine());
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] load = new boolean[l][l];

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] first = new int[3];

            first[1] = Integer.parseInt(st.nextToken());
            first[2] = Integer.parseInt(st.nextToken());

            queue.add(first);
            load[first[1]][first[2]] = true;

            st = new StringTokenizer(br.readLine());

            int[] last = new int[2];

            last[0] = Integer.parseInt(st.nextToken());
            last[1] = Integer.parseInt(st.nextToken());

            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                if(now[1] == last[0] && now[2] == last[1]){
                    System.out.println(now[0]);
                    break;
                }
                for(int[] m : move){
                    int x = now[1] + m[0];
                    int y = now[2] + m[1];

                    if( x < l && x >= 0 && y < l && y >= 0 && !load[x][y]){
                        queue.add(new int[]{now[0] + 1, x, y});
                        load[x][y] = true;
                    }
                }
            }
        }
    }
}