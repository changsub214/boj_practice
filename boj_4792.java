import java.util.*;
import java.io.*;

public class boj_4792 {
    static class Node implements Comparable<Node>{
        int s;
        int d;
        int c;
        public Node(int s, int d, int c){
            this.s = s;
            this.d = d;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c-o.c;
        }
    }
    static int N, M, K, res1, res2;
    static int[] parent;
    static ArrayList<Node> list1;
    static ArrayList<Node> list2;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a!=b){
            if(a<b){
                parent[b] = a;
            }else{
                parent[a] = b;
            }
        }
    }
    public static void kruskal1(){
        parent = new int[N+1];
        res1 = 0;
        for(int i=0; i<N+1; i++)parent[i] = i;
        int len = list1.size();
        for(int i=0; i<len; i++){
            Node node = list1.get(i);
            if(find(node.s) != find(node.d)){
                union(node.s, node.d);
                res1 += node.c;
            }
        }
    }
    public static void kruskal2(){
        parent = new int[N+1];
        res2 = 0;
        for(int i=0; i<N+1; i++)parent[i] = i;
        int len = list2.size();
        for(int i=0; i<len; i++){
            Node node = list2.get(i);
            if(find(node.s) != find(node.d)){
                union(node.s, node.d);
                res2 += node.c;
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            if(N==0 && M==0 && K==0)break;
            list1 = new ArrayList<>();
            list2 = new ArrayList<>();
            while(M-- > 0){
                st = new StringTokenizer(br.readLine());
                String color = st.nextToken();
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                if(color.equals("R")){
                    //list1은 blue 간선을 최소로 뽑아내기 위함
                    //list2는 blue 간선을 최대로 뽑아내기 위함
                    list1.add(new Node(s, d, 0));
                    list2.add(new Node(s, d, 1));
                }else{
                    list1.add(new Node(s, d, 1));
                    list2.add(new Node(s, d, 0));
                }
            }
            //for kruskal
            Collections.sort(list1);
            Collections.sort(list2);
            kruskal1();
            kruskal2();
            if(res1 <= K && K <= (N-1-res2)){
                sb.append(1).append("\n");
            }else{
                sb.append(0).append("\n");
            }
//            System.out.println("oo :" + res1 + " " + (N-1-res2));
        }
        System.out.print(sb);
    }
}
