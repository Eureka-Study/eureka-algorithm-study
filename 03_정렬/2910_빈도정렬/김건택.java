// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14516 KB , 시간 : 116 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> Map = new HashMap<>(); // 들어온 숫자들의 빈도를 저장할 map
        Map<Integer, Integer> incomeMap = new HashMap<>(); // 숫자의 빈도가 같을 때 먼저 들어온 숫자를 비교하기 위한 map

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            if (!incomeMap.containsKey(n)) { // 처음 들어온 숫자의 들어올때의 순서 저장
                incomeMap.put(n, i);
            }

            Map.put(n, Map.getOrDefault(n, 0) + 1); // 들어온 숫자의 빈도 저장
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(Map.entrySet()); // 빈도가 저장된 map을 list로 변환

        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(java.util.Map.Entry<Integer, Integer> o1, java.util.Map.Entry<Integer, Integer> o2) {
                if (o1.getValue() == o2.getValue()) { // 빈도가 같다면 먼저 들어온 것이 앞으로
                    return incomeMap.get(o1.getKey()) - incomeMap.get(o2.getKey());
                }
                return o2.getValue() - o1.getValue(); // 빈도가 큰 것이 앞으로
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : list) {
            int key = entry.getKey();
            int value = entry.getValue();

            for (int i = 0; i < value; i++) {
                sb.append(key).append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}
