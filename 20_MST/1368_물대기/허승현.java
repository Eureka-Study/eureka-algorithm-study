//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 32884KB , 시간 : 428ms

import java.io.*;
import java.util.*;

public class Main {

	static class Edge implements Comparable<Edge> {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	static int N;
	static ArrayList<Edge>[] graph;
	static PriorityQueue<Edge> pq;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Main.Edge>();
		}

		pq = new PriorityQueue<Main.Edge>();

		// 일단 물이 있어야 끌어오든 말든 하기 때문에 처음에 논에 우물을 파야함 -> 가장 비용이 작은 노드를 시작
		for (int i = 0; i < N; i++) {
			int weight = Integer.parseInt(br.readLine());
			pq.offer(new Edge(i, weight));
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				graph[i].add(new Edge(j, weight));
				graph[j].add(new Edge(i, weight));
			}

		}

		System.out.println(prim());

	}

	static int prim() {
		visited = new boolean[N];

		int minCost = 0;

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int vertex = edge.v;
			int weight = edge.w;

			if (visited[vertex])
				continue;

			visited[edge.v] = true;
			minCost += weight;

			for (Edge e : graph[vertex]) {
				if (!visited[e.v]) {
					pq.offer(e);
				}
			}

		}
		return minCost;
	}

}