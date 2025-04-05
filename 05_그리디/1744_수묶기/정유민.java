// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11552 KB , 시간 : 68 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();

        int res = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 1) {
                plus.add(num);
            } else if (num == 1) {
                res += 1; // 1은 곱하지 말고 더한다. 왜냐? 다른 수랑 곱하는 것보다는 더하는게 더 이득
            } else {
                minus.add(num); // 음수도 마이너스에 넣어서 남는 음수 없애도록.
            }
        }

        // 양수 내림차순 정렬
        Collections.sort(plus, Collections.reverseOrder());

        // 음수 오름차순 정렬
        Collections.sort(minus);

        // 양수
        for (int i = 0; i < plus.size() - 1; i += 2) {
            res += plus.get(i) * plus.get(i + 1);
        }
        // 남는 양수가 있으면 더함
        if (plus.size() % 2 == 1) {
            res += plus.get(plus.size() - 1);
        }

        // 음수
        for (int i = 0; i < minus.size() - 1; i += 2) {
            res += minus.get(i) * minus.get(i + 1);
        }
        // 남는 음수가 있으면 더한다. 여기서 만약 0이 남는다? 더해서 없애버린다.
        if (minus.size() % 2 == 1) {
            res += minus.get(minus.size() - 1);
        }

        System.out.println(res);
    }
}
