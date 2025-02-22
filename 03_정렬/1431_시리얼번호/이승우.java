// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 15468KB , 시간 : 128ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class serial{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] serial = new String[N];

        for(int i = 0; i < N; i++){
            serial[i] = br.readLine();
        }
        
        //배열 정렬 시작
        Arrays.sort(serial, (s1, s2) -> {
            // T 라서 String이라는 보장이 없기 때문에 string으로 변환
            String str1 = s1.toString(); 
            String str2 = s2.toString();

            if(str1.length() != str2.length()){ // 만약 길이가 다를 경우
                return str1.length() - str2.length(); // 길이가 짧은 것을 우선
            }else{ // 길이가 같을 경우
                String[] str1List = str1.split("");
                String[] str2List = str2.split("");
                int str1Sum = 0;
                int str2Sum = 0;

                for(int i = 0; i < str1List.length; i++){ //숫자만 뽑아 더하기
                    try{
                        str1Sum += Integer.parseInt(str1List[i]);
                    }catch(NumberFormatException e){

                    }

                    try {
                        str2Sum += Integer.parseInt(str2List[i]);
                    } catch (NumberFormatException e) {

                    }
                }

                if(str1Sum != str2Sum){// 숫자 합 중 오른차순을 정렬
                    return str1Sum - str2Sum;
                }else{// 만약 숫자 합도 같을 경우
                    for(int i = 0; i < str1.length(); i++){ // 사전 순으로 정렬
                        if(str1.charAt(i) != str2.charAt(i)){
                            return str1.charAt(i) - str2.charAt(i);
                        }
                    }
                }
                return 0; // 어차피 여기까지 올일 없음
            }
        });

        StringBuilder sb = new StringBuilder();

        for(String s : serial){
            sb.append(s)
              .append("\n");
        }

        System.out.println(sb);
    }
}