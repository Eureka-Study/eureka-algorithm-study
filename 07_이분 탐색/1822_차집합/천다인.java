//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 182180 KB , 시간 : 1252 ms
import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Set<Integer> setA = new HashSet<>();

        int aNum = Integer.parseInt(st.nextToken()); // A의 원소의 개수
        int bNum = Integer.parseInt(st.nextToken()); // B의 원소의 개수

        // A 집합 입력 및 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aNum; i++) {
            setA.add(Integer.parseInt(st.nextToken()));
        }

        // B 집합 입력 및 A에서 제거
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bNum; i++) {
            setA.remove(Integer.parseInt(st.nextToken()));
        }

        // 남아있는 원소들을 정렬하기 위해 List로 변환
        List<Integer> list = new ArrayList<>(setA);
        Collections.sort(list);

        sb.append(list.size()).append("\n");
        for(int l : list) {
            sb.append(l).append(" ");
        }
        System.out.print(sb);
    }
}