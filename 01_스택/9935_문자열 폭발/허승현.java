//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 35716KB , 시간 : 424ms

import java.io.*;
import java.util.*;

public class Main {
	// 메모리 초과
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String str = br.readLine();
//		String bomb = br.readLine();
//
//		while (str.contains(bomb)) {
//			str = str.replace(bomb, "");
//		}
//
//		if (str.length() == 0) {
//			System.out.println("FRULA");
//		} else {
//			System.out.println(str);
//		}
//
//	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();

		int m = bomb.length();
		char last = bomb.charAt(m - 1);

		Stack<Character> stack = new Stack<>();

		for (char c : str.toCharArray()) {
			stack.push(c);
			// 스택의 마지막 글자가 폭발 문자열과 같으면 스택에 담겨진 내용 검사
			if (c == last && stack.size() >= m) {
				boolean matched = true;
				// 폭발 문자열 역순으로 비교
				for (int i = 0; i < m; i++) {
					if (stack.get(stack.size() - m + i) != bomb.charAt(i)) {
						matched = false;
						break;
					}
				}
				if (matched) {
					// str내 bomb 제거(m개만큼)
					for (int i = 0; i < m; i++) {
						stack.pop();
					}
				}
			}
		}

		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (char c : stack) {
				sb.append(c);
			}
			System.out.println(sb);
		}
	}

}