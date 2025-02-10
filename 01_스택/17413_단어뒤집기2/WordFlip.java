import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordFlip {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        StringBuilder answer = new StringBuilder();
        StringBuilder word = new StringBuilder();
        boolean inTag = false;

        for (char ch : str.toCharArray()) {
            if (ch == '<') {  
                answer.append(word.reverse());//< 이전까지의 문자를 뒤집어서 저장
                word.setLength(0); // word 초기화
                inTag = true; //태그 활성화
                answer.append(ch); // <
            } else if (ch == '>') {
                inTag = false; //태그 비활성화
                answer.append(ch); // >
            } else if (inTag) { //태그 상태일 때
                answer.append(ch); // 태그
            } else { //태그 상태가 아닐 때
                if (ch == ' ') {  // 스페이스 바를 만날 때 그 이전까지 저장한 것을 뒤집어서 저장
                    answer.append(word.reverse());
                    word.setLength(0);
                    answer.append(ch);
                } else { //word에 저장
                    word.append(ch);
                }
            }
        }
        answer.append(word.reverse()); // 마지막이 문자일 경우 대비

        System.out.println(answer);
    }
}
