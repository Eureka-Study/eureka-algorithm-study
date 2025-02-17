// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 16124KB , 시간 : 132ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        list.add(new Integer[]{1, 0});
        list.add(new Integer[]{0, 1});

        for(int i = 0; i < N; i++){
            int n = Integer.parseInt(br.readLine());

            for(int j = list.size(); j <= n; j++){
                Integer[] one = list.get(j - 2);
                Integer[] two = list.get(j - 1);

                list.add(new Integer[]{one[0]+two[0], one[1]+two[1]});
            }
            Integer[] arr = list.get(n);
            System.out.println(arr[0] + " " + arr[1]);
        }
    }
}