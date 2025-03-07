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
            int count = 0;
            int n = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();

            q.offer(0);

            while(!q.isEmpty()){
                int num = q.poll();

                if(num == n){
                    count++;
                    continue;
                }

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

            sb.append(count)
              .append("\n");
        }

        System.out.print(sb);
    }    
}
