
//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 20768 KB , 시간 : 408 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int n = 0; n < N; n++) {
            list[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list); // 어차피 무작위로 뽑는 것이기 때문에 정렬해도 무관

        long answer = 0;
        for (int i = 0; i < N - 2; i++) {
            int target = -list[i];
            int left = i + 1;
            int right = N - 1;
            while (left < right) { // 넘어서는 순간 모든 학생을 순회했다.
                int sumTwo = list[left] + list[right];
                if (sumTwo == target) { // 두 학생을 더한 실력에 정반대의 실력을 지녔는지 확인(target을 -처리했기 때문에 같을 경우 실제로 둘을 더하면 0)
                    if (list[left] == list[right]) { // 두 학생의 실력이 같다면 두 학생 사이도 전부 동일한 실력이기 때문에 일괄 계산한다.
                                                     // 그리고 이 계산으로 남은 학생 전부 계산했기 때문에 break
                        int count = right - left + 1;
                        answer += (long) count * (count - 1) / 2; // 바꿔도 동일하기 때문에 하나로 본다.
                        break;
                    } else { // 두 학생의 실력이 다르다면
                        int cntL = 1;
                        int cntR = 1;
                        while (left + 1 < right && list[left] == list[left + 1]) { // 왼쪽기준으로 동일한 실력의 학생이 몇명이 있는지 확인
                            cntL++;
                            left++;
                        }
                        while (right - 1 > left && list[right] == list[right - 1]) { // 오른쪽 기준으로 동일한 실력의 학생이 몇명이 있는지 확인
                            cntR++;
                            right--;
                        }
                        answer += (long) cntL * cntR; // 순서를 바꿀 경우도 있기 때문에 두 학생의 실력이 같은 경우와 다르게 계산
                        // 세 학생을 더해서 0이 나왔다면 한 학생을 다른 실력으로 바꿀 경우 무조건 0이 안나오므로 두 학생 모두 다른 학생으로 대체
                        left++;
                        right--;
                    }
                } else if (sumTwo < target) {// 두 학생을 더한 실력보다 타겟 학생의 실력이 더 좋다면 평균 실력을 올려야하므로 최저 학생보다 높은 학생으로 대체
                    left++;
                } else { // 반대의 경우이므로 평균 실력을 낮춰야하므로 가장 높은 학생의 실력보다 낮은 학생으로 대체
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}