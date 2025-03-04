// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 33480 KB , 시간 : 308 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            long card = Long.parseLong(br.readLine());

            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        long max = 0;
        long card = 0;

        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                card = entry.getKey();
            } else if (entry.getValue() == max) {
                if (entry.getKey() < card) {
                    card = entry.getKey();
                }
            }
        }

        System.out.println(card);
    }
}