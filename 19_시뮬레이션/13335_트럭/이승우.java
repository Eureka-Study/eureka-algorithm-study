
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15196 KB , 시간 : 120 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트럭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()),
                w = Integer.parseInt(st.nextToken()),
                L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        Queue<Integer> waiting = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            waiting.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) { // 브릿지의 길이 만큼 생성
            bridge.add(0);
        }

        int time = 0;
        int currentWeight = 0;

        while (!waiting.isEmpty()) { // 대기가 없으면 굳이 할 이유가 없다.
            time++;

            int left = bridge.poll();
            currentWeight -= left; // 트럭이 벗어났으면 그 트럭의 무게만큼 브릿지 안의 무게를 줄인다.

            if (currentWeight + waiting.peek() <= L) {// 대기 중인 트럭이 진입을 했을 때 브릿지가 버티는지 확인
                int truck = waiting.poll();
                bridge.add(truck);
                currentWeight += truck;
            } else {
                bridge.add(0);
            }
        }

        System.out.println(time + w); // 마지막이 진입하고 그 후에 확인을 안했으나 무조건 w 초 후에 완전히 종료될것이기 때문에 추가해서 print
    }
}