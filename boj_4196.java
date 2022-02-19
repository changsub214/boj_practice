import java.util.*;
import java.io.*;

public class boj_4196 {
    static int T, N, M, res=0;
    static int[] parent;
    static int[] scc_num;
    static boolean[] visit;
    static ArrayList<Integer>[] list;
    static ArrayList<Integer>[] revers_list;
    static Stack<Integer> stack = new Stack<>();
    public static void dfs(int start){
        visit[start] = true;
        int len = list[start].size();
        for(int i=0; i<len; i++){
            int nw = list[start].get(i);
            if(!visit[nw]){
                dfs(nw);
            }
        }
        stack.add(start);
    }
    public static void dfs2(int start, int num){
        visit[start] = true;
        scc_num[start] = num;
        int len = revers_list[start].size();
        for(int i=0; i<len; i++){
            int nw = revers_list[start].get(i);
            if(visit[nw]){
                if(scc_num[nw] != num){
                    parent[num]++;
                }
                continue;
            }
            dfs2(nw, num);
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int group_num = 1;
            res = 0;
            parent = new int[N+1];
            scc_num = new int[N+1];
            visit = new boolean[N+1];
            list = new ArrayList[N+1];
            revers_list = new ArrayList[N+1];
            for(int i=0; i<N+1; i++){
                list[i] = new ArrayList<>();
                revers_list[i] = new ArrayList<>();
            }
            while(M-->0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                list[s].add(d);
                revers_list[d].add(s);
            }
            for(int i=1; i<N+1; i++){
                if(!visit[i]){
                    dfs(i);
                }
            }
            Arrays.fill(visit,false);
            while(!stack.isEmpty()){
                int nw = stack.pop();
                if(visit[nw])continue;
                dfs2(nw,group_num);
                group_num++;
            }
            int cnt = 0;
            for(int i=1; i<group_num; i++){
                if(parent[i] == 0){
                    cnt++;
                }
            }
            if(cnt == 0)cnt++;
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}
