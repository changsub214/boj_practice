import java.util.*;
import java.io.*;

public class boj_15758 {
    static class Node implements Comparable<Node>{
        int v;
        public Node(int v){
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }
    static int N, M;
    static ArrayList<Node>[] list;
    static ArrayList<Integer> result;
    public static int binary_search(){
        //첫번째 규칙부터 mid 번째 규칙까지 법칙을 만족하는지 조사
        int left = 0;
        int right = M-1; //you must consider list array is starting at 0
        while(left < right){
            int mid = (left+right+1)/2;
            if(check(mid)){
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        check(left); //이 법칙까지 잘되는지 한번 더 확인
        return left;
    }
    public static boolean check(int x){
        ArrayList<Node>[] list2 = new ArrayList[N+1];
        int[] connect_edge = new int[N+1];
        result.clear(); //법칙 내 소들을 정렬한 것을 다시 담기 위함
        for(int i=0; i<N+1; i++){
            list2[i] = new ArrayList<>();
        }
        for(int i=0; i< x; i++){
            for(int j=1; j<list[i].size(); j++){
                list2[list[i].get(j-1).v].add(new Node(list[i].get(j).v));
                connect_edge[list[i].get(j).v]++;
            }
        }

        //topological sort
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i=1; i<N+1; i++){
            if(connect_edge[i] == 0){
                pq.offer(new Node(i));
            }
        }
        if(pq.isEmpty()){ //cycle so can't comply rules
            return false;
        }
        int idx = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            result.add(node.v);
            idx++;

            for(Node vertex : list2[node.v]){
                connect_edge[vertex.v]--;
                if(connect_edge[vertex.v] == 0){
                    pq.add(new Node(vertex.v));
                }
            }
        }

        if(idx != N)return false;

        return true;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        result = new ArrayList<>();
        for(int i=0; i<N; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            for(int j=0; j<v; j++){
                int vertex = Integer.parseInt(st.nextToken());
                list[i].add(new Node(vertex));
            }
        }
        binary_search();

        for(int i=0; i<result.size(); i++){
            System.out.print(result.get(i) + " ");
        }

    }
}
