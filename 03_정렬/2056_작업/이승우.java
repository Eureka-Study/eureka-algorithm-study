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

            time[i] = Integer.parseInt(st.nextToken()); //작업시간
            int count = Integer.parseInt(st.nextToken()); // 선행 조건 개수
            greed[i] = count;
            for(int c = 1; c <= count; c++){ // 선행 조건
                list[Integer.parseInt(st.nextToken())].add(i);
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 1; i <= N; i++){
            if(greed[i] == 0){ // 선행 조건이 0이면 지금 당장 수행해도 됨
                deque.offer(i); // 작업 영역에 넣기
                deep[i] = time[i]; // 소요된 시간
            }
        }

        while(!deque.isEmpty()){
            int work = deque.pollFirst(); // 끝난 작업 빼기

            for(int next : list[work]){ // 이 작업이 선행 조건인 다음 작업 살펴보기
                deep[next] = Math.max(deep[next], deep[work] + time[next]); // 다음 작업이 이미 실행이 되었을 경우 이번 실행과 이전에 실행된 시간 중 가장 큰 값을 선출
                if(--greed[next] == 0){ // 선행 조건이 모두 사라졌으면 작업 영역에 넣기
                    deque.offer(next);
                }
            }
        }

        int answer = -1;

        for(int d : deep){
            answer = Math.max(answer, d); // deep 중 가장 큰 영역 선출
        }

        System.out.println(answer);
    }
}
