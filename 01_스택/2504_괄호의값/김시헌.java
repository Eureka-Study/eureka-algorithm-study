// 언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 11600 KB , 시간 : 64 ms

import java.io.*;
import java.util.*;

public class Main {    // 괄호의 값
    static int point = 0;   // 정답용 값
    static boolean isFail = false;  // 올바르지 못한 괄호열이면 true
    static Deque<Character> stack = new ArrayDeque<>();    // 괄호 쌓는 스택
    static Deque<IndexNum> calStack = new ArrayDeque<>();  // 계층 기억용 스택

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int idx = 0;      // 계층 기억용 스택에 사용될 인덱스

        for (int i = 0; i < line.length(); i++) {
            char chr = line.charAt(i);
            if (chr == '(') {
                stack.push(chr);
                idx++;

            } else if (chr == '[') {
                stack.push(chr);
                idx++;

            } else if (chr == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    calculate(idx, 2);
                    idx--;

                } else {
                    isFail = true;
                    break;
                }

            } else {
                if (!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                    calculate(idx, 3);
                    idx--;

                } else {
                    isFail = true;
                    break;
                }
            }

        }

        if (isFail || !stack.isEmpty()) System.out.println(0);  // 예외처리
        else {
            while (!calStack.isEmpty()) {
                point += calStack.pop().num;    // 스택에 남은 1계층들 더해주기
            }

            System.out.println(point);
        }
    }

    static void calculate(int idx, int num) {
        if (calStack.isEmpty()) {
            calStack.push(new IndexNum(idx, num));

        } else {
            int nowIndex = calStack.peek().index;
            if (idx < nowIndex) {   // 이번 계층이 스택의 peek 계층보다 작으면 ( ex. 2 < 3 )
                int tempPoint = 0;
                while (calStack.peek().index == nowIndex) {
                    IndexNum temp = calStack.pop();
                    tempPoint += temp.num;
                    if (calStack.isEmpty()) break;
                }
                calStack.push(new IndexNum(idx, tempPoint * num));
            } else {
                calStack.push(new IndexNum(idx, num));
            }
        }

    }

    static class IndexNum {
        int index, num;
        IndexNum(int index, int num) {
            this.index = index; this.num = num;
        }
    }
}
