// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 227176 KB , 시간 : 1588 ms
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            setA.add(Integer.parseInt(st.nextToken())); // A 집합 입력
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            setB.add(Integer.parseInt(st.nextToken())); // B 집합 입력
        }

        setA.removeAll(setB); // 집합 A에서 집합 B에 있는 요소 삭제
        List<Integer> result = new ArrayList<>(setA); // 집합 A를 리스트에 넣고
        Collections.sort(result); // 정렬
        bw.write(setA.size() + "\n");
        for (Integer i : result) {
            bw.write(i + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
