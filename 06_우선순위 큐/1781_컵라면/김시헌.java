// 언어 : JAVA , (성공/실패) : 0/1 , 메모리 :  KB , 시간 :  ms

import java.io.*;
import java.util.*;

public class Main {    // 컵라면
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> info = new HashMap<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (info.containsKey(deadline)) {
                if (info.get(deadline) < num) info.put(deadline, num);
            } else {
                info.put(deadline, num);
            }
        }

        int ans = 0;

        for (int key : info.keySet()) {
            ans += info.get(key);
        }

        System.out.println(ans);
    }
}
