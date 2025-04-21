import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 문제 해결
// 반드시 거쳐야 하는 정점이 포함되도록 다익스트라를 설계해보자

//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 69688 KB , 시간 : 572ms

public class Main {

	static final int INF = 200_000_100;
	static int N, E;
	static ArrayList<Node>[] graph;
	static int[] dist;

	static class Node implements Comparable<Node> {
		int vertex, weight;

		Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		dist = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Main.Node>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int dist1 = 0; // v1 -> v2일 때 최단거리
		int dist2 = 0; // v2 -> v1일 때 최단거리

		// v1 -> v2
		dist1 += dijkstra(1, v1);
		dist1 += dijkstra(v1, v2);
		dist1 += dijkstra(v2, N);

		// v2 -> v1
		dist2 += dijkstra(1, v2);
		dist2 += dijkstra(v2, v1);
		dist2 += dijkstra(v1, N);

		int result = (dist1 >= INF && dist2 >= INF ? -1 : Math.min(dist1, dist2));

		System.out.println(result);

	}

	static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<Main.Node>();

		int[] dist = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			dist[i] = INF;
		}

		pq.add(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curV = cur.vertex;
			int curW = cur.weight;

			if (dist[curV] < curW)
				continue;

			for (Node next : graph[curV]) {
				int cost = dist[curV] + next.weight;
				if (cost < dist[next.vertex]) {
					dist[next.vertex] = cost;
					pq.offer(new Node(next.vertex, cost));
				}
			}
		}
		return dist[end];
	}
}
