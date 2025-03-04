// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 40192KB , 시간 : 312ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 이승우 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        Map<Integer, Integer> map = new HashMap<>();
        int[] num = new int[N];

        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
            map.put(num[i], map.getOrDefault(num[i], 0) + 1); // 초기값이 없으면 0 있으면 그 값을 반환
        }

        List<Integer> keySet = new ArrayList<>(map.keySet()); // map은 정렬이 안되기 때문에 list로 변환

        Collections.sort(keySet, (m1, m2) -> { // 정렬
            if(map.get(m1) == map.get(m2)){ // 빈도 수가 같을 경우 나온 순서대로
                return Arrays.toString(num).indexOf(Integer.toString(m1)) - Arrays.toString(num).indexOf(Integer.toString(m2));
            }
            return map.get(m2).compareTo(map.get(m1)); // 빈도 수가 많은 것이 먼저
        });

        StringBuilder sb = new StringBuilder(N * 2);

        for(int i : keySet){
            for(int j = 0; j < map.get(i); j++){
                sb.append(i)
                  .append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }
}