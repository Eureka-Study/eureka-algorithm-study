package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 문제 해결
// 최소 스패닝 트리를 구성하고 두 도시를 분할해야 하니까 가장 큰 간선을 하나 제거한다.
// 크루스칼 
// 가중치가 가장 작은 것부터 시작해서 간선이 잇는 두 정점이 같은 그룹이 아니라면 선택
// 두 정점이 이미 같은 그룹이라면 다시 선택했을 때 사이클이 형성

// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 329568 KB , 시간 : 2160ms
public class Main {

	static int N, M;
	static int parent[];
	static Edge[] edges;

	static class Edge {
		int u, v, c;

		public Edge(int u, int v, int c) {
			this.u = u;
			this.v = v;
			this.c = c;
		}

	}
	
	static void makeSet() {
		for (int  i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	// 같은 그룹인 지 확인
	static int findSet(int x) {
		if (parent[x] == x) return x;
		return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = findSet(parent[x]);
		int py = findSet(parent[y]);
		
		if (px == py) return false;
		else if (px < py) parent[py] = px;
		else parent[px] = py;
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 집의 개수
		M = Integer.parseInt(st.nextToken()); // 길의 개수
		
		parent = new int[N+1];
		edges = new Edge[M]; // 간선의 수가 주어졌으므로 배열 사용
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(u,v,c);
		}
		
		makeSet();
		
		// 간선을 비용(c)기준으로 정렬
		Arrays.sort(edges,(e1,e2)->e1.c-e2.c);
		
		int mstWeight = 0;
		int maxEdgeCost = 0; // 최소 스패닝을 이루는 간선 중 비용이 가장 큰 간선
		int edgesUsed = 0; // 사용한 간선의 수
		
		// 크루스칼
		for (int i = 0; i < M; i++) {
			Edge now = edges[i];
			if (findSet(now.u) == findSet(now.v)) continue;
			mstWeight += now.c;
			maxEdgeCost = Math.max(maxEdgeCost, now.c);
			union(now.u,now.v);
			edgesUsed++;
			
			if (edgesUsed == N - 1) {
				break;
			}
		}
		
		// 두 번째 최소 스패닝 트리의 비용 = 최소 스패닝 트리에서 가장 큰 간선을 제외한 비용
		System.out.println(mstWeight - maxEdgeCost);
	}

}
