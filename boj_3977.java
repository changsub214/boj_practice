import java.util.*;
import java.io.*;

public class boj_3977 {
    static int T, N, M;
    static int scc_num, num;
    static int[] order;
    static int[] scc_arr;
    static int[] indegree;
    static boolean[] visit;
    static ArrayList<Integer>[] list;
    static Stack<Integer> stack = new Stack<>();
    public static int dfs(int start){
        order[start] = ++num;
        stack.add(start);
        int root = order[start];
        int len = list[start].size();
        for(int i=0; i<len; i++){
            int nw = list[start].get(i);
            if(order[nw] == 0){
                root = Math.min(root, dfs(nw));
            }else if(!visit[nw]){
                root = Math.min(root, order[nw]);
            }
        }
        if(root == order[start]){
            while(true){
                int t = stack.pop();
                scc_arr[t] = scc_num;
                visit[t] = true;
                if(t == start)break; //scc 형성 완료
            }
            scc_num++;
        }
        return root;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        int loop = T;
        while(loop-- > 0){
            if(loop != T-1){
                //아래는 입력처리를 위해 버리는 용도
                String trash = br.readLine();
            }
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            visit = new boolean[N+1];
            order = new int[N+1];
            scc_arr = new int[N+1];
            list = new ArrayList[N+1];
            for(int i=0; i<N+1; i++){
                list[i] = new ArrayList<>();
            }
            scc_num = 0 ;
            num = 0;
            while(M-- > 0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                list[s].add(d);
            }
            for(int i=0; i<N; i++){
                if(order[i] == 0){
                    dfs(i);
                }
            }
            indegree = new int[scc_num];
            for(int i=0; i<N; i++){
                int len = list[i].size();
                for(int j=0; j<len; j++){
                    int nw = list[i].get(j);
                    if(scc_arr[nw] != scc_arr[i]){
                        indegree[scc_arr[nw]]++;
                    }
                }
            }
            int cnt = 0;
            int tag = 0;
            for(int i=0; i<scc_num; i++){
                if(indegree[i] == 0){
                    tag = i;
                    cnt++;
                }
            }
            if(cnt>1){ //indegree가 0이 1개 이상이면 모든 정점을 방문할 수가 없음
                sb.append("Confused\n");
            }else{
                for(int i=0; i<N; i++){
                    if(scc_arr[i] == tag){
                        sb.append(i).append("\n");
                    }
                }
            }
            sb.append("\n");

        }
        System.out.print(sb);
    }
}
