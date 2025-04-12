// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 :  333200KB , 시간 :  2116MS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정유민 {

    static Edge[] edges; //간선리스트 (도로 정보)
    static int N,M,sum; // N 집 , M 간선, sum : MST 비용
    static int[] parent; //각 집별 대표마을

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(v1 -1, v2 -1, c);
        }

        makeSet();

        Arrays.sort(edges,(e1, e2) -> e1.c - e2.c);// 간선 비용 기준으로 정렬

        // 크루스칼
        int cnt = 0;
        int edgesLen = edges.length;
        int maxEdge=0;
        for (int i = 0; i < edgesLen; i++) {
            Edge edge = edges[i];
            //cycle을 체크함. 없으면 선택
            if (union(edge.v1, edge.v2)) {
                sum+=edge.c;
                maxEdge = Math.max(maxEdge, edge.c);
                cnt++;
                if(cnt == N -1) break;
            }
        }
        System.out.println(sum - maxEdge); // 가장 비싼 비용 한 개 빼면 마을이 최소 비용으로 분리된다!

    }
    static void makeSet() { // 각 집은 처음에 자기 자신이 대표
        for (int i = 0; i < N; i++) {
            parent[i]= i; // 모두가 대표 마을이라고 치고 난제 점차점차 합친다.
        }
    }

    static boolean union(int x, int y) {
        int px = findSet(x); // 대표 마을
        int py = findSet(y); // 대표 마을

        if(px ==py) return false; // 이미 같은 마을.
        if(px <py) parent[py] = px;     // 다르면 마을 병합! 여기서 parent 배열이 바뀐다!!
        else parent[px] =py;
        return true;
    }

    //전달된 x 원소의 대표 원소 찾아서 return
    // 압축 기법
    static int findSet(int x) {
        if(parent[x]==x) return x; //자기 자신이 대표면 끝
        return parent[x] = findSet(parent[x]); // 대표 찾아가면서 경로 압축
    }

    //간선 클래스 <=int[]
    static class Edge {
        int v1, v2, c;

        public Edge(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }
}
