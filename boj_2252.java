import java.util.*;
import java.io.*;

public class boj_2252 {
    static class Node{
        int vertex;
        public Node(int vertex){
            this.vertex = vertex;
        }
    }
    static int N, M;
    static int[] edge_num;
    static ArrayList<Node>[] list;
    static Queue<Integer> result = new LinkedList<>();
    public static void topological_sort(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<N+1; i++){
            if(edge_num[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int n = q.poll();
            result.add(n);
            int len = list[n].size();
            for(int i=0; i<len; i++){
                edge_num[list[n].get(i).vertex]--;
                if(edge_num[list[n].get(i).vertex] == 0){
                    q.add(list[n].get(i).vertex);
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
        list = new ArrayList[N+1];
        edge_num = new int[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            edge_num[d]++;
            list[s].add(new Node(d));
        }
        topological_sort();
        while(!result.isEmpty()){
            System.out.print(result.poll() + " ");
        }
    }
}
