//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 300624 KB , 시간 : 684 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 접두사찾기 {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd; 
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word){
            TrieNode node = root;
            /**
             * 꼬리에 꼬리를 무는 과정
             */
            for(char c : word.toCharArray()){
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.isEnd = true;
        }

        boolean startWith(String prefix){
            TrieNode node = root;

            /**
             * 꼬리에 물린 것을 확인하는 작업
             */
            for(char c : prefix.toCharArray()){
                if(!node.children.containsKey(c)) return false; // 중간에 일치하지않으면 접두사가 아님
                node = node.children.get(c);
            }

            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        for(int i = 0; i < N; i++){
            trie.insert(br.readLine()); // 단어 집어 넣기
        }

        int count = 0;
        for(int i = 0; i < M; i++){
           if(trie.startWith(br.readLine())) count++; // 접두사임을 확인
        }
        System.out.println(count);
    }
}
