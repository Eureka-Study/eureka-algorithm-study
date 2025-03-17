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
            student[A].add(B); // A 뒤에 B가 있어야 함
            deg[B]++; // B가 누군가의 더 뒤에 있어야할 확률이 있으므로 깊이를 카운트
        }

        for(int i = 1; i <= N; i++){
            if(deg[i] == 0) deque.offer(i); // 0이면 아무도 앞에 없는 것이므로 누구든 처음으로 올수 있다.
        }

        while(!deque.isEmpty()){
            int person = deque.poll(); // 줄 서는 사람을 줄에 배치
            sb.append(person)
              .append(" ");

            for(int next : student[person]){ // 줄에 배치된 사람에 뒤에 배치될 사람에 깊이를 하나 빼서 0이 되었으면 들어와도 되므로 deque에 추가
                if(--deg[next] == 0) deque.offer(next);
            }
        }

        System.out.println(sb.toString().trim());
    }
}