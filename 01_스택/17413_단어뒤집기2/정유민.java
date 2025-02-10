// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 27356 KB , 시간 : 356ms
import java.util.*;

class 정유민 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        String input = sc.nextLine();
        boolean reverse = true;

        for (int i = 0; i < input.length(); i++) {
            char word = input.charAt(i);

            switch (word) {
                case ' ':
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(word);
                    break;
                case '<':
                    reverse = false;
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(word);
                    break;
                case '>':
                    reverse = true;
                    sb.append(word);
                    break;
                default:
                    if (reverse) {
                        stack.push(word);
                    } else {
                        sb.append(word);
                    }
                    break;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
        sc.close();
    }
}

