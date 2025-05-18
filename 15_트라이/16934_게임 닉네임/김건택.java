package boj.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Gold3_16934_trie {
    static Map<String, Integer> nicknameCount = new HashMap<>();
    static StringBuilder sb = new StringBuilder();


    static class Node {
        Map<Character, Node> children = new HashMap<>();

    }

    static class Trie {
        Node root = new Node();

        void insertAndPrint(String nickname) {
            // 같은 닉네임이 있다면 닉네임 + 개수
            if (nicknameCount.containsKey(nickname)) {
                nicknameCount.put(nickname, nicknameCount.get(nickname) + 1);
                sb.append(nickname).append(nicknameCount.get(nickname)).append("\n");
                return;
            } else {
                nicknameCount.put(nickname, 1);
            }

            Node node = root;

            StringBuilder temp = new StringBuilder();
            boolean alias = false; // 별칭 지정 완료 여부

            for (char c : nickname.toCharArray()) {
                temp.append(c);

                if (!node.children.containsKey(c)) {
                    if (!alias) { // node 에 c 가 없고 별칭 지정이 완료 되지 않았다면 현재까지를 별칭으로 지정
                        sb.append(temp).append("\n");
                        alias = true;
                    }
                    node.children.put(c, new Node());
                }
                node = node.children.get(c);
            }

            // 트라이에 저장이 다 되었는데도 별칭 지정이 완료되지 않았다면
            // 닉네일 전체를 별칭으로
            if (!alias) {
                sb.append(temp).append("\n");
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            trie.insertAndPrint(br.readLine());
        }

        System.out.println(sb.toString());

        br.close();
    }
}
