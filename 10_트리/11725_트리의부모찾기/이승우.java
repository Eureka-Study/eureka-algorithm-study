// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 64088KB, 시간 : 572ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이승우 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] tree = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 1; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        int[] parent = new int[N + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while(!q.isEmpty()){
            int node = q.poll();

            for(int next : tree[node]){
                if(parent[next] == 0){
                    parent[next] = node;
                    q.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 2; i <= N; i++){
            sb.append(parent[i])
              .append("\n");
        }

        System.out.println(sb);
    }
}