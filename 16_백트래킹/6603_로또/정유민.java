// 언어 : JAVA , (성공/실패) : 미제출 , 메모리 :  , 시간 :


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정유민 {

    static int[] src;
    static int[] tgt = new int[6];
    static int[] index;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            if (k == 0) break;

            src = new int[k];
            index = new int[src.length];
            for (int i = 0; i < k; i++) {
                src[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = src.length - tgt.length; i < src.length; i++) {
                index[i]=1;
            }

            while (true) {
                int tgtIdx = 0;
                for (int i = 0; i < index.length; i++) {
                    if (index[i] == 1) {
                        tgt[tgtIdx++]=src[i];
                    }
                }
                for (int i = 0; i < tgt.length; i++) {
                    sb.append(tgt[i]).append(" ");
                }
                sb.append("\n");

                if(!np(index)) break;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean np(int[] array) {
        int i = array.length -1;
        while(i>0 && array[i-1] >= array[i]) --i;

        if(i==0) return false;

        int j = array.length - 1;
        while(array[i-1] >= array[j]) --j;
        swap(array, i - 1, j);

        int k = array.length -1;
        while (i < k) {
            swap(array, i++,k--);
        }
        return true;
    }
    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
