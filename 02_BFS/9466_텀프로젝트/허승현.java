import java.io.*;
import java.util.*;

// 문제 정의
// 각 학생은 자신이 선택한 단 한 명의 학생만을 가리킨다.
// 어느 팀에도 속하지 못한 학생 수를 구하라

// 문제 해결 전략
// 사이클을 이루는 학생들만 팀이 될 수 있다.
// DFS로 돌면서 싸이클이 형성되는 순간을 체크하고, 그에 포함된 학생들을 팀원으로 표시
// DFS 탐색이 끝나기 위해선 무조건 싸이클에 들어가야 함
// 내가 지금까지 밟아온 경로 안에 그놈이 있냐를 체크

// Pseudocode

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 309732 KB , 시간 : 1232ms

public class Main {
    static int[] choice;
    static boolean[] visited;
    static boolean[] inCycle;
    static int notInTeam;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            choice = new int[n + 1];
            visited = new boolean[n + 1];
            inCycle = new boolean[n + 1];
            notInTeam = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                choice[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            System.out.println(notInTeam);
        }
    }

    static void dfs(int start) {
        List<Integer> path = new ArrayList<>();
        int current = start;

        // 사이클 찾을 때까지 경로 추적
        while (true) {
            if (visited[current]) {
                // 이미 방문한 적 있음
                if (path.contains(current)) {
                    // 경로 리스트 내에 있으면 => 사이클 시작점
                    int idx = path.indexOf(current);
                    for (int i = idx; i < path.size(); i++) {
                        inCycle[path.get(i)] = true;
                    }
                }
                break;
            }

            visited[current] = true;
            path.add(current);
            current = choice[current];
        }

        // 사이클에 포함되지 않은 노드 카운트
        for (int student : path) {
            if (!inCycle[student]) notInTeam++;
        }
    }
}
