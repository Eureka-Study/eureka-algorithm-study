// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 30704 KB , 시간 : 368 ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = 0;

        String[] S = new String[N]; // 문자열 개수만큼 배열 생성
        for (int i = 0; i < N; i++) {
            S[i] = br.readLine();
        }

        Arrays.sort(S); // 문자열을 정렬

        for (int i = 0; i < M; i++) {
            String prefix = br.readLine();
            if (binarySearch(S, prefix, 0, N-1)) { // 이진 탐색 진행
                answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean binarySearch(String[] S, String prefix, int low, int high) {
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (S[mid].startsWith(prefix)) {
                return true;
            } else if (S[mid].compareTo(prefix) < 0) {
                low = mid + 1;
            } else if (S[mid].compareTo(prefix) > 0) {
                high = mid - 1;
            }
        }
        return false;
    }
}