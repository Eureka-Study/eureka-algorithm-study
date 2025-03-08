//언어 : JAVA , (성공/실패) : 1/3 , 메모리 : 14940 KB , 시간 : 132 ms
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); // 메시지의 길이
        int c = Integer.parseInt(st.nextToken()); // 숫자는 모두 c보다 작거나 같음

        Map<Integer, Integer> freqMap = new HashMap<>(); // 숫자의 빈도수 저장
        List<Integer> order = new ArrayList<>(); // 등장 순서 저장

        st = new StringTokenizer(br.readLine());
        List<Integer> numbers = new ArrayList<>();

        // 입력 및 빈도 계산
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            numbers.add(num);
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            // 처음 등장한 숫자는 order 리스트에 추가
            if (!order.contains(num)) {
                order.add(num);
            }
        }

        // 빈도수 기준으로 내림차순, 빈도수가 같으면 먼저 등장한 순서대로 정렬
        numbers.sort((a, b) -> {
            int freqA = freqMap.get(a);
            int freqB = freqMap.get(b);

            if (freqA == freqB) {
                return order.indexOf(a) - order.indexOf(b); // 먼저 등장한 숫자가 앞에 오도록 정렬
            }
            return freqB - freqA; // 빈도수가 높은 숫자가 앞에 오도록 정렬
        });

        // 결과 출력
        for (int num : numbers) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString());
    }
}