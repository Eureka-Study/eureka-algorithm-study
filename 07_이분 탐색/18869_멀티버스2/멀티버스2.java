import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 멀티버스2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n], sorted;
        int[][] zips = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            sorted = Arrays.stream(arr)
                    .distinct()
                    .sorted()
                    .toArray();

            for (int j = 0; j < n; j++) {
                zips[i][j] = Arrays.binarySearch(sorted, arr[j]);
            }
        }

        int total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (Arrays.equals(zips[i], zips[j])) total++;
            }
        }

        System.out.println(total);
    }
}