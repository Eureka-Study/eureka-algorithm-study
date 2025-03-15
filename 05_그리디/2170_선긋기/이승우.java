// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 341960 KB , 시간 : 2796 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 선긋기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] line = new int[N][2];

        for(int n = 0; n < N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());

            line[n] = new int[] {x, y};
        }

        Arrays.sort(line, (n1, n2) -> n1[0] - n2[0]);

        int[] size = new int[] {-1000000001, -1000000001}; // -10억부터이므로 -10억 1로 설정


        int len = 0;

        for(int[] l : line){
            if(l[0] <= size[1]){ // 정렬을 했으므로 size[0]는 무조건 l[0]이하임 / l[0]가 size[1]이하라면 겹치는 부분이 존재 안의 구문 수행
                size = new int[]{size[0], Math.max(l[1], size[1])}; // size[0]는 가장 작은 최소값이지만 가장 큰 값은 결정되지않음
            }else{// size[1]보다 더 크다면 겹치는 부분이 없다는 의미
                len += size[1] - size[0]; //겹치는게 없으므로 지금까지 겹쳐서 그린 선의 길이를 구하여 저장
                size = l; // 지금의 선으로 저장하여 초기화
            }
        }

        len += size[1] - size[0]; // 마지막 선은 저장이 안되었으므로 마지막으로 저장

        System.out.println(len);
    }
}
