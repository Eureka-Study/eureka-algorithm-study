// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 19404 KB , 시간 : 184 ms

import java.io.*;
import java.util.*;

public class Main {  // 회전 초밥
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken())
                , k = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());

        int[] sushiList = new int[N];
        for (int i = 0; i < N; i++) {
            sushiList[i] = Integer.parseInt(br.readLine());
        }

        HashMap<Integer, Integer> map = new HashMap<>();    // 초밥번호, 갯수
        map.put(c, 1);  // 일단 쿠폰초밥 먼저 1개 넣는다
        for (int i = 0; i < k; i++) {   // 일단 0번 인덱스부터 k-1번까지 한 사이클 돌면서 넣기
            if (map.containsKey(sushiList[i])) {    // map에 이미 i번초밥이 있으면
                map.put(sushiList[i], map.get(sushiList[i]) + 1);   // count 1 더하기
            } else {
                map.put(sushiList[i], 1);   // 없으면 i번초밥 count 1개로 넣기
            }
        }
        int max = map.size();   // 현재 map의 크기 -> 먹을 수 있는 초밥 가짓수

        int startIdx = 0;
        int endIdx = 0;
        for (int i = 1; i < N; i++) {   // 1번 인덱스부터 N-1번 인덱스까지 앞칸(i-1)은 빼고 뒷칸(i+k-1)은 넣는다
            startIdx = i % N;   // 이번 사이클 시작 인덱스
            endIdx = (i + k - 1) % N;   // 이번 사이클 끝 인덱스
            if (map.get(sushiList[startIdx-1]) > 1) {
                map.put(sushiList[startIdx-1], map.get(sushiList[startIdx-1]) - 1);
            } else {
                map.remove(sushiList[startIdx-1]);
            }

            if (map.containsKey(sushiList[endIdx])) {
                map.put(sushiList[endIdx], map.get(sushiList[endIdx]) + 1);
            } else {
                map.put(sushiList[endIdx], 1);
            }

            if (map.size() > max) max = map.size();

            // max값이 이미 초밥가짓수인 d 이상이거나, 연속먹기수 k에 쿠폰초밥 1개를 더한 k+1 이상이면 break
            if (max >= d) break;
            if (max >= k + 1) break;
        }

        System.out.println(max);
    }
}
