// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 108096 KB , 시간 : 668 ms

import java.io.*;
import java.util.*;

public class Main {   // 게임 닉네임
    static Set<String> prefixSet = new HashSet<>();
    static Map<String, Integer> nickMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            if (nickMap.containsKey(input)) {
                nickMap.replace(input, nickMap.get(input) + 1);     // 해당 nickname 이미 등록되었으면 등록횟수 더하기
            } else {
                nickMap.put(input, 1);  // 첫등록이면 map에 등록
            }

            String prefix = splitAndCheck(input);
            sb.append(prefix).append("\n");
        }

        System.out.println(sb);
    }

    static String splitAndCheck(String nickname) {
        String temp = "";
        boolean isFirst = true;
        String ans = "";

        for (int i = 0; i < nickname.length(); i++) {
            char chr = nickname.charAt(i);
            temp += chr;

            if (prefixSet.contains(temp)) continue;     // set에 temp 있다면 넘어가

            prefixSet.add(temp);

            if (isFirst) {      // 처음으로 made된 temp면 이게 답, set에 temp 넣기
                ans = temp;

                isFirst = false;

            }
        }

        if (isFirst) {      // 문자열 다 돌았는데 아직도 made 안되었으면
            int num = nickMap.get(nickname);    // nickname 등록 횟수

            ans = (num == 1) ? nickname : nickname + num;
        }

        return ans;
    }
}
