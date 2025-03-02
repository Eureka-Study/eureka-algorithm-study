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
        ArrayList<Integer>[] list = new ArrayList[N + 1]; // 연결된 요소를 리스트 형식으로 넣음
        boolean[] visited = new boolean[N + 1]; //연결 여부


        for(int i = 1; i <= N; i++){ // 초기화
            list[i] = new ArrayList<>();
        }

        for(int m = 1; m <= M; m++){
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());

            // 그래프 쌍으로 연결
            list[u].add(v);
            list[v].add(u);
        }

        int count = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 1; i <= N; i++){
            if(visited[i]){ // 이미 확인한 것이므로 넘기기
                continue;
            }
            visited[i] = true;
            deque.offer(i);

            count++; //확인이 안된 것이므로 새로운 연결이라고 판단

            while (!deque.isEmpty()) { // 한 연결에 어디까지 연결되는지 파악하기
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