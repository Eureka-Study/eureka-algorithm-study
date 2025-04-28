import java.io.*;
import java.util.*;

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 48528 KB , 시간 : 488ms

// 문제 핵심
// 발전소가 설치된 도시는 집합의 루트 역할을 해야한다.
// 발전소끼리는 연결을 하지 않는다.(발전소들을 같은 집합으로 만들기)

public class Main {

	static int N, M, K;
	static int[] parent;
	static int[] power;
	static Edge[] edges;

	static class Edge {
		int u, v, w;

		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;

		}
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = findSet(parent[x]);
	}

	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px == py)
			return false;

		parent[py] = px;

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		power = new int[K];

		// 발전소
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			power[i] = Integer.parseInt(st.nextToken());
		}

		// 케이블 초기화
		edges = new Edge[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(u, v, w);
		}

		// 부모 초기화
		makeSet();
		// 발전소가 설치된 도시 같은 집합으로 초기화
		for (int i = 0; i < K-1; i++) {
			union(power[i],power[i+1]);
		}
		
		// 간선 비용 기준 오름차순
		Arrays.sort(edges, (e1, e2) -> e1.w - e2.w);
		
		int totalCost = 0;
		
		for (Edge edge : edges) {
			int u = edge.u;
			int v = edge.v;
			int w = edge.w;
			
			if(union(u,v)) { // 다른 집합이면 연결하고 비용추가
				totalCost += w;
			}
		}
		
		System.out.println(totalCost);

	}

}
