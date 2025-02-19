// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 26640KB , 시간 : 292ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class heap{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(br.readLine());

            if (x > 0) {
                queue.add(x);
            }else{
                if(queue.isEmpty()){
                    sb.append("0\n");
                }else{
                    sb.append(queue.poll()).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}