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

    static void insert(String[] strings) {
        TriNode node = root;

        for (String s : strings) {
            node.tri.putIfAbsent(s, new TriNode());
            node = node.tri.get(s);
        }
    }

    static StringBuilder sb = new StringBuilder();

    static void print(TriNode tri, String space) {
        TriNode node = tri;

        List<String> list = new ArrayList<>(node.tri.keySet());

        list.sort(null);

        StringBuilder newSpace = new StringBuilder(space);
        newSpace.append(" ");

        for (String s : list) {
            sb.append(space)
                    .append(s)
                    .append("\n");
            print(node.tri.get(s), newSpace.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 1; n <= N; n++) {
            String[] strings = br.readLine().split("\\\\");

            insert(strings);
        }
        print(root, "");

        System.out.print(sb);
    }
}