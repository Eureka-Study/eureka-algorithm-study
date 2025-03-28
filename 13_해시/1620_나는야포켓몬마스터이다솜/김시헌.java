// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 97040 KB , 시간 : 632 ms

import java.io.*;
import java.util.*;

public class Main {  // 나는야 포켓몬 마스터 이다솜
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> keyInteger = new HashMap<>();
        HashMap<String, Integer> keyString = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            keyInteger.put(i, line);    // 번호 : 포켓몬이름
            keyString.put(line, i);     // 포켓몬이름 : 번호
        }

        for (int i = 0; i < M; i++) {
            String str = br.readLine(); // 문제 읽어옴
            try {
                int key = Integer.parseInt(str);    // 일단 읽어온 문제를 int로 변환해보고
                String ans1 = keyInteger.get(key);  // 되면 그 값을 key로 포켓몬이름 찾아옴
                bw.write(ans1);
                bw.newLine();

            } catch (Exception e) {
                int ans2 = keyString.get(str);      // 안되면 그냥 str을 key로 포켓몬 번호 찾아옴
                bw.write(String.valueOf(ans2));
                bw.newLine();
            }
        }

        bw.close();
        br.close();
    }
}
