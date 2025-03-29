// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 54500 KB , 시간 : 976 ms

import java.util.*;
import java.io.*;

class 나는야포켓몬마스터이다솜 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, String> numToStr = new HashMap<>();
        Map<String, Integer> StrToNum = new HashMap<>();

        for(int i = 1 ; i<=n; i++){
            String a= br.readLine();
            numToStr.put(i,a);
            StrToNum.put(a,i);
        }
        for(int i = 1; i<=m ; i++) {
            String a = br.readLine();
            int num;
            if(a.charAt(0)>48 && a.charAt(0)<58){
                num = Integer.parseInt(a);
                System.out.println(numToStr.get(num));
            }else System.out.println(StrToNum.get(a));
        }
    }
}
