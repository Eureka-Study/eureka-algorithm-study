//언어 : JAVA , (성공/실패) : 1/3 , 메모리 : 283464 KB , 시간 : 1508 ms
import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<int[]> lines = new ArrayList<>(); // 선분 정보를 저장
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines.add(new int[]{x, y});
        }

        // 시작점을 기준으로 정렬
        Collections.sort(lines, Comparator.comparingInt(a -> a[0]));

        int totalLength = 0;
        int start = lines.get(0)[0];
        int end = lines.get(0)[1];

        for (int i = 1; i < n; i++) {
            int[] line = lines.get(i);

            if (line[0] <= end) { // 현재 선분이 이전 선분과 겹치는 경우
                end = Math.max(end, line[1]); // 끝점을 갱신하여 병합
            } else { // 겹치지 않는 경우
                totalLength += (end - start); // 이전 선분 길이 추가
                start = line[0]; // 새로운 선분 시작점 설정
                end = line[1]; // 새로운 선분 끝점 설정
            }
        }
        totalLength += (end - start); // 마지막 선분 길이 추가

        System.out.print(totalLength);
    }
}