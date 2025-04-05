// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 332872 KB , 시간 : 2876 ms

import java.io.*;
import java.util.*;

public class Main {    // 텀 프로젝트
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashMap<Integer, Integer> map;
        boolean[] visit;
        boolean[] hasTeam;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            visit = new boolean[N+1];
            hasTeam = new boolean[N+1];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map.put(j, Integer.parseInt(st.nextToken()));
            }

            Deque<Integer> stack = new ArrayDeque<>();
            HashSet<Integer> set = new HashSet<>();     // 한 시퀀스에서 이미 stack에 들어갔는지 체크용

            for (int j = 1; j <= N; j++) {
                if (!visit[j]) stack.push(j);

                while (!stack.isEmpty()) {
                    int key = stack.peek();
                    int value = map.get(key);

                    if (visit[value]) {     // 이미 방문한 적 있는 숫자면
                        while (!stack.isEmpty()) {
                            visit[stack.pop()] = true;    // 그냥 stack에 있는거 싹다 visit 처리만
                        }
                        break;

                    } else if (key == value) {  // key값과 value값이 같으면
                        hasTeam[key] = true;    // key에 해당하는 숫자는 team 있다고 처리

                        while (!stack.isEmpty()) {
                            visit[stack.pop()] = true;    // 나머지 stack에 있는거는 싹다 visit 처리
                        }
                        break;

                    } else if (set.contains(value)) {   // 이 시퀀스에 value에 해당하는 숫자 있으면
                        int idx = 0;    //  stack 순회할때 key값 확인용 인덱스
                        while (idx != value) {  // 인덱스와 value에 해당하는 숫자가 같아질때까지
                            idx = stack.pop();
                            hasTeam[idx] = true;
                            visit[idx] = true;
                        }

                        while (!stack.isEmpty()) {
                            visit[stack.pop()] = true;    // 나머지 stack에 있는거는 싹다 visit 처리
                        }
                        break;

                    } else {
                        set.add(key);
                        stack.push(value);
                    }
                }
            }

            int ans = 0;
            for (int j = 1; j <= N; j++) {
                if (!hasTeam[j]) ans++;
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
