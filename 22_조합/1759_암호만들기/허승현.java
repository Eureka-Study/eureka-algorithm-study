import java.io.*;
import java.util.*;


// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14268 KB , 시간 : 108ms

public class Main {

	static char[] src;
	static char[] tgt;
	static int L, C;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		src = new char[C];
		tgt = new char[L];
		
		String[] temp = br.readLine().split(" ");
        for (int i = 0; i < C; i++) {
            src[i] = temp[i].charAt(0);
        }
		
		
		Arrays.sort(src);

		comb(0, 0);

		System.out.println(sb);
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == L) {
			if (isValid()) {
				for (int i = 0; i < L; i++) {
					sb.append(tgt[i]);
				}
				sb.append("\n");

			}
			return;
		}

		if (srcIdx == C)
			return;

		tgt[tgtIdx] = src[srcIdx];

		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
	
	static boolean isValid() {
		int vowel = 0;
		int consonant = 0;
		for (char c : tgt) {
			if ("aeiou".indexOf(c) >= 0) vowel++;
			else consonant++;
		}
		
		return vowel >= 1 && consonant >= 2;
	}
}
