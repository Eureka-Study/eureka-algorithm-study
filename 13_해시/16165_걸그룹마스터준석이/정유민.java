// 언어 : JAVA , (성공/실패) : 1/0 , 메모리 : 11640 KB , 시간 : 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        HashMap<String, TreeSet<String>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String groupName = br.readLine();
            TreeSet<String> members = new TreeSet<>();
            int memberCnt = Integer.parseInt(br.readLine());
            for (int j = 0; j < memberCnt; j++) {
                members.add(br.readLine());
            }
            map.put(groupName, members);
        }

        for (int i = 0; i < m; i++) {
            String ques = br.readLine();
            int option = Integer.parseInt(br.readLine());

            if (option == 0) {
                TreeSet<String> members = map.get(ques);
                for (String member : members) {
                    System.out.println(member);
                }
            }else{
                for (String key : map.keySet()) {
                    if(map.get(key).contains(ques))
                        System.out.println(key);
                }
            }
        }
    }
}
