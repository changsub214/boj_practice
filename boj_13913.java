import java.util.*;
import java.io.*;

public class boj_13913 {
    static class Node{
        int x;
        int sec;
        public Node(int x, int sec){
            this.x = x;
            this.sec = sec;
        }
    }
    static int[] arr;
    static int[] cal;
    static boolean[] visit;
    public static void bfs(int s, int e){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s, 0)); //begin
        arr[s] = -5;
        cal[s] = 0;
        visit[s] = true;
        while(!q.isEmpty()){
            Node node = q.poll();
            if(visit[node.x] && node.x == e) return;
            int nx = node.x;
            for(int i=0; i<3; i++){
                if(i ==0){ // move +1
                     nx = node.x + 1;
                }else if(i ==1){ // move -1
                     nx = node.x - 1;
                }else{ // jump 2*X
                     nx = 2 * node.x;
                }

                if(-1<nx && nx<100001){
                    if(!visit[nx] && cal[nx]==Integer.MAX_VALUE){
                        visit[nx] = true;
                        cal[nx] = cal[node.x] +1;
                        arr[nx] = node.x;
                        q.add(new Node(nx, cal[nx]));
                    }
                }

            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        arr = new int[100001];
        cal = new int[100001];
        visit = new boolean[100001];
        Arrays.fill(cal, Integer.MAX_VALUE);
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        bfs(s,e);
        System.out.println(cal[e]);
        Stack<Integer> stack = new Stack<>();
        while(true){
            stack.add(e);
            e = arr[e];
            if(e == -5)break;
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
