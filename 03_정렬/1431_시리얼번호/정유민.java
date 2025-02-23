// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 14312 KB , 시간 : 108ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            list.add(br.readLine());
        }

        /*
        1. A와 B의 길이가 다르면, 짧은 것이 먼저 온다.
        2. 만약 서로 길이가 같다면, A의 모든 자리수의 합과 B의 모든 자리수의 합을 비교해서 작은 합을 가지는 것이 먼저온다. (숫자인 것만 더한다)
        3. 만약 1,2번 둘 조건으로도 비교할 수 없으면, 사전순으로 비교한다. 숫자가 알파벳보다 사전순으로 작다.
         */
        list.sort((x, y) -> {
            if (x.length() != y.length()) { // 만약 길이가 다르다면 ?
                return x.length() - y.length(); // 짧은 것이 먼저 온다.
            }else{ //만약 길이가 같다면 ?
//                for (int i = 0; i < x.length(); i++) { // 코드 리뷰 중 for 문 이거 왜 했는지 모르겠습니다. 주석 처리 하고 다시 제출해서 통과하였습니다.
                    int xTotal = totalMachine(x); //합치기기계를 따로 만들었다.
                    int yTotal = totalMachine(y);

                    if (xTotal != yTotal) { //자리수의 합이 다르다면 ?
                        return Integer.compare(xTotal, yTotal); //자리수의 합 크기 비교. -1 또는 1이 넘어가겠지.
                    }
//                }
            }
            return x.compareTo(y); // 최종적으로 비교가 불가능할 때, 사전순 비교
        });

        for (String s : list) {
            System.out.println(s);
        }
    }

    static int totalMachine(String s) { //합치기 기계.
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0'; // 오답의 원흉. 숫자로 잘 뽑아냈다.
            if (0 <= num && num < 10) {
                total += num;
            }
        }
        return total;
    }
}