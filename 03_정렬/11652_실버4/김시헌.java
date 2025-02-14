// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 39088 KB , 시간 : 436 ms

import java.io.*;
import java.util.*;

public class Silver_11652 {     // 카드
    static int N;
    static HashMap<Long, Integer> cards;  // 문제조건 보니, 카드 숫자범위가 -2^62 ~ 2^62 임. int 범위 초과하므로 Long으로 해보자. (Long은 64비트)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new HashMap<>() ;

        for (int i = 0; i < N; i++) {
            long cardNum = Long.parseLong(br.readLine());
            if (cards.containsKey(cardNum)) {
                int cardValue = cards.get(cardNum);
                cards.put(cardNum, cardValue + 1);
            } else {
                cards.put(cardNum, 1);
            }
        }

        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(cards.entrySet());
        entryList.sort( (e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()) );    // 카드의 빈도수로 내림차순 정렬

        ArrayList<Long> candidates = new ArrayList<>();     // 최빈수 value를 가진 카드들의 키값만 담기
        int value = entryList.get(0).getValue();
        for (int i = 0; i < entryList.size(); i++) {
            if (entryList.get(i).getValue() != value) break;
            candidates.add(entryList.get(i).getKey());
        }

        Collections.sort(candidates);   // 오름차순 정렬

        System.out.println(candidates.get(0));

    }
}
