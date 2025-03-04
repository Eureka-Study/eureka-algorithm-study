import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 이승우{
    public static void main(String[] args) throws Exception{
        final int max = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());

        int[][] graph = new int[n+1][n+1];

        for(int i = 1; i <= n; i++){
            Arrays.fill(graph[i], max); // int 최대값으로 초기화
            graph[i][i] = 0; // 같은 정점일 경우 0으로 초기화
        }

        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()),
            b = Integer.parseInt(st.nextToken()),
            c = Integer.parseInt(st.nextToken());

            graph[a][b] = Math.min(graph[a][b], c); // 경로가 여러 개일 수 있으니 그 중 가장 작은 값을 저장
        }

        for(int i = 1; i <= n; i++){// 경유지
            for(int j = 1; j <= n; j++){ // 시작 지점
                for(int k = 1; k <= n; k++){ // 도착 지점
                    if(graph[j][i] != max && graph[i][k] != max){ // 경유지를 거쳐 갈 수 있는지 확인
                        graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]); // 갈 수 있다면 최소값을 확인
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                sb.append(graph[i][j] == max ? 0 : graph[i][j]).append(" "); // int의 최대값일 경우 못 가는 것이므로 0, 그 외엔 최소값
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
