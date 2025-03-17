// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14076 KB , 시간 : 100 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 이승우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        Map<String, String[]> group = new HashMap<>(); // 걸 그룹
        Map<String, String> Affiliation = new HashMap<>(); // 개인 소속 그룹
        
        for(int n = 0; n < N; n++){
            String name = br.readLine();
            int num = Integer.parseInt(br.readLine());

            String[] people = new String[num];

            for(int i = 0; i < num; i++){
                people[i] = br.readLine(); // 그룹 사람들 담기
                Affiliation.put(people[i], name); // 개인 소속 담기

            }

            group.put(name, people);
        }

        StringBuilder sb = new StringBuilder();

        for(int m = 0; m < M; m++){
            String name = br.readLine();
            int type = Integer.parseInt(br.readLine());

            if(type == 0){ // 소속되어있는 사람 이름 순으로 정렬하여 출력
                String[] member = group.get(name);
                Arrays.sort(member);

                for(String mem : member){
                    sb.append(mem)
                      .append("\n");
                }
            }else{ // 그 사람의 소속 출력
                sb.append(Affiliation.get(name))
                  .append("\n");
            }
        }

        System.out.print(sb);
    }
}
