// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11768 KB , 시간 : 68 ms

import java.io.*;
import java.util.*;

public class Main {    // 트리
    static int N;   // 노드 개수
    static int[] nodes;         // 부모들 리스트
    static int root, delete;    // 루트노드 번호, 삭제할노드 번호
    static int leaf;            // 리프노드 개수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parentNode = Integer.parseInt(st.nextToken());
            nodes[i] = parentNode;
            if (parentNode == -1) root = i;     // 만약 부모노드가 없다면 루트노드
        }
        delete = Integer.parseInt(br.readLine());

        if (delete == root) System.out.println(0);  // 루트노드를 삭제할거면 0 출력해버림
        else {
            bfs(root);      // 루트노드부터 bfs 돌기
            System.out.println(leaf);
        }
    }

    static void bfs(int root) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int parent = q.poll();  // "현재 부모노드"
            int count = 0;          // 현재 부모노드가 자식없는 leaf면 count가 0으로 남을 것

            for (int i = 0; i < N; i++) {   // for문으로 부모들 리스트 돌면서
                if (nodes[i] == parent) {   // 이번 노드의 부모가 "현재 부모노드"면
                    if (i != delete) {      // 그리고 삭제대상 노드가 아니면
                        q.offer(i);         // 큐에다 넣고
                        count++;            // 자식노드 있음을 알리기 위해 count 증가
                    }
                }
            }
            if (count == 0) leaf++;         // 만약 count 0이면 자식없는 leaf 였던 것
        }
    }
}
