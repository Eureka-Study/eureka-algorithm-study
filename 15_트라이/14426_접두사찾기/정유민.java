//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 298424 KB , 시간 : 616 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 정유민 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            trie.insert(br.readLine());
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            if (trie.search(br.readLine())) {
                res++;
            }
        }
        System.out.println(res);
    }

    static class Node{
        HashMap<Character, Node> child; // 각 노드의 자식 노드"들"을 저장한다.
        boolean isEnd;

        public Node() {
            this.child = new HashMap<>();
            this.isEnd=false;
        }
    }

    static class Trie{
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String str) {
            Node node = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                node.child.putIfAbsent(c, new Node());
                // 문자열 각 단어를 가져와서 자식노드 중에 있는지 체크하는데,
                // 없을때는 새로운 노드를 생성해서 넣는다.
                node= node.child.get(c);
                // 자식노드로 이동함.
            }
            node.isEnd= true; //문자열의 끝에 끝이라는 표시를 해준다.
        }

        boolean search(String str) { //접두사 check
            Node node = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!node.child.containsKey(c)) return false;
                node = node.child.get(c);
            }
            return true;
        }
    }
}
