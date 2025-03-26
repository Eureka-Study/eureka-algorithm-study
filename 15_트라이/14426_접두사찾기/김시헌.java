// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 39908 KB , 시간 : 412 ms

import java.io.*;
import java.util.*;

public class Main {     // 접두사 찾기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node root = new Node();

        // 트라이 생성
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            Node parent = root;
            for (int j = 0; j < word.length(); j++) {
                char key = word.charAt(j);
                if (!parent.children.containsKey(key)) {    // 이번 문자가 트리구조 타고내려오는 과정에 없으면
                    parent.children.put(key, new Node(key));    // 추가
                }
                parent = parent.children.get(key);      // 부모노드를 해당 문자노드로 이동시킴
            }
        }

        int cnt = 0;

        // 트라이 검색
        for (int i = 0; i < M; i++) {
            String word = br.readLine();
            Node parent = root;
            for (int j = 0; j < word.length(); j++) {
                char key = word.charAt(j);
                if (!parent.children.containsKey(key)) break;   // 이번 문자가 트리구조 타고내려오는 과정에 없으면 이 단어는 아닌 것
                parent = parent.children.get(key);  // 있으면 부모노드를 해당 문자노드로 이동시킴
                if (j == word.length() - 1) cnt++;
            }
        }

        System.out.println(cnt);
    }

    static class Node {
        char key;
        Map<Character, Node> children = new HashMap<>();    // 셀프참조 (재귀적으로)

        Node() {}   // 루트용 기본생성자

        Node(char key) {    // 자식용 생성자
            this.key = key;
        }
    }

}

