import java.util.*;
import java.io.*;

public class boj_1766 {
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
        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        for(int i=1; i<N+1; i++){
            if(edge_num[i]==0){
                q1.add(i);
            }
        }
        while(!q1.isEmpty()){
            int n = q1.poll();
            result.add(n);
            int len = list[n].size();
            for(int i=0; i<len; i++){
                int v = list[n].get(i).vertex;
                edge_num[v]--;
                if(edge_num[v] == 0){
                    q1.add(v);
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
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        while(M-- >0){
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
