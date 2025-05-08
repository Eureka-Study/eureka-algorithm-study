import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

        int N = Integer.parseInt(br.readLine());
        int[] ipList = new int[N];

        // IP 주소 정수 변환
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");
            int ip = 0;
            for (int j = 0; j < 4; j++) {
                ip |= Integer.parseInt(st.nextToken()) << (24 - 8 * j);
            }
            ipList[i] = ip;
        }

        // 최솟값, 최댓값 계산
        int min = ipList[0];
        int max = ipList[0];
        for (int ip : ipList) {
            min = Math.min(min, ip);
            max = Math.max(max, ip);
        }

        // 다른 비트 구하기
        int diff = min ^ max;

        // 몇 비트가 다른지 구하기 (shift)만큼은 서로 다르다 => 전체 32비트 중 32 - shift 비트는 같음
        int shift = 0;
        while ((diff >> shift) != 0) {
            shift++;
        }

        // 서브넷 마스크
        // 공통된 비트 수 만큼 1, 나머지는 0으로 만들면 서브넷 마스크
        int mask = ~((1 << shift) - 1);
        
        // 네트워크 주소 계산
        // 아무 IP하나와 서브넷 마스크를 AND 연산 -> 공통된 비트만 남음 (= 네트워크 주소)
        int network = ipList[0] & mask;

        PrintIP(network);
        PrintIP(mask);


        System.out.print(sb);
    }

    static void PrintIP(int ip) {
        sb.append((ip >> 24) & 0xFF).append(".")
          .append((ip >> 16) & 0xFF).append(".")
          .append((ip >> 8) & 0xFF).append(".")
          .append(ip & 0xFF).append("\n");
    }
}
