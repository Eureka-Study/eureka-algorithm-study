// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 324940 KB , 시간 : 1368 ms

import java.io.*;
import java.util.*;

public class Main {  // 차집합
    static int nA, nB;
    static HashSet<Integer> A = new HashSet<>();    // A는 HashSet으로 선언
    static int[] B;     // B는 int배열로 선언

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nA = Integer.parseInt(st.nextToken());      // A 집합 크기
        nB = Integer.parseInt(st.nextToken());      // B 집합 크기

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nA; i++) {
            A.add(Integer.parseInt(st.nextToken()));    // A HashSet에 원소 넣어주기
        }

        B = new int[nB];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nB; i++) {
            B[i] = Integer.parseInt(st.nextToken());    // B 배열에 원소 넣어주기
        }

        // 풀이
        for (int i = 0; i < nB; i++) {
            A.remove(B[i]);     // B 배열을 for문 돌면서 B의 원소와 같은 값의 A의 원소를 빼줌 (없으면 false리턴)
        }

        if (A.size() == 0) {    // 인텔리제이가 isEmpty()를 사용해도 된다고 함
            bw.write('0');
            bw.flush();

        } else {
            ArrayList<Integer> list = new ArrayList<>(A);   // 정렬을 위해서 A를 ArrayList로 재정의
            Collections.sort(list);     // 정렬
            bw.write(String.valueOf(list.size()));
            bw.newLine();
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i) + " ");
            }
            bw.flush();
        }

        bw.close();
        br.close();
    }
}
