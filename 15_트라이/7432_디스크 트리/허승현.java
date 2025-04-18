import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

// 문제 조건
// 각 서브 디렉토리는 사전순으로 출력한다.
// 서브 디렉토리는 부모 디렉토리에서 출력한 공백의 개수보다 1많게 공백 출력

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 24064 KB , 시간 : 244ms

public class Main {
	
	static Trie root = new Trie();
	
	static class Trie {
		Map<String, Trie> children;

		public Trie() {
			children = new TreeMap<>();
		}

	}
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			insert(br.readLine());
		}
		
		traversal(root, 0);
		System.out.println(sb);
	}
	
	static void insert(String path) {
		Trie curr = root; // 처음 호출 시 curr는 항상 root부터 시작 
		
		String[] paths = path.split("\\\\"); // 경로 이스케이프 고려해서 역슬래시 기준으로 분리
		
		// 각 경로 부분을 트라이에 삽입
		for (String part : paths) {
			if (!curr.children.containsKey(part)) { // 서브 폴더로 추가
				curr.children.put(part, new Trie());
			}
	
			curr = curr.children.get(part);
		}
		
	}
	
	static void traversal(Trie trie, int depth) {
		for (Map.Entry<String, Trie> entry : trie.children.entrySet() ) {
			// 부모 디렉토리보다 한 공백 더 들여쓰기를 해야 하므로 depth만큼 공백 출력하고 디렉토리 이름 출력
			sb.append(" ".repeat(depth) + entry.getKey()).append("\n");
			traversal(entry.getValue(), depth + 1);
		
		}
	}
	

}