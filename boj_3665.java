import java.util.*;
import java.io.*;

public class boj_3665 {
    static int T, N, M;
    static int[] edge_num;
    static ArrayList<Integer>[] list;
    static ArrayList<Integer> result;
    public static int topological_sort(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<N+1; i++){
            if(edge_num[i] == 0){
                q.add(i);
            }
        }
        if(q.size()>=2)return -1;
        while(!q.isEmpty()){
            int node = q.poll();
            result.add(node);
            int len = list[node].size();
            for(int i=0; i<len; i++){
                int v = list[node].get(i);
                edge_num[v]--;
                if(edge_num[v]==0){
                    q.add(v);
                }
            }
        }
        if(result.size() != N)return -2; //impossible
        return 0;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-- >0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            edge_num = new int[N+1];
            list = new ArrayList[N+1];
            result = new ArrayList<>();
            for(int i=0; i<N+1; i++){
                list[i] = new ArrayList<>();
            }
            String[] temp = br.readLine().split(" ");
            for(int i=0; i<N-1; i++){
                int s = Integer.parseInt(temp[i]);
                for(int j=i+1; j<N; j++){
                    int d = Integer.parseInt(temp[j]);
                    list[s].add(d);
                    edge_num[d]++;
                }
            }

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            while(M-- > 0){
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                if(list[n1].contains(n2)){
                    list[n1].remove((Integer)n2);
                    list[n2].add(n1);
                    edge_num[n1]++;
                    edge_num[n2]--;
                } else {
                    list[n2].remove((Integer)n1);
                    list[n1].add(n2);
                    edge_num[n2]++;
                    edge_num[n1]--;
                }

            }
            int r = topological_sort();
            if(r == -1){
                sb.append("?").append("\n");
            }else if(r == -2){
                sb.append("IMPOSSIBLE").append("\n");
            }else{
                for(int i=0; i<result.size(); i++){
                    sb.append(result.get(i) + " ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
