
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 94592 KB , 시간 : 752 ms
import java.io.*;
import java.util.*;

public class 게임닉네임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Set<String> forbidden = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        StringBuilder ans = new StringBuilder();
        for (int n = 0; n < N; n++) {
            String name = br.readLine().trim();

            int c = map.getOrDefault(name, 0) + 1;
            map.put(name, c);

            String alias = null;

            for (int len = 1; len <= name.length(); len++) {
                String p = name.substring(0, len);
                if (!forbidden.contains(p)) {
                    alias = p;
                    break;
                }
            }

            if (alias == null) {
                if (c == 1) {
                    alias = name;
                } else {
                    alias = name + c;
                }
            }

            ans.append(alias).append('\n');

            for (int len = 1; len <= name.length(); len++) {
                forbidden.add(name.substring(0, len));
            }
        }

        System.out.print(ans);
    }
}