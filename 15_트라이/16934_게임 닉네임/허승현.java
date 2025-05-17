import java.io.*;
import java.util.*;

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 98752KB , 시간 : 672ms

public class Main {

	static Trie root = new Trie();
	static Map<String, Integer> nameCount = new HashMap<>();

	static class Trie {
		Map<Character, Trie> children = new HashMap<>();
		int passCount = 0;
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			insert(br.readLine());
		}

		System.out.print(sb);
	}

	static void insert(String name) {
		Trie node = root;
		StringBuilder prefix = new StringBuilder();
		boolean printed = false;

		for (char ch : name.toCharArray()) {
			prefix.append(ch);
			if (!node.children.containsKey(ch)) {// 처음 등장하는 문자
				node.children.put(ch, new Trie());
			}
			node = node.children.get(ch);
			node.passCount++;

			if (!printed && node.passCount == 1) { // 이 접두어는 처음이다!
				sb.append(prefix).append("\n");
				printed = true;
			}
		}

		if (!nameCount.containsKey(name)) {
			nameCount.put(name, 1);
			if (!printed) sb.append(name).append("\n"); //포함관계 ab는 abcd의 포함관계 처리
		} else {// ab 등장 이후 ab등장
			int count = nameCount.get(name) + 1;
			nameCount.put(name, count);
			sb.append(name).append(count).append("\n");
		}
	}
}