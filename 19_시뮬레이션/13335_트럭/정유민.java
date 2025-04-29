// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 12116 KB , 시간 : 104 ms

//다리 n개 트럭 지나가려고 함
//트럭 순서 못바꿈
//무게 다르거나 같음
//다리의 길이 w = 트럭 w대
//단위 시간에 하나의 단위길이
//다리 위의 트럭의 무게 합 <= 다리 최대하중 L
//다리 위에 있는 트럭만 생각한다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트럭 {

    static int n, w, l;
    static int[] bridge;
    static Queue<Integer> truck = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //다리 건너는 트럭 수
        w = Integer.parseInt(st.nextToken()); //다리길이
        l = Integer.parseInt(st.nextToken()); //최대하중

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck.offer(Integer.parseInt(st.nextToken()));
        }

        bridge = new int[w]; //다리 위의 트럭 위치로 쓰임

        int minTime = 0;
        while (true) {
            int weight = move();
            pushTruck(weight);
            minTime++;

            if(sumTruck()==0)break;
        }
        System.out.println(minTime);
    }


    static int move() {
        for (int i = bridge.length-1; i >0; i--) {
            bridge[i] = bridge[i -1]; //트럭 전체 한 칸 씩 옮김
        }
        bridge[0]=0;
        return sumTruck();
    }

    private static void pushTruck(int weight) {
        if (!truck.isEmpty()) {
            if (l >= weight + truck.peek()) {
                bridge[0] = truck.poll(); // 트럭 넣는다
            }
        }
    }

    static int sumTruck() {
        int sum = 0;
        for (int i = 0; i < bridge.length; i++) {
            sum += bridge[i];
        }
        return sum;
    }
}

