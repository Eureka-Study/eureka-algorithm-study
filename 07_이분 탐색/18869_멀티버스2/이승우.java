//언어 : JAVA , (성공/실패) : 2/0, 메모리 : 237460 KB , 시간 : 1452 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 멀티버스2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());

        Map<List<Integer>, Integer> map = new HashMap<>();
        int[][] space = new int[M][N];

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int n = 0; n < N; n++){
                space[m][n] = Integer.parseInt(st.nextToken());
            }
        }

        for(int m = 0; m < M; m++){
            List<Integer> universe_size = universeSize(space[m]); //행성이 어떻게 생겼는지 확인
            map.put(universe_size, map.getOrDefault(universe_size, 0) + 1); // 같은 순서로 되어있는게 있는지 확인
        }

        int count = 0;
        for(int m : map.values()){
            count += (m * (m - 1)) / 2; // 쌍의 개수 구하기
        }

        System.out.println(count);
    }

    static List<Integer> universeSize(int[] planet){
        int[] space = planet.clone(); //2개가 필요하므로 복제
        Arrays.sort(space); //오름차순으로 정렬
        Map<Integer, Integer> map = new HashMap<>();

        int count = 0;
        for(int s : space){ // 정렬된 순서대로 중복은 제거하여 map에 담기(크기 분류를 위해)
            if(!map.containsKey(s)) map.put(s, count++);
        }

        List<Integer> answer = new ArrayList<>();

        for(int p : planet){
            answer.add(map.get(p)); // map에서 빼내기
        }

        return answer;
    }
}
