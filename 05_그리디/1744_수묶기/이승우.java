//언어 : JAVA , (성공/실패) : 1/2 , 메모리 : 14248 KB , 시간 : 104 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 수묶기{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        List<Integer> positive = new ArrayList<>(); // 양수인 것만
        List<Integer> negative = new ArrayList<>(); // 음수인 것만
        boolean zero = false; // 0 여부 파악
        int one = 0; // 1은 어떤 것을 곱해도 그 수이기 때문에 더하는게 이득이기 때문에 따로 부여

        for(int n = 0; n < N; n++){
            int num = Integer.parseInt(br.readLine());

            if(num > 1){
                positive.add(num);
            }else if(num == 1){
                one++;
            }else if(num == 0){
                zero = true;
            }else{
                negative.add(num);
            }
        }

        positive.sort((n1, n2) -> n2 - n1); // 큰 수부터 계산하기위해 내림차순
        negative.sort((n1, n2) -> n1 - n2); // 음수이기 때문에 작은 순으로 곱하면 +가 되어 큰 수로 변하기 때문에 오름차순으로 해야됨

        int answer = 0;

        for(int i = 0; i < positive.size(); i += 2){
            // 마지막일 경우 1개이기 때문에 묶을 수 없음
            if(i + 1 < positive.size()){
                answer += positive.get(i) * positive.get(i + 1);
            }else{
                answer += positive.get(i);
            }
        }

        for(int i = 0; i < negative.size(); i += 2){
            // 마지막일 경우 1개 이기 때문에 묶을 수 없음 단, 0이 있다면 그 수를 제거할 수 있기 때문에 0이 없을 경우만 더하기
            if(i + 1 < negative.size()){ 
                answer += negative.get(i) * negative.get(i + 1);
            }else if(!zero){
                answer += negative.get(i);
            }
        }

        System.out.println(answer + one); // 1은 그냥 더하는게 이득이기에 1이 있는만큼 추가로 더하기
    }
}