// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16380 KB , 시간 : 156 ms


import java.io.*;
import java.util.*;

public class 빈도정렬 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] a = new int[1001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> map.get(o2) - map.get(o1));

        for (int key : list) {
            for (int i = 0; i < map.get(key); i++) {
                System.out.print(key + " ");
            }
        }
    }
}
