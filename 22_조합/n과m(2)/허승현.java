import java.io.*;
import java.util.*;


//언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15964KB , 시간 : 124ms

public class Main {

	static int N, M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[M];
        
        dfs(0,1);
    
        System.out.println(sb);
    }
    
    static void dfs(int depth, int start) {
    	if (depth == M) {
    		for (int num : arr) {
    			sb.append(num).append(" ");
    		}
    		sb.append("\n");
    		return;
    	}
    	
    	for (int i = start; i <= N; i++) {
    		arr[depth] = i;
    		dfs(depth+1,i+1);
    	}

    }
}

