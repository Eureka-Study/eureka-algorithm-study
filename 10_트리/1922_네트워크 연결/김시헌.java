// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 54552 KB , 시간 : 560 ms

import java.io.*;
import java.util.*;

public class Main {    // 네트워크 연결
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Node>[] graph = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        boolean[] visit = new boolean[N+1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (A == B) continue;
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>( (n1, n2) -> n1.cost - n2.cost );  // cost로 오름차순
        for (int i = 1; i <= N; i++) {
            ArrayList<Node> list = graph[i];
            if (!list.isEmpty()) {                 // list가 안비었으면, 해당 list의 index를 시작노드로 설정하고 PQ에 넣어주기
                pq.offer(new Node(i, 0));     // => 시작노드까지 0의 비용으로 간다는 의미
                break;
            }
        }

        int ans = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visit[current.tgt]) continue;   // 이미 tgt 방문했으면 넘어가
            visit[current.tgt] = true;          // 아니면 tgt 방문처리

            ans += current.cost;    // tgt 방문했으니 비용 더하기

            ArrayList<Node> list = graph[current.tgt];  // 이번노드(tgt)에서 갈 수 있는 노드들(다음 tgt들)
            for (int i = 0; i < list.size(); i++) {
                Node next = list.get(i);
                if (!visit[next.tgt]) pq.offer(next);   // tgt 아직 방문 안했다면 PQ에 넣어주기
            }
        }

        System.out.println(ans);
    }

    static class Node {
        int tgt, cost;
        Node(int tgt, int cost) {
            this.tgt = tgt; this.cost = cost;
        }
    }
}
