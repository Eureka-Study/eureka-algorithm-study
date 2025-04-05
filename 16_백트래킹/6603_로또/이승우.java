//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14344 KB , 시간 : 104 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로또 {
    static boolean[] isSelect;
    static int[] lotto;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());

            if(N == 0)break; // 0 입력 시 종료

            lotto = new int[N];
            isSelect = new boolean[N];

            for(int n = 0; n < N; n++){
                lotto[n] = Integer.parseInt(st.nextToken());
            }

            pick(0, 0); // 조합 찾기 시작
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void pick(int i, int c){
        if(c == 6){ // 6개를 꺼낼 경우 끝내기
            for(int j = 0; j < N; j++){
                if(isSelect[j]) sb.append(lotto[j]).append(" "); //선택한 공만 출력
            }
            sb.append("\n");
            return;
        }
        for(int j = i; j < N; j++){
            isSelect[j] = true; //선택
            pick(j + 1, c + 1); // 이 공을 선택한 다음의 경우의 수 보기
            isSelect[j] = false; // 이 공을 선택한 경우를 모두 탐색했으니 이 공을 선택안했을 때의 경우의 수 모두 탐색
        }
    }
}
