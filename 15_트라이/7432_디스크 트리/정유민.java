// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 :  67948 , 시간 :  2336ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            trie.insert(s);
        }

        trie.print(trie, 0);
    }

    static class Trie {
        Map<String, Trie> child = new HashMap<>();

        public void insert(String word) {
            Trie trie = this;
            String[] split = word.split("\\\\");// 역슬래쉬 한 개 표현 + 정규식 때문에 역슬래쉬 한 개 더
            for (String s : split) {
                trie.child.putIfAbsent(s, new Trie()); // 자식 없으면 새로운 자식 만든다.
                trie = trie.child.get(s);
            }
        }
        public void print(Trie now, int depth) {
            Trie trie = now;
            if(trie.child != null) { //루트 trie 노드의 자식이 없을 때 스탑.
                List<String> list = new ArrayList<>(trie.child.keySet());
                Collections.sort(list);

                for (String s : list) {
                    for (int i = 0; i < depth; i++) {
                        System.out.print(" "); // 댑스 만큼 띄운다.
                    }
                    System.out.println(s);
                    print(trie.child.get(s), depth + 1); // 댑스 1 씩 추가하여 하위 폴더로 췍
                }
            }
        }
    }
}
