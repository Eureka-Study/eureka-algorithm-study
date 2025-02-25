// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 212400 KB , 시간 : 1624 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정유민 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeSet<Integer> set1 = new TreeSet<>();
        TreeSet<Integer> set2 = new TreeSet<>();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a; i++) {
            set1.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b; i++) {
            set2.add(Integer.parseInt(st.nextToken()));
        }
        //집합 a에는 속하면서 집합 b에는 속하지 않는 원소의 개수
        List<Integer> res = new ArrayList<>();
        for (int num : set1) {
            if (!set2.contains(num)) {
                res.add(num);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (res.isEmpty()) {
            sb.append("0");
        } else {
            sb.append(res.size()).append("\n");
            for (int num : res) {
                sb.append(num).append(" ");
            }
        }

        System.out.println(sb);
    }
}
