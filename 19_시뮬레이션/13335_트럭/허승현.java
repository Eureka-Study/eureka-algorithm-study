//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14468 KB , 시간 : 120ms

import java.io.*;
import java.util.*;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Queue<Integer> trucks = new ArrayDeque<Integer>();
		for (int i = 0; i < n; i++) {
			trucks.add(Integer.parseInt(st.nextToken()));
		}
		
		Queue<Integer> bridge = new ArrayDeque<Integer>(); // 현재 건너고 있는 트럭의 무게가 위치 별로 들어있음
		// 처음에 다리 위에 트럭 수 만큼 0을 배치
		for (int i = 0; i < w; i++) {
			bridge.add(0);
		}
		
		int time = 0;
		int weightStatus = 0; // 현재 다리를 건너고 있는 트럭 무게의 합
		
		while(!bridge.isEmpty()) {
			time++;
			// 해당 지점에 트럭이 없었다면 0을 빼고 트럭이 있었다면 건너고 있는 트럭의 무게가 빠진다.
			weightStatus -= bridge.poll();
			
			// 트럭이 비어있으면 건너뜀(모든 트럭이 큐에서 빠져나가도 현재 다리를 건너는 트럭이 있을 수 있음)
			if (trucks.isEmpty()) continue;
			
			// 트럭이 다리 위를 지날 수 있는 경우
			if (trucks.peek() + weightStatus <= L) {
				int truck = trucks.poll();
				weightStatus += truck;
				bridge.add(truck);
			}else {
				// 지나갈 수 없으면 0 삽입
				bridge.add(0);
			}
		}
		
		System.out.println(time);
		
	}

	

}