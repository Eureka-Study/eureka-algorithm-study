// 언어 : JAVA , (성공/실패) : 1/4 , 메모리 : 14268KB , 시간 : 104ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computer = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> virus = new LinkedList<>();
        boolean[] visited = new boolean[computer];
        int count = 0;
        virus.add(0);
        visited[0] = true;

        ArrayList<Integer>[] connect = new ArrayList[computer];
        for(int i = 0; i < computer; i++){
            connect[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1;

            connect[a].add(b);
            connect[b].add(a);
        }

        while (!virus.isEmpty()) {
            int v = virus.poll();
            
            for(int i : connect[v]){
                if (!visited[i]) {
                    visited[i] = true;
                    virus.add(i);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}