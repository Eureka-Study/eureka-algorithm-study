
//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 16868 KB , 시간 : 160 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 비트마스킹 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ips = new int[N]; // int는 32비트 ip 주소도 32비트 이므로 int로 처리
        for (int i = 0; i < N; i++) {
            String[] p = br.readLine().split("\\.");

            // 같은 비트 위치이면 숫자가 변경되기에 그것을 막기위해 비트연산자로 ip주소 그대로 저장
            ips[i] = (Integer.parseInt(p[0]) << 24)
                    | (Integer.parseInt(p[1]) << 16)
                    | (Integer.parseInt(p[2]) << 8)
                    | Integer.parseInt(p[3]);
        }

        int mask = 0;

        out: // break loop

        // 서브넷 마스크는 같은 대역폭을 가진 경우까지 1로 처리하고 나머지는 0
        for (int bit = 31; bit >= 0; bit--) {
            int b = (ips[0] >>> bit);
            for (int ip : ips) {
                if ((ip >>> bit) != b) { // 0번이랑 다른 ip 중 하나라도 다르면 이중 for문을 전부 빠져나간다.(이 이후의 ip부터 0처리)
                    break out;
                }
            }
            mask |= (1 << bit);
        }
        int network = ips[0] & mask; // 네트워크 마스크 중 1로 처리된부분은 동일하게 가야하고 0인 부분은 일괄 0으로 하면 가장 크기가 작은 ip 주소이다.

        StringBuilder sb = new StringBuilder();

        sb.append(toIpString(network))
                .append("\n")
                .append(toIpString(mask));

        System.out.println(sb);
    }

    private static String toIpString(int x) {
        // & 0xff를 하는 이유는 x >> 를 하게되면 24는 문제 없지만 16 같은 경우 32 ~ 24비트에 있던 비트들도 있기 때문에
        // 8비트만 가져오기 위해서 존재한다.
        return ((x >>> 24)) + "."
                + ((x >>> 16) & 0xFF) + "."
                + ((x >>> 8) & 0xFF) + "."
                + (x & 0xFF);
    }
}