//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 57528KB , 시간 : 464ms

import java.io.*;
import java.util.*;

public class Main {

	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

	}
	
	static class Node implements Comparable<Node> {
        int node;
        int weight;
        Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        public int compareTo(Node o) {
            return o.weight - this.weight; // 최대 중량 우선
        }
    }
	
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
		
		st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        visited = new boolean[N + 1];
        int result = dijkstra(A,B);
		System.out.println(result);
		
	}
	
	static int dijkstra(int A, int B) {
		PriorityQueue<Node> pq = new PriorityQueue<Main.Node>();
		pq.offer(new Node(A, Integer.MAX_VALUE)); // 가장 무게가 큰 경로를 먼저 탐색 (시작점 A에서 출발할 때 중량은 무제한으로 시작)
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int node = cur.node;
				
			if (visited[node]) continue;
			
			visited[node] = true;
			
			if (node == B) return cur.weight; //(= 지금까지 온 경로의 최소 중량 반환)
			
			for (Edge next : graph[node]) {
				if (!visited[next.v]) {
					int minWeight = Math.min(cur.weight, next.w);
					pq.offer(new Node(next.v,minWeight));
				}
			}
		}
		return 0;
	}
}