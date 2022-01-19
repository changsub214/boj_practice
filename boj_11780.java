import java.util.*;
import java.io.*;

public class boj_11780 {
    static int V, E, INF=987654321;
    static int[][] floyd;
    static int[][] path;
    static StringBuilder sb = new StringBuilder();
    public static void floyd_warshall(){
        for(int k=1; k<V+1; k++){
            for(int i=1; i<V+1; i++){
                for(int j=1; j<V+1; j++){
                    int temp = floyd[i][j];
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                    if(temp!=floyd[i][j]){
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
    }
    public static void print(){
        for(int i=1; i<V+1; i++){
            for(int j=1; j<V+1; j++){
                if(floyd[i][j] >= INF){
                    sb.append(0 + " ");
                    continue;
                }
                sb.append(floyd[i][j] + " ");
            }
            sb.append("\n");
        }
        for(int i=1; i<V+1; i++){
            for(int j=1; j<V+1; j++){
                if(path[i][j] == -1){
                    sb.append(0).append("\n");
                    continue;
                }
                Stack<Integer> stack = new Stack<>();
                int v = j;
                stack.add(j);
                while(i != path[i][v]){
                    v = path[i][v];
                    stack.add(v);
                }
                sb.append(stack.size()+1 + " ");

                sb.append(i + " ");
                while(!stack.isEmpty()){
                    sb.append(stack.pop() + " ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        floyd = new int[V+1][V+1];
        path = new int[V+1][V+1];
        for(int i=1; i<V+1; i++){ // initialization
            for(int j=1; j<V+1; j++){
                path[i][j] = -1;
                if(i==j){
                    floyd[i][j] = 0;
                    continue;
                }
                floyd[i][j] = INF;
            }
        }
        while(E-- >0){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            floyd[S][D] = Math.min(C, floyd[S][D]);
            path[S][D] = S;
        }
        floyd_warshall();
        print();
        br.close();
    }
}
