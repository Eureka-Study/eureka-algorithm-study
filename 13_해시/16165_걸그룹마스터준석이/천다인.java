//언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14076 KB , 시간 : 104 ms
import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        HashMap<String, List<String>> groupToMembers = new HashMap<>(); // 팀 이름 -> 멤버 리스트 매핑
        HashMap<String, String> memberToGroup = new HashMap<>(); // 멤버 이름 -> 팀 이름 매핑

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String groupName = br.readLine();
            int memberCount = Integer.parseInt(br.readLine());
            List<String> members = new ArrayList<>();

            for (int j = 0; j < memberCount; j++) {
                String memberName = br.readLine();
                members.add(memberName);
                memberToGroup.put(memberName, groupName); // 멤버 -> 그룹 매핑 저장
            }

            Collections.sort(members); // 멤버들을 사전순으로 정렬
            groupToMembers.put(groupName, members); // 팀 -> 멤버 매핑 저장
        }

        for (int i = 0; i < m; i++) {
            String query = br.readLine();
            int type = Integer.parseInt(br.readLine()); // 퀴즈 유형 (0: 팀명 -> 멤버, 1: 멤버 -> 팀명)

            if (type == 0) { // 팀 이름이 주어진 경우 -> 해당 팀의 멤버 출력
                List<String> members = groupToMembers.get(query);
                for (String member : members) {
                    sb.append(member).append("\n");
                }
            } else { // 멤버 이름이 주어진 경우 -> 속한 팀 이름 출력
                sb.append(memberToGroup.get(query)).append("\n");
            }
        }
        System.out.print(sb);
    }
}