//언어 : JAVA , (성공/실패) : 1/3 , 메모리 : 57548 KB , 시간 : 524 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 겹치는건싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        int[] list = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int n = 0; n < N; n++){
            list[n] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();

        int maxLen = -1;
        for(int i : list){
            int count = map.getOrDefault(i, 0) + 1; // 해당 숫자의 개수

            if(count > K){ //같은 숫자가 K개 이하인지 체크
                maxLen = Math.max(maxLen, deque.size()); // 지금까지의 길이가 이전까지의 길이보다 긴지 체크
                int poll = 0;
                while(!deque.isEmpty() && poll != i){ // i가 K개가 된 것이므로 i가 나올 때까지 큐로 빼낸다.
                    poll = deque.poll();
                    map.put(poll, map.get(poll) - 1);
                }
                count = K; // Deque에 쌓여있는건 K - 1 개이지만 다음에 넣는게 i이므로 K와 동일해진다.
            }
            deque.offer(i);
            map.put(i, count);
        }
        maxLen = Math.max(maxLen, deque.size());//마지막으로 한번 더 최대 길이 체크
        System.out.println(maxLen);
    }
}
