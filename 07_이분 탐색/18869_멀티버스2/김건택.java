// 언어 : JAVA , (성공/실패) : 0/3 , 메모리 :  KB , 시간 :  ms

import java.io.*;
import java.util.*;

// 우주별 순위 구조가 같으면 두 우주는 균등하다는 가정 하의 풀이
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][][] multiverse = new int[M][N][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                multiverse[i][j] = new int[]{Integer.parseInt(st.nextToken()), j}; // 행성의 크기, 들어온 순서( 원래 index 저장)
            }
        }

        for (int i = 0; i < M; i++) {
            Arrays.sort(multiverse[i], (o1, o2) -> o1[0] - o2[0]); // 행성 크기 순으로 정렬
        }

        // 행성 크기 대신에 순위로 저장
        for (int i = 0; i < M; i++) {
            int rank = 1;
            int temp = multiverse[i][0][0];
            multiverse[i][0][0] = 1;
            for (int j = 1; j < N; j++) {
                if (multiverse[i][j][0] == temp) { // 앞의 행성 크기와 같다면 같은 순위
                    multiverse[i][j][0] = rank;
                    continue;
                }
                temp = multiverse[i][j][0];
                multiverse[i][j][0] = ++rank; // 앞의 행성 크기와 다르다면 rank를 하나 증가시킨 후 저장
            }
        }

        // 행성 크기 별로 정렬된 배열을 원래 index 자리로 되돌리는 연산
        int[][] newMultiverse = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                newMultiverse[i][multiverse[i][j][1]] = multiverse[i][j][0];
            }
        }

        // 실패 ( 쌍을 찾는 것이 아니라 중복 횟수를 구함 )
//        int count = 0;
//        Set<String> set = new HashSet<>();
//        for (int i = 0; i < M; i++) {
//            String temp = Arrays.toString(newMultiverse[i]);
//            if (set.contains(temp)) {
//                count++;
//                continue;
//            }
//            set.add(temp);
//        }

        // 실패 ( Arrays.toString()이 시간이 오래 걸림 )
//        Map<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < M; i++) {
//            String temp = Arrays.toString(newMultiverse[i]);
//            map.put(temp, map.getOrDefault(temp, 0) + 1);
//        }
//
//        int count = 0;
//        for (int val : map.values()) {
//            if (val > 1) {
//                count += val * (val - 1) / 2;
//            }
//        }

        // 시간 초과 실패 ( 행성 크기 랭크를 계산하는데 시간이 오래 걸리는 것으로 추정.. )
        Map<List<Integer>, Integer> map = new HashMap<>();

        for (int i = 0; i < M; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                list.add(newMultiverse[i][j]);
            }
            map.put(list, map.getOrDefault(list, 0) + 1);
        }

        int count = 0;
        for (int val : map.values()) {
            count += val * (val - 1) / 2;
        }

        System.out.println(count);
    }
}