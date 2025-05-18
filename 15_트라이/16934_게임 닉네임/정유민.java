//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 103396 KB , 시간 : 424 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 게임닉네임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String alias = trie.insertAndAlias(br.readLine());
            sb.append(alias+"\n");
        }

        System.out.println(sb);
    }

    static class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        //문자 한개가 노드임
        boolean isEnd;
        int times = 0;
    }

    static class Trie {
        TrieNode root = new TrieNode();


        String insertAndAlias(String word) {

            StringBuilder sb = new StringBuilder();
            int aliasIdx = 11; //길이 제한 시키는 용도

            TrieNode thisNode = root; // 현재 노드
            for (int i = 0; i < word.length(); i++) {
                char n = word.charAt(i);

                if(thisNode.childNodes.get(n) == null) {
                    thisNode.childNodes.put(n, new TrieNode());//없으면 자식넣음
                    aliasIdx = Math.min(aliasIdx, i);
                }

                thisNode = thisNode.childNodes.get(n); // 현재 노드 업뎃
            }

            if (thisNode.isEnd || !thisNode.childNodes.isEmpty()) {
                aliasIdx = word.length()-1;
            }
            sb.append(word.substring(0,aliasIdx+1));
            thisNode.isEnd = true; //마지막 문자 true
            thisNode.times++;

            //이전에 나온 적 있는 문자면 times 까지 넣어줘야 함
            if (thisNode.times!=1){
                sb.append(thisNode.times);
            }

            return sb.toString();
        }
    }
}
