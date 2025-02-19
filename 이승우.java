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
        StringBuilder sb = new StringBuilder(); // 큐를 뺀 것을 저장

        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(br.readLine());

            if (x > 0) { // 자연수일 경우
                queue.add(x);
            }else{ // 0일 경우
                if(queue.isEmpty()){ // 큐가 비어있으면
                    sb.append("0\n"); //0을 출력
                }else{
                    sb.append(queue.poll()).append("\n"); //큐에서 빼고 그 값을 출력
                }
            }
        }

        System.out.println(sb);
    }
}