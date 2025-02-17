// 언어 : JAVA , (성공/실패) : 1/5 , 메모리 : 314192 KB , 시간 : 3008 ms
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        int T = sc.nextInt();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {

            int N = Integer.parseInt(br.readLine());

            int[][] scores = new int[N][2];

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                scores[j][0] = Integer.parseInt(st.nextToken()); // 서류 순위
                scores[j][1] = Integer.parseInt(st.nextToken()); // 면접 순위
            }

            Arrays.sort(scores, Comparator.comparingInt(a -> a[0]));

            int count = 1;
            int bestScore = scores[0][1];

            for (int i = 1; i < N; i++) {

                if (scores[i][1] < bestScore) {
                    bestScore = scores[i][1];
                    count++;
                }
            }

            System.out.println(count);

        }
    }
}