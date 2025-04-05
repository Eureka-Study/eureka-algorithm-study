import java.io.*;
import java.util.*;

//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 333648 KB , 시간 : 1552ms

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		Deque<Integer> queue = new ArrayDeque<Integer>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			switch (command) {
			case "push":
				queue.offer(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				sb.append(queue.isEmpty() ? "-1\n" : queue.poll() + "\n");
				break;
			case "size":
				sb.append(queue.size()).append("\n");
				break;
			case "empty":
				sb.append(queue.isEmpty() ? "1\n" : "0\n");
				break;
			case "front":
				sb.append(queue.isEmpty() ? "-1\n" : queue.peekFirst() + "\n");
				break;
			case "back":
				sb.append(queue.isEmpty() ? "-1\n" : queue.peekLast() + "\n");
				break;
			}
		}
		System.out.println(sb.toString());

	}

}