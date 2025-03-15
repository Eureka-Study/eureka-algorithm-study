// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11564 KB , 시간 : 60 ms

import java.io.*;
import java.util.*;

public class Main {     // 걸그룹 마스터 준석이
    static int N, M;
    static HashMap<String, String[]> girlGroups;    // 그룹이름, 멤버들
    static HashMap<String, String> groupGirls;      // 멤버, 해당 멤버의 그룹이름

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        girlGroups = new HashMap<>();
        groupGirls = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String groupName = br.readLine();
            int groupSize = Integer.parseInt(br.readLine());
            String[] members = new String[groupSize];   // 멤버들 담을 배열 선언

            for (int j = 0; j < groupSize; j++) {
                String girlName = br.readLine();    // 멤버
                members[j] = girlName;              // 배열에 넣기
                groupGirls.put(girlName, groupName);    // 멤버와 해당 그룹이름 추가
            }
            girlGroups.put(groupName, members);     // 그룹, 멤버들 추가
        }

        for (int i = 0; i < M; i++) {
            String qName = br.readLine();           // 1이면 멤버이름, 0이면 그룹이름이 주어진다
            int qType = Integer.parseInt(br.readLine());

            if (qType == 1) {
                bw.write(groupGirls.get(qName));    // 1이면 해당멤버를 groupGirls에서 찾아서 그룹이름 출력
                bw.newLine();
            } else {
                String[] groupMembers = girlGroups.get(qName);  // 0이면 그 그룹의 멤버들을 불러오고
                Arrays.sort(groupMembers);          // 사전순 정렬 해주고
                for (int j = 0; j < groupMembers.length; j++) {
                    bw.write(groupMembers[j]);      // 멤버별 출력
                    bw.newLine();
                }
            }
        }

        bw.close();
        br.close();
    }
}
