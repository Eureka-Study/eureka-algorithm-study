// 언어 : JAVA , (성공/실패) : 1/6 , 메모리 : 32304KB , 시간 : 328ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Card {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<Long, Integer> cards = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            long card = Long.parseLong(br.readLine());
            cards.put(card, cards.getOrDefault(card, 0) + 1);
        }

        long maxCard = -1;
        int maxCount = 0;

        /**
         * 정렬된 카드이기 때문에 이전 횟수보다 많으면 무조건 최소 크기의 최대값
         */
        for (Map.Entry<Long, Integer> entry : cards.entrySet()) {
            long key = entry.getKey();
            int value = entry.getValue();

            if (value > maxCount) {
                maxCard = key;
                maxCount = value;
            }
        }

        System.out.println(maxCard);
    }
}
