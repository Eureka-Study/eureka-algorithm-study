
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 276180 KB , 시간 : 1132 ms
import java.io.*;
import java.util.*;

public class 소문난칠공주 {
    static char[][] board = new char[5][5];
    static int answer = 0;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }
        combination(new ArrayList<>(), 0); // 시작
        System.out.println(answer);
    }

    static void combination(List<Integer> comb, int start) {
        if (comb.size() == 7) { // 7개가 모이면
            if (isValid(comb)) // 조건에 부합한 칠공주인지
                answer++;
            return;
        }
        for (int i = start; i < 25; i++) {
            comb.add(i); // 넣었을 때 시작
            combination(comb, i + 1); // 이번 사람을 포함했을 때
            comb.remove(comb.size() - 1); // 안 넣었을 때 시작
        }
    }

    static boolean isValid(List<Integer> comb) {
        boolean[] selected = new boolean[25];
        int sCount = 0;
        for (int idx : comb) {
            selected[idx] = true;
            if (board[idx / 5][idx % 5] == 'S') // 이다솜 파인지 확인
                sCount++;
        }
        if (sCount < 4) // 이다솜 파가 4개 이하일 경우
            return false;
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[25];
        q.offer(comb.get(0));
        vis[comb.get(0)] = true;
        int cnt = 1;
        while (!q.isEmpty()) { // 이어있는지 확인하는 작업
            int cur = q.poll();
            int x = cur / 5, y = cur % 5;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d], nxt = nx * 5 + ny;
                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && selected[nxt] && !vis[nxt]) {
                    vis[nxt] = true;
                    q.offer(nxt);
                    cnt++;
                }
            }
        }
        return cnt == 7;
    }
}