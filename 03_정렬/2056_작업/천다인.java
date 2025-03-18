//언어 : JAVA , (성공/실패) : 0/1 , 메모리 :  KB , 시간 :  ms
import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N + 1];
        int[] inDegree = new int[N + 1];
        List<Integer>[] adjList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            for (int j = 0; j < count; j++) {
                int prerequisite = Integer.parseInt(st.nextToken());
                adjList[prerequisite].add(i);
                inDegree[i]++;
            }
        }

        System.out.println(getMinimumCompletionTime(N, time, inDegree, adjList));
    }

    static int getMinimumCompletionTime(int N, int[] time, int[] inDegree, List<Integer>[] adjList) {
        Queue<Integer> queue = new LinkedList<>();
        int[] completionTime = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                completionTime[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : adjList[current]) {
                completionTime[next] = Math.max(completionTime[next], completionTime[current] + time[next]);
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, completionTime[i]);
        }
        return maxTime;
    }
}