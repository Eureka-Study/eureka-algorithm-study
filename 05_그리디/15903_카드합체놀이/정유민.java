import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15903_카드합체놀이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Long> cards = new PriorityQueue<>();
        //카드개수 n //카드 합체 횟수 m
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        //맨 처음 카드 상태인 n개의 자연수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards.offer(Long.parseLong(st.nextToken()));
        }

        //x y 에 써진 수 더한 값 계산 (x y 다름)
        //x y는 그 값으로 덮어씌어짐.
        //이걸 m 번 한다.
        while (m-- > 0) {
            Long x = cards.poll();
            Long y = cards.poll();
            Long temp = x+y;
            cards.offer(temp);
            cards.offer(temp);
        }
        long cnt = 0;
        while (!cards.isEmpty()) {
            cnt += cards.poll();
        }
        System.out.println(cnt);
        //다하고 카드에 쓰여있는 수를 모두 더한 것 출력.(점수를 가장 작게 만드는것이 목표)
    }
}
