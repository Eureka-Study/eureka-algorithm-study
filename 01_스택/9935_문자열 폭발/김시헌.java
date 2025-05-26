// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 165168 KB , 시간 : 460 ms

import java.io.*;
import java.util.*;

public class Main {    // 문자열 폭발
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();

        Deque<Character> deq = new ArrayDeque<>();      // 문자열 스택
        ArrayList<Character> temp = new ArrayList<>();  // 체크용 임시리스트

        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            deq.offer(chr);

            for (int j = bomb.length() - 1; j >= 0 ; j--) {
                if (deq.isEmpty()) break;   // 스택 비어있으면 확인할 필요 없음

                char popped = deq.pollLast();   // 스택 맨 위
                temp.add(popped);   // 일단 임시리스트에 넣고

                if (popped != bomb.charAt(j)) { // 만약 스택 맨 위와 폭탄의 맨 끝이 같으면 넘어가고, 다르면 이 루프 실행
                    for (int k = temp.size() - 1; k >= 0; k--) {    // 임시리스트 맨 오른쪽부터 스택에 다시 넣고
                        deq.offer(temp.get(k));
                    }
                    temp = new ArrayList<>();   // 다 넣었으면 임시리스트 초기화
                    break;
                }

                if (j != 0 && deq.isEmpty()) {  // 아직 j가 0이 안됐는데 deq가 비어버렸으면 (bomb가 bdbd인데 str이 bd~ 이었을 경우 대비)
                    for (int k = temp.size() - 1; k >= 0; k--) {
                        deq.offer(temp.get(k));
                    }
                    temp = new ArrayList<>();
                    break;
                }

                if (j == 0) {   // 여기까지 왔으면 bomb 폭탄 터진거
                    temp = new ArrayList<>();   // 임시리스트 초기화
                }
            }
        }

        if (deq.isEmpty()) {
            System.out.println("FRULA");    // 다 하고 스택 비어있으면 FRULA 출력

        } else {
            StringBuilder sb = new StringBuilder();

            while (!deq.isEmpty()) {
                sb.append(deq.pollFirst()); // 스택 맨 아래부터 출력
            }

            System.out.println(sb);
        }
    }
}
