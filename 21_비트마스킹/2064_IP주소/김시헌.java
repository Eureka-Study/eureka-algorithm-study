// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 13264 KB , 시간 : 96 ms

import java.io.*;
import java.util.*;

public class Main {    // IP 주소
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String minIp = "255.255.255.255";
        String maxIp = "0.0.0.0";

        StringTokenizer st1;
        StringTokenizer st2;
        StringTokenizer st3;
        for (int i = 0; i < N; i++) {
            String ip = br.readLine();

            st1 = new StringTokenizer(ip, ".");
            st2 = new StringTokenizer(minIp, ".");
            for (int j = 0; j < 4; j++) {   // min 판별
                int bit1 = Integer.parseInt(st1.nextToken());   // ip bit
                int bit2 = Integer.parseInt(st2.nextToken());   // minIp bit

                if (bit1 < bit2) {
                    minIp = ip;
                    break;
                }
                if (bit1 > bit2) break;
            }

            st1 = new StringTokenizer(ip, ".");
            st3 = new StringTokenizer(maxIp, ".");
            for (int j = 0; j < 4; j++) {   // max 판별
                int bit1 = Integer.parseInt(st1.nextToken());   // ip bit
                int bit3 = Integer.parseInt(st3.nextToken());   // maxIp bit

                if (bit1 > bit3) {
                    maxIp = ip;
                    break;
                }
                if (bit1 < bit3) break;
            }
        }

        int m = 0;
        int diffLen = 0;

        st2 = new StringTokenizer(minIp, ".");
        st3 = new StringTokenizer(maxIp, ".");
        for (int i = 0; i < 4; i++) {
            int bit2 = Integer.parseInt(st2.nextToken());
            int bit3 = Integer.parseInt(st3.nextToken());

            if (bit2 == bit3) continue;
            else {
                String diff = Integer.toBinaryString(bit2 ^ bit3);  // XOR 연산으로 다른부분 구하기
                diffLen = diff.length();    // 밑에 network, mask 완성시킬때 써먹을것
                m = (3-i) * 8 + diffLen;    // m 세팅
                break;
            }
        }

        StringBuilder mask = new StringBuilder();   // mask 완성
        if (32  >= m && m > 24) {
            mask.append(255 - (int)Math.pow(2, diffLen) + 1).append(".0.0.0");
        } else if (24  >= m && m > 16) {
            mask.append("255.").append(255 - (int)Math.pow(2, diffLen) + 1).append(".0.0");
        } else if (16  >= m && m > 8) {
            mask.append("255.255.").append(255 - (int)Math.pow(2, diffLen) + 1).append(".0");
        } else {
            mask.append("255.255.255.").append(255 - (int)Math.pow(2, diffLen) + 1);
        }

        st2 = new StringTokenizer(minIp, ".");
        int bit1 = Integer.parseInt(st2.nextToken());
        int bit2 = Integer.parseInt(st2.nextToken());
        int bit3 = Integer.parseInt(st2.nextToken());
        int bit4 = Integer.parseInt(st2.nextToken());

        StringBuilder net = new StringBuilder();    // network 완성
        if (32  >= m && m > 24) {
            String bitCal = Integer.toBinaryString( (bit1 >> diffLen) << diffLen );
            int bitToInt = Integer.parseInt(bitCal,2);
            net.append(bitToInt).append(".0.0.0");
        } else if (24  >= m && m > 16) {
            String bitCal = Integer.toBinaryString( (bit2 >> diffLen) << diffLen );
            int bitToInt = Integer.parseInt(bitCal,2);
            net.append(bit1).append(".").append(bitToInt).append(".0.0");
        } else if (16  >= m && m > 8) {
            String bitCal = Integer.toBinaryString( (bit3 >> diffLen) << diffLen );
            int bitToInt = Integer.parseInt(bitCal,2);
            net.append(bit1).append(".").append(bit2).append(".").append(bitToInt).append(".0");
        } else {
            String bitCal = Integer.toBinaryString( (bit4 >> diffLen) << diffLen );
            int bitToInt = Integer.parseInt(bitCal,2);
            net.append(bit1).append(".").append(bit2).append(".").append(bit3).append(".").append(bitToInt);
        }

        System.out.println(net);
        System.out.println(mask);
    }
}
