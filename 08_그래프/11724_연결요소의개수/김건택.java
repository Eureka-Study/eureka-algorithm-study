// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 114236 KB , 시간 : 432 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        parent = new int[v + 1];

        // 각 원소의 대표 원소가 자기를 가르키도록 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x, y);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= v; i++) {
            set.add(findSet(i));
        }

        System.out.println(set.size());
    }

    // 각 원소가 속한 집합의 대표 원소를 출력
    static int findSet(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findSet(parent[x]);
    }

    //  전달된 두 원소 x, y 에 대해, x가 속한 집합과 y가 속한 집합을 하나의 집합으로 합친다.
    static void union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);
        // px == py 이면 이미 같은 집합
        // 그렇지 않은 경우 규칙부여할 수 있다. 아래 코드는 작은 값을 부모로.
        if (px < py) parent[py] = px;
        else parent[px] = py;
    }
}