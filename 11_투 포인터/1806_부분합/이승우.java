
//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 28208 KB , 시간 : 296 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), S = Integer.parseInt(st.nextToken());
        int[] list = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int n = 0; n < N; n++) {
            list[n] = Integer.parseInt(st.nextToken());
        }
        int sum = 0, len = Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();

        for (int l : list) {
            // 연속된 수열을 넣기
            sum += l;
            queue.offer(l);

            while (sum >= S) { // 초과될 경우 연속된 수열을 찾는 것이기 때문에 먼저 넣은 것부터 차례로 빼서 S보다 작아질 때까지 빼서 최소 길이를 찾는다.
                len = Math.min(len, queue.size());
                sum -= queue.poll();
            }
        }

        System.out.println(len == Integer.MAX_VALUE ? 0 : len); // int 최대값일 경우 한번도 S 이상인적이 없기 때문에 합을 만드는 것이 불가능

    }
}
