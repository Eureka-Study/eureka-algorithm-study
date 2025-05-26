
// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 52524 KB , 시간 : 596 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공주님의정원 {
    static class Flower {
        int start, end;

        Flower(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Flower[] flowers = new Flower[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken()); // 꽃이 피기 시작하는 달
            int sd = Integer.parseInt(st.nextToken()); // 꽃이 피기 시작하는 날
            int em = Integer.parseInt(st.nextToken()); // 꽃이 지는 달
            int ed = Integer.parseInt(st.nextToken()); // 꽃이 지는 달
            // 예시 0320 3월 20일 처럼 날짜를 계산하기 편하게 하기위해
            int start = sm * 100 + sd;
            int end = em * 100 + ed;
            flowers[i] = new Flower(start, end);
        }

        Arrays.sort(flowers, (a, b) -> {
            return a.start != b.start ? a.start - b.start : b.end - a.end; // 꽃이 빨리 피는 순으로(단 같이 필 경우 늦게 지는 꾳을 앞에 둔다)
        });

        int current = 301; // 3월 1일
        int idx = 0;
        int count = 0;

        while (current <= 1130) { // 11월 30일까지
            int farthest = current;

            while (idx < N && flowers[idx].start <= current) { // 꽃이 피어야하는 시기보다 꽃이 피기 시작하는 시기가 같거나 빠른지 확인
                farthest = Math.max(farthest, flowers[idx].end);// 그 중 가장 긴 시기를 선택(긴 것을 선택해야 2번에 충족하기 쉽다.)
                idx++;
            }

            if (farthest == current) { // 같다면 중간에 꽃이 비는 시기가 있다는 의미이므로 조건에 만족할 수 없다.
                System.out.println(0);
                return;
            }
            count++;
            current = farthest; // 이 꽃이 지는 순간 1번에 충족할 수 없으므로 이 꽃이 지기 직전까지 피는 꽃이 있는지 탐색
        }

        System.out.println(count);
    }
}