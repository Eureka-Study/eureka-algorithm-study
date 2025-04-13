
// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 330272 KB , 시간 : 1476 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 도시분할계획 {

    static class Brige {
        int from, to, cost;

        public Brige(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int[] brige;

    static int find(int x) { // x가 속한 루트 노드 찾기
        if (brige[x] == x) // 자기 자신일 경우
            return x;
        return brige[x] = find(brige[x]); // 경로 압축
    }

    static void union(int from, int to) { // 두개를 하나로
        from = find(from);
        to = find(to);

        if (from != to) // 같은 경로가 아니라면 to를 from 밑으로 붙인다.
            brige[to] = from;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        brige = new int[N + 1];
        List<Brige> list = new ArrayList<>();

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()),
                    cost = Integer.parseInt(st.nextToken());

            list.add(new Brige(from, to, cost));
        }

        for (int n = 1; n <= N; n++) {
            brige[n] = n; // 우선 자기 자신을 가리킨다
        }

        list.sort((a, b) -> a.cost - b.cost); // cost 오름차순으로 정렬

        int total = 0, max = 0;

        for (Brige b : list) {
            if (find(b.from) != find(b.to)) { // 같은 경로가 아닐 경우
                union(b.from, b.to); // 서로 합하기
                total += b.cost; // 총 유지비 구하기
                max = Math.max(max, b.cost); // 가장 큰 유지비
            }
        }

        System.out.println(total - max);
    }
}