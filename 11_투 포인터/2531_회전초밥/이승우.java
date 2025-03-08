// 언어 : JAVA , (성공/실패) : 1/4 , 메모리 : 18552KB , 시간 : 160ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이승우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken())
           ,k = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());

        int[] belt = new int[N]; // 벨트에 들어갈 초밥
        int[] sushi = new int[d + 1]; // 초밥 종류

        for(int i = 0; i < N; i++){
            belt[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;

        for(int i = 0; i < k; i++){
            if(sushi[belt[i]]++ == 0) max++; // 초밥 종류 추가 초기 값이 0이면 새로운 종류이므로 max 증가
        }

        // 쿠폰 초밥은 무조건 먹을 수 있으므로 미리 추가( 없을 경우 max를 증가시키고 쿠폰 초밥이 0 되는걸 방지하고자 이미 있더라도 + 1 )
        if(sushi[c]++ == 0) max++;

        int maxCombi = max; // 처음 조합이 일단 최대값이므로 max로 초기화

        for(int i = 0; i < N; i++){
            if(--sushi[belt[i]] == 0) max--; // 벨트에서 빠짐 다만 0이 되어야 완전히 빠진 것아므로 0일 때만 max 감소

            if(++sushi[belt[(i + k) % N]] == 1) max++; // 벨트에 추가 다만 추가 당시 새로운 종류여야 가짓수가 증가된 것이므로 추가하고 1일 경우만 max 증가

            maxCombi = Math.max(maxCombi, max); // 최대값 갱신
        }

        System.out.println(maxCombi);
    }
}