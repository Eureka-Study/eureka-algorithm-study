// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14204KB , 시간 : 108ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 이승우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++){
            int count = 0; // 경우의 수
            int n = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>(); // 과정을 담을 큐

            q.offer(0); // 0부터 시작

            while(!q.isEmpty()){
                int num = q.poll();

                if(num == n){ // n이 되면 하나의 경우니까 증가
                    count++;
                    continue;
                }

                // 1, 2, 3을 더하는데 n을 넘지않는 범위만 계산하여 넣기
                if(num + 1 <= n){
                    q.offer(num + 1);
                }
                if(num + 2 <= n){
                    q.offer(num + 2);
                }
                if(num + 3 <= n){
                    q.offer(num + 3);
                }   
            }

            // 하나의 태스트에서의 경우의 수 출력
            sb.append(count)
              .append("\n");
        }

        System.out.print(sb);
    }    
}
