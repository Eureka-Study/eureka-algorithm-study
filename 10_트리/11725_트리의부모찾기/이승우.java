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

            // a, b 두 노드 중 정점이 정해지지않아서 일단 양방향으로 연결
            tree[a].add(b);
            tree[b].add(a);
        }

        int[] parent = new int[N + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(1); //루트 노드

        while(!q.isEmpty()){
            int node = q.poll(); //현 노드 추출

            for(int next : tree[node]){ // 현 노드와 연결된 노드들
                if(parent[next] == 0){ // 부모 노드가 정해지지않았다면 현 노드가 부모 노드
                    parent[next] = node;
                    q.offer(next); // 부모와 자식을 연결했으므로 그 자식의 자식을 찾기위해 큐에 넣기
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 2; i <= N; i++){
            sb.append(parent[i]) // 현 노드의 부모노드를 출력
              .append("\n");
        }

        System.out.println(sb);
    }
}