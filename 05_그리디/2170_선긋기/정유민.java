// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 328260 KB , 시간 : 1376ms

import java.io.*;
import java.util.*;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        List<int[]> lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines.add(new int[]{x, y});
        }

        lines.sort((a, b) -> Integer.compare(a[0], b[0]));

        int totalLength = 0;
        int nowX = lines.get(0)[0];
        int nowY = lines.get(0)[1];

        for (int i = 1; i < n; i++) {
            int nextX = lines.get(i)[0];
            int nextY = lines.get(i)[1];

            if (nextX <= nowY) {  // 겹침
                nowY = Math.max(nowY, nextY);
            } else {  // 안 겹침
                totalLength += (nowY - nowX);
                nowX = nextX;
                nowY = nextY;
            }
        }
        totalLength += (nowY - nowX);

        System.out.println(totalLength);
    }
}
