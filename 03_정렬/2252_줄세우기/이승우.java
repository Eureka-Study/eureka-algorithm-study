// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 47548 KB , 시간 : 480 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class 이승우{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();

        ArrayList<Integer>[] student = new ArrayList[N + 1];
        int[] deg = new int[N + 1];
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= N; i++){
            student[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
            student[A].add(B);
            deg[B]++;
        }

        for(int i = 1; i <= N; i++){
            if(deg[i] == 0) deque.offer(i);
        }

        while(!deque.isEmpty()){
            int person = deque.poll();
            sb.append(person)
              .append(" ");

            for(int next : student[person]){
                if(--deg[next] == 0) deque.offer(next);
            }
        }

        System.out.println(sb.toString().trim());
    }
}