import java.io.*;
import java.util.*;

// 문제 정의
// N,M은 100,000 보다 작거나 같음
// 첫 글자만 대문자 하지만 일부 포켓몬은 마지막 문자만 대문자일 수도 있음
// 문제가 알파벳으로 들어오면 번호를, 번호면 포켓몬 이름을 출력

// 문제 해결
// HashMap<String,String> 으로 생성해서 번호,이름 구분없이 key로 저장해보자
// hashmap이니까 timeout은 안날 것 같음

// Pseudo Code
// HashMap<String,String> 으로 생성
// Map 초기화
// map.put(name, number);
// map.put(number, name);

// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 50264 KB , 시간 : 456 ms

// 실패 이유: 출력 수를 M으로 안하고 테스트케이스 5를 넣어버림
public class Main {
	
	static HashMap<String,String> map = new HashMap<String, String>();
	static int N,M;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 포겟몬 수
        M = Integer.parseInt(st.nextToken()); // 문제 수

        for (int i = 1; i <= N; i++) {
        	String name = br.readLine();
        	String number = Integer.toString(i);
        	
        	map.put(name, number);
        	map.put(number, name);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
        	sb.append(map.get(br.readLine())).append("\n");
        }
        
        System.out.println(sb);
    }
}