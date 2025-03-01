// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 141700KB , 시간 : 556ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class 이승우{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] list = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];


        for(int i = 1; i <= N; i++){
            list[i] = new ArrayList<>();
        }

        for(int m = 1; m <= M; m++){
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }

        int count = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 1; i <= N; i++){
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            deque.offer(i);

            count++;

            while (!deque.isEmpty()) {
                int connect = deque.pollFirst();

                for(int j : list[connect]){
                    if(!visited[j]){
                        visited[j] = true;
                        deque.offer(j);
                    }
                }

            }
        }

        System.out.println(count);
    }
}