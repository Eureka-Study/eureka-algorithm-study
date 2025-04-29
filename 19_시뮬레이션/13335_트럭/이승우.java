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
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        int time = 0;
        int currentWeight = 0;

        while (!waiting.isEmpty()) {
            time++;

            int left = bridge.poll();
            currentWeight -= left;

            if (currentWeight + waiting.peek() <= L) {
                int truck = waiting.poll();
                bridge.add(truck);
                currentWeight += truck;
            } else {
                bridge.add(0);
            }
        }

        System.out.println(time + w);
    }
}