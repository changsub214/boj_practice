import java.util.*;
import java.io.*;

public class boj_2623 {
    static class Node{
        int vertex;
        public Node(int vertex){
            this.vertex = vertex;
        }
    }
    static int N, M;
    static int[] edge_num;
    static ArrayList<Node>[] list;
    static ArrayList<Integer> result;
    public static void topological_sort(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<N+1; i++){
            if(edge_num[i] == 0)q.add(i);
        }
        while(!q.isEmpty()){
            int node = q.poll();
            result.add(node);
            int len = list[node].size();
            for(int i=0; i<len; i++){
                int v = list[node].get(i).vertex;
                edge_num[v]--;
                if(edge_num[v]==0){
                    q.add(v);
                }
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edge_num = new int[N+1];
        list = new ArrayList[N+1];
        result = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int loop = Integer.parseInt(st.nextToken());
            int[] temp = new int[loop];
            for(int i=0; i<loop; i++){
                temp[i] = Integer.parseInt(st.nextToken());
            }
            for(int j=0; j<temp.length-1; j++){
                list[temp[j]].add(new Node(temp[j+1]));
                edge_num[temp[j+1]]++;
            }
        }
        topological_sort();
        if(result.size() == N){
            for(int i=0; i<result.size(); i++){
                System.out.println(result.get(i));
            }
        }else{
            System.out.print(0);
        }
    }
}
