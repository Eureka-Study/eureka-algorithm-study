
// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 15928KB , 시간 : 220ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 세용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

        long bestSum = Long.MAX_VALUE;
        long x = 0, y = 0, z = 0;

        for (int i = 0; i < N - 2; i++) {
            int l = i + 1, r = N - 1;
            while (l < r) {
                long sum = (long) list[i] + list[l] + list[r]; // 10억까지이므로 int의 최대값을 넘어갈 가능성이 조금이라도 있기에 long으로 변환
                if (Math.abs(sum) < Math.abs(bestSum)) { // -10억 ~ 10억인데 0에 가까운 것이 가장 좋은것이므로 절대값으로 계산
                    bestSum = sum;
                    x = list[i];
                    y = list[l];
                    z = list[r];
                }
                if (sum > 0) {// 0보다 크면 작아져야 0에 가까워지므로 r이 작아져야함
                    r--;
                } else if (sum < 0) { // 0보다 작으면 커져야 0에 가까워지므로 l이 커져야함
                    l++;
                } else { // 0이면 같은 값이 존재할 수 있지만 0에 더 가까운게 존재할 수 없다 그리고 아무거나 출력해도 된다고 되어있기에 즉시 출력하고 끝낸다.
                    System.out.printf("%d %d %d\n", x, y, z);
                    return;
                }
            }
        }

        System.out.printf("%d %d %d\n", x, y, z);
    }
}