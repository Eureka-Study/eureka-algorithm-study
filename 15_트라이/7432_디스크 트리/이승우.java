
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 27588 KB , 시간 : 260 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 디스크트리 {
    static class TriNode {
        Map<String, TriNode> tri = new HashMap<>();
    }

    static TriNode root = new TriNode();

    static void insert(String[] strings) { // 넣기
        TriNode node = root;

        for (String s : strings) {
            node.tri.putIfAbsent(s, new TriNode()); // 폴더 생성 이미 존재한다면 무시
            node = node.tri.get(s);// 폴더 안에 진입
        }
    }

    static StringBuilder sb = new StringBuilder();

    static void print(TriNode tri, String space) { // 찾기
        TriNode node = tri;

        List<String> list = new ArrayList<>(node.tri.keySet()); // 정렬을 위해 list 생성

        list.sort(null); // 사전 순으로 정렬

        StringBuilder newSpace = new StringBuilder(space); // 현 폴더 위치
        newSpace.append(" "); // 다음 폴더 위치

        for (String s : list) {
            sb.append(space)
                    .append(s)
                    .append("\n");// 해당 폴더가 있음을 확인
            print(node.tri.get(s), newSpace.toString()); // 현 폴더에서 그 안에 들어가 다른 폴더가 있는지 확인
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 1; n <= N; n++) {
            String[] strings = br.readLine().split("\\\\");// \로 잘라 경로 파악

            insert(strings); // 폴더 넣기
        }
        print(root, ""); // 폴더 확인

        System.out.print(sb); // 폴더 출력
    }
}