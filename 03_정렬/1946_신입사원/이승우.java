// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 317236KB , 시간 : 2936ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NewPeople {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        int[] maxMember = new int[testCase];

        for(int i = 0; i < testCase; i++){
            int people = Integer.parseInt(br.readLine());
            int[][] chart = new int[people][2];
            for(int j = 0; j < people; j++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                chart[j][0] = Integer.parseInt(st.nextToken());
                chart[j][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(chart, (a, b) -> Integer.compare(a[0], b[0])); //서류를 기준으로 등수가 높은 순으로 정렬

            int count = 0;
            int max = 100001; // 최대 지원자 수가 100000 이기 때문에 + 1 하여 max값으로 지정

            for(int[] j : chart){
                /**
                 * 처음은 서류 1등이기 때문에 누가 들어와도 무조건 합격
                 * 그 이후로는 등수가 점점 낮아지기 때문에 이전에 합격한 사람보다 면접 등수가 높아야 합격할 수 있음
                 * max 수치보다 높을 경우는 서류로 못 이기는데 면접으로도 못이기기 때문에 탈락
                 */
                if (j[1] < max) {
                    count++; 
                    max = j[1];
                }
            }
            maxMember[i] = count;
        }
        
        for(int i : maxMember){
            System.out.println(i);
        }
        
    }
}
