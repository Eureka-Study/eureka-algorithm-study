// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 18432 KB , 시간 : 208 ms

import java.io.*;
import java.util.*;

public class Main {      // 빈도 정렬
    static int N, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        HashMap<Integer, Info> map = new HashMap<>();       // 숫자를 key로, Info(빈도, 순서)를 value로
        st = new StringTokenizer(br.readLine());
        int orderIdx = 1;
        while (st.hasMoreTokens()) {
            int token = Integer.parseInt(st.nextToken());
            if (map.containsKey(token)) {
                map.get(token).count++;
            } else {
                map.put(token, new Info(1, orderIdx));
            }
            orderIdx++;
        }

        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> {
            if (map.get(o1).count > map.get(o2).count) return -1;       // 빈도수 큰거로 내림차순
            else if (map.get(o1).count == map.get(o2).count) {          // 빈도수 같다면
                if (map.get(o1).order < map.get(o2).order) return -1;   // 들어온 순서값으로 오름차순
                else return 1;
            } else return 1;
        });

        for (Integer num : keySet) {
            int valCount = map.get(num).count;
            for (int i = 0; i < valCount; i++) {    // 해당 num의 빈도수(count) 만큼 숫자 출력
                bw.write(String.valueOf(num) + " ");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static class Info {
        int count, order;
        Info(int count, int order) {
            this.count = count; this.order = order;
        }
    }
}
