// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 23268 KB , 시간 : 148ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 입출력, 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();     // 정답 담을 스트링빌더 생성
        boolean isTag = false;  // 태그가 열리면('<'): true, 태그가 닫히면('>'): false
        Deque<Character> stack = new ArrayDeque<>();   // char 하나씩 담을 스택 생성
        String S = br.readLine();    // 문자열 받기

        // 구현
        for (int i = 0; i < S.length(); i++) {
            char character = S.charAt(i);   // 이번 char 문자

            if (character == '<') {         // 태그 열림
                // 우선, 태그열리기 전에 stack에 쌓여있던 문자들 pop해서 sb에 추가
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }

                isTag = true;
                sb.append(character);       // < sb에 추가

            } else if (character == '>') {  // 태그 닫힘
                isTag = false;
                sb.append(character);       // > sb에 추가

            } else if (isTag) {             // 태그 안의 문자들 sb에 추가
                sb.append(character);

            } else if (character == ' ') {  // 공백이면 stack pop들 sb에 추가
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(' ');

            } else {
                stack.push(character);    // char 문자 하나씩 스택에 넣기
            }

        }
        // 마지막으로, stack의 문자들 pop해서 sb에 추가
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        // 출력
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();

    }
}
