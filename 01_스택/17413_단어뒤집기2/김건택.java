// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 22928 KB , 시간 : 348ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        char[] chars = input.toCharArray();

        StringBuilder answer = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        boolean check = false;
        for (char c : chars) {
            if (c == '<') {
                check = true;
            } else if (c == '>') {
                check = false;
                answer.append(c);
                continue;
            } else if (c == ' ') {
                answer.append(temp.reverse().toString()).append(c);
                temp.setLength(0);
                continue;
            }
            if (check) {
                answer.append(temp.reverse().toString());
                temp.setLength(0);
                answer.append(c);
            } else {
                temp.append(c);
            }
        }
        answer.append(temp.reverse().toString());

        System.out.println(answer.toString());


    }
}