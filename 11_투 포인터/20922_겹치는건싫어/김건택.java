import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 실패 코드 ( 수를 지나쳐서 실패 )
//        int maxLen = 0;
//        int length = 0;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int n : arr) {
//            if (map.getOrDefault(n, 0) >= K) { // 해당 원소가 k개 보다 많아지면
//                maxLen = Math.max(maxLen, length); // 이때까지 수열의 길이 length 와 maxLen 을 비교 할당
//                length = 0; // 새로운 수열 시작
//                map = new HashMap<>(); // map 초기화
//            }
//            map.put(n, map.getOrDefault(n, 0) + 1); // 수열 추가
//            length++;
//        }
//        maxLen = Math.max(maxLen, length);
//
//        System.out.println(maxLen);

        // 실패 코드 ( 시간 초과 )
//        int maxLen = 0;
//        int length;
//
//        for (int i = 0; i < N; i++) {
//            length = 0;
//            Map<Integer, Integer> map = new HashMap<>();
//            for (int j = i; j < N; j++) {
//                int n = arr[j];
//                if (map.getOrDefault(n, 0) >= K) {
//                    maxLen = Math.max(maxLen, length);
//                    break;
//                }
//                map.put(n, map.getOrDefault(n, 0) + 1);
//                length++;
//            }
//            maxLen = Math.max(maxLen, length);
//        }
//        System.out.println(maxLen);

        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < N; right++) {
            int num = arr[right];
            map.put(num, map.getOrDefault(num, 0) + 1);

            while (map.get(num) > K) { // 조건을 초과하면 left 이동
                map.put(arr[left], map.get(arr[left]) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        System.out.println(maxLen);
    }
}
//                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//                    System.out.println(entry.getKey() + " " + entry.getValue());
//                }
//                System.out.println("------------------------------");