// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 85084 KB , 시간 : 700 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class 작업 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N + 1];
        ArrayList<Integer>[] list = new ArrayList[N + 1];
        int[] greed = new int[N + 1];
        int[] deep = new int[N + 1];

        for(int i = 1; i <= N; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            time[i] = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            greed[i] = count;
            for(int c = 1; c <= count; c++){
                list[Integer.parseInt(st.nextToken())].add(i);
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 1; i <= N; i++){
            if(greed[i] == 0){
                deque.offer(i);
                deep[i] = time[i];
            }
        }

        while(!deque.isEmpty()){
            int work = deque.pollFirst();

            for(int next : list[work]){
                deep[next] = Math.max(deep[next], deep[work] + time[next]);
                if(--greed[next] == 0){
                    deque.offer(next);
                }
            }
        }

        int answer = -1;

        for(int d : deep){
            answer = Math.max(answer, d);
        }

        System.out.println(answer);
    }
}
