//언어 : JAVA , (성공/실패) : 1/4 , 메모리 : 1236 KB , 시간 : 1620 ms
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            List<int[]> applicant = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int docRank = Integer.parseInt(st.nextToken());
                int interviewRank = Integer.parseInt(st.nextToken());
                applicant.add(new int[]{docRank, interviewRank});
            }

            Collections.sort(applicant, (a, b) -> Integer.compare(a[0], b[0]));

            int minInterviewRank = applicant.get(0)[1];
            int count = 0;

            for (int[] a : applicant) {
                if (a[1] <= minInterviewRank) {
                    minInterviewRank = a[1];
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }
}