// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14360 KB , 시간 : 108 ms

import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<int[]> pqueue = new PriorityQueue<>( (v1, v2) -> v1[1] - v2[1] );
    static int[] cost;
    static int s, e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        s = 0;
        e = D;

        String[] lines = new String[N];
        for (int i = 0; i < N; i++) {
            lines[i] = br.readLine();
        }

        // 0 ~ D 까지 모두 노드로 사용하지 않고 필요한 노드만 사용하기 위한 작업
        // 예제 1 을 예로 들면 0, 50, 100, 150 만을 뽑기 위한 작업
        Set<Integer> set = new TreeSet<>();
        for (String line : lines) {
            st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 도착 위치가 목표 위치를 넘거나 지름길이 아닌 경우 스킵
            if (end > D || cost > end - start) continue;

            set.add(start);
            set.add(end);
        }
        set.add(0);
        set.add(D);

        cost = new int[D+1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        // 필요한 노드만 뽑은 후 각 노드에서 노드로의 edge를 추가하기 위한 작업
        ArrayList<Integer> list = new ArrayList<>(set);
        Map<Integer, ArrayList<int[]>> graph = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            graph.putIfAbsent(list.get(i), new ArrayList<>());
            if (i < list.size() - 1) {
                graph.get(list.get(i)).add(new int[]{list.get(i + 1), list.get(i + 1) - list.get(i)});
            }
        }

        // 입력받은 지름길 입력
        for (String line : lines) {
            st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (end > D || cost > end - start) continue;

            graph.get(start).add(new int[]{end, cost});
        }

        // 다익스트라
        cost[s] = 0;
        pqueue.offer(new int[]{s, 0});
        while (!pqueue.isEmpty()) {
            int[] shortcut = pqueue.poll();

            if (cost[shortcut[0]] > shortcut[1]) continue;

            for (int[] next : graph.get(shortcut[0])) {
                if (cost[next[0]] > shortcut[1] + next[1]) {
                    cost[next[0]] = shortcut[1] + next[1];
                    pqueue.offer(new int[]{next[0], cost[next[0]]});
                }
            }
        }
        System.out.println(cost[e]);
    }

    static class Shortcut {
        int start, end, cost;
        public Shortcut(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}