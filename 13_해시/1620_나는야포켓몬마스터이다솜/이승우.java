//언어 : JAVA , (성공/실패) : 1/0, 메모리 : 54812 KB , 시간 : 464 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 나는야포켓몬마스터이다솜{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        Map<String, String> map = new HashMap<>();
        
        for(int n = 1; n <= N; n++){
            String pokemon = br.readLine();
            map.put(Integer.toString(n), pokemon); // key가 번호인 것
            map.put(pokemon, Integer.toString(n)); // key가 포켓몬 명인 것
        }

        StringBuilder sb = new StringBuilder();

        for(int m = 0; m < M; m++){
            sb.append(map.get(br.readLine())) // 무엇을 원하는지 모르지만 map에 다 넣어 원하는 것이 출력되게 설정
              .append("\n");
        }

        System.out.print(sb);
    }
}