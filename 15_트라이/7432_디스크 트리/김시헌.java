// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 29100 KB , 시간 : 220 ms

import java.io.*;
import java.util.*;

public class Main {    // 디스크 트리
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] texts = new String[N];
        for (int i = 0; i < N; i++) {
            texts[i] = br.readLine();
        }

        Node root = new Node();     // 루트노드 선언 (기본생성자로 선언)

        // 트리 생성 과정
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            Node parent = root;     // 부모를 루트로 설정

            st = new StringTokenizer(texts[i], "\\");
            while (st.hasMoreTokens()) {
                String dir = st.nextToken();
                if (!parent.children.containsKey(dir)) {    // 루트부터 타고내려오기 시작 -> 부모의 자식에 dir이 없다면
                    parent.children.put(dir, new Node(dir));    // 자식에 넣어주기
                }                                               // (부모의 자식에 dir이 있는경우, 아~ 있구나 하고 넘어가)
                parent = parent.children.get(dir);          // 부모를 dir로 설정해주기
            }
        }

        // 트리 완성되었으니, 오름차순 디렉토리구조 출력 과정 시작
        root.print(0);
        System.out.println(sb);
    }

    static class Node {
        String node;
        HashMap<String, Node> children = new HashMap<>();

        Node() {}   // 루트노드용 기본생성자
        Node(String node) {
            this.node = node;
        }

        public void print(int blank) {
            List<String> keySet = new ArrayList<>(children.keySet());
            Collections.sort(keySet);

            for (int i = 0; i < keySet.size(); i++) {
                String nodeName = keySet.get(i);
                for (int j = 0; j < blank; j++) {
                    sb.append(" ");     // blank 수만큼 공백 넣기
                }
                sb.append(nodeName).append("\n");    // 그 다음 노드이름 넣고 줄바꿈

                Node child = children.get(nodeName);    // 노드의 자식들

                int nextBlank = blank + 1;
                child.print(nextBlank);   // blank에 1 더해주고 재귀호출 (자식들로 들어가면서 sb에 담아주겠지)
            }
        }

    }
}
