//언어 : JAVA , (성공/실패) : 0/2 , 메모리 : , 시간 :

//3인조의 합이 0이 되는 경우의 수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int count = 0;

        for (int i = 0; i < n - 2; i++) {// i 한개를 고정하고 그 뒤의 숫자들 투포인터로 골라내기
            if(i>0 && arr[i]==arr[i-1]) continue; // 고정 인덱스의 중복을 방지하기 위해 -> 고정인덱스가 0이면 괜찮은데 그 이후로부터는 또 같은 고정인덱스를 가질 필요가 없다.

            int point = arr[i];
            int left = i+1; //고정 인덱스 이후의 first 인덱스
            int right = n -1; //end 인덱스

            while(left<right){ // 투포인터로 돌림
                int sum = point+arr[left]+arr[right];
                if (sum == 0) {
                    count++;

                    // 0이 되면 아예 새로운 조합을 만들기 위해 start 인덱스 올리고 right 인덱스 내린다
                    left++;
                    right--;
// 문제를 똑바로 안 읽음. 두 개의 -4는 서로 다른 참가자를 나타내는 것에 유의하라. 라고 나와있음.

/*
                    // 만약에 left 인덱스가 중복이면 right만 내리는 꼴이 되니까 무조건 달라야해서 중복인것 패스.
                    while(left<right && arr[left]==arr[left-1]) left++;

                    //right 도 마찬가지
                    while(left<right && arr[right]==arr[right+1]) right--;
*/


                }else if(sum<0){
                    left++;
                }else
                    right--;
            }
        }

        System.out.println(count);
    }

}
