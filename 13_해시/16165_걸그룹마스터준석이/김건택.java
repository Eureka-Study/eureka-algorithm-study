// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 14148 KB , 시간 : 104 ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, String> groupMap = new HashMap<>();
        Map<String, Integer> memberMap = new HashMap<>();
        Map<Integer, String[]> sortedMemeberMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String groupName = br.readLine();
            groupMap.put(i, groupName);

            int groupSize = Integer.parseInt(br.readLine());
            String[] members = new String[groupSize];
            for (int j = 0; j < groupSize; j++) {
                String memberName = br.readLine();
                memberMap.put(memberName, i);
                members[j] = memberName;
            }
            Arrays.sort(members);
            sortedMemeberMap.put(i, members);
        }

        for (int i = 0; i < M; i++) {
            String quiz = br.readLine();
            int quizType = Integer.parseInt(br.readLine());

            if (quizType == 0) {
                for (Map.Entry<Integer, String> entry : groupMap.entrySet()) {
                    int key = entry.getKey();
                    String value = entry.getValue();
                    if (value.equals(quiz)) {
                        for (String member : sortedMemeberMap.get(key)) {
                            bw.write(member + "\n");
                        }
                    }
                }
            } else if (quizType == 1) {
                String answer = groupMap.get(memberMap.get(quiz));
                bw.write(answer + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
