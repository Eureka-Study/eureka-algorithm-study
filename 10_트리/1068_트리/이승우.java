// 언어 : JAVA , (성공/실패) : 1/1 , 메모리 : 14268 KB , 시간 : 104 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 트리 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] list = new ArrayList[N];
        boolean[] remove = new boolean[N];

        for(int i = 0; i < N; i++){
            list[i] = new ArrayList<>();
        }

        String[] parent = br.readLine().split(" ");

        for(int i = 0; i < N; i++){
            if(!parent[i].equals("-1")){
                list[Integer.parseInt(parent[i])].add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(Integer.parseInt(br.readLine()));
        remove[queue.peek()] = true;
        while (!queue.isEmpty()) {
            int removeNode = queue.poll(); //삭제된 노드

            for(int i : list[removeNode]){ // 삭제된 노드의 하위 노드가 있는지 확인
                if(!remove[i]){ // 살아있는지 확인
                    remove[i] = true; // 삭제 처리
                    queue.offer(i); // 삭제된 노드에 넣기
                }
            }
        }

        int count = 0;

        for(int i = 0; i < N; i++){
            int c = 0;
            for(int j : list[i]){
                if(!remove[j]) c++; //살아있는 자식 노드 체크
            }
            if(!remove[i] && c == 0){ // 현 노드가 살아있고 자식 노드가 0개면 리프 노드이므로 카운트
                count++;
            }
        }

        System.out.println(count);
    }
}
