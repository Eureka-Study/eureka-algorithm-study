// 언어 : JAVA , (성공/실패) : 1/5 , 메모리 : 185300KB , 시간 : 1508ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Difference {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int nA = Integer.parseInt(st.nextToken()), nB = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < nA; i++){
            set.add(Integer.parseInt(st.nextToken())); // A를 저장
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < nB; i++){
            set.remove(Integer.parseInt(st.nextToken())); // A에도 B에도 있는 것 제거
        }

        // A에 넣는 순서가 반드시 오름차순이라는 보장이 없기 때문에 sort 배열을 추가
        int[] sort = new int[set.size()];
        int cnt = 0;

        for(int i : set){
            sort[cnt++] = i; // set에 있는 것을 배열에 담기
        }

        Arrays.sort(sort); //정렬

        sb.append(sort.length) //배열 크기 담기
          .append("\n");

        for(int i : sort){
            sb.append(i) // 정렬된 A 순서대로 담기
              .append(" ");
        }

        System.out.println(sb);
    }
}