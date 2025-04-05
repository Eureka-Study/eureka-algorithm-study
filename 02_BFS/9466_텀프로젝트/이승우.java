//언어 : JAVA , (성공/실패) : 2/7 , 메모리 : 307916 KB , 시간 : 1124 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 텀프로젝트 {
    /*

    이 부분이 왜 틀렸는지 모르겠다.

    사이클을 인식하지못하여서 라고하는데
    그 부분을 인지하고 코드를 작성한거인데...
    이 것 때문에 실패횟수가 상당히 많다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] list = new int[N + 1];
            Queue<Integer> queue = new LinkedList<>();
            boolean[] isSelected = new boolean[N + 1];
            int count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int n = 1; n <= N; n++){
                list[n] = Integer.parseInt(st.nextToken());
            }

            for(int n = 1; n <= N; n++){
                if(isSelected[n]) continue;
                queue.offer(list[n]);
                List<Integer> match = new ArrayList<>();

                while (!queue.isEmpty()) {
                    int newPoint = queue.poll();
                    match.add(newPoint);
                    isSelected[newPoint] = true;
                    int nextPoint = list[newPoint];

                    if(!isSelected[nextPoint]) queue.offer(nextPoint);
                }

                if(!match.contains(n)){
                    for(int m : match){
                        isSelected[m] = false;
                    }

                    isSelected[n] = true;
                    count++;
                }
            }

            sb.append(count)
              .append("\n");
        }
        System.out.print(sb);
    }
    */
    static int[] list;
    static boolean[] isSelected;
    static boolean[] isCycle;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine());
            list = new int[N + 1];
            isSelected = new boolean[N + 1]; // 돌았는지 확인
            isCycle = new boolean[N + 1]; //사이클로 형성이 되었는지
            count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int n = 1; n <= N; n++){
                list[n] = Integer.parseInt(st.nextToken());
            }

            for(int n = 1; n <= N; n++){
                if(!isSelected[n]) BFS(n); // 안 돈것만 돌린다.
            }

            sb.append(N - count)
              .append("\n");
        }
        System.out.print(sb);
    }

    static void BFS(int now){
        isSelected[now] = true;

        int next = list[now];

        if(!isSelected[next]){ // 다음 부분이 돌아져있는지 확인
            BFS(next); // 다음 부분 확인
        }else if(!isCycle[next]){ // 사이클이 이미 형성되어있지않다면
            int temp = next;
            while (temp != now){ // 현 지점까지 한 사이클 돌기
                count++;
                temp = list[temp];
            }
            count++; //마지막은 추가를 안했으므로 + 1 추가
        }
        isCycle[now] = true; // 사이클을 돌았음을 체크
    }
}
