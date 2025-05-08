// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 20876 KB , 시간 : 416 ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long count = 0;

        // fix one number, use two pointers for the rest
        for(int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while(left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if(sum == 0) {
                    if(arr[left] == arr[right]) {
                        int n = right - left + 1;
                        count += (long)n * (n - 1) / 2;
                        break;
                    } else {
                        int leftCount = 1;
                        int rightCount = 1;
                        while(left + 1 < right && arr[left] == arr[left + 1]) {
                            leftCount++;
                            left++;
                        }
                        while(right - 1 > left && arr[right] == arr[right - 1]) {
                            rightCount++;
                            right--;
                        }
                        count += (long)leftCount * rightCount;
                        left++;
                        right--;
                    }
                } else if(sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
