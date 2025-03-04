// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 35516 KB , 시간 : 792 ms


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            //x가 자연수 일 때
            if (x > 0) {
                queue.offer(x);
                continue;
            }


            //x가 0 일때


            //근데 만약 배열 비어있으면
            if (queue.isEmpty()) System.out.println(0);
            else System.out.println(queue.poll());
        }
    }
}
