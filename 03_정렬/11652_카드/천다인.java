//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 31924 KB , 시간 : 320 ms
import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Long, Integer> map = new HashMap<>();
        long mostFrequentNum = 0;
        int maxCount = 0;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            Long num = Long.parseLong(br.readLine());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            long key = entry.getKey();
            int value = entry.getValue();

            if (value > maxCount || (value == maxCount && key < mostFrequentNum)) {
                mostFrequentNum = key;
                maxCount = value;
            }
        }
        System.out.println(mostFrequentNum);
    }
}