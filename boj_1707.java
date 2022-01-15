import java.util.*;
import java.io.*;

public class boj_1707 {
    //Bipartite Graph : the graph must be filled same color on same level
    static ArrayList<ArrayList<Integer>> arrayLists;
    static int R = 1;
    static int B = -1;
    static int[] color;
    static boolean judge;
    public static void dfs(int s, int c){
        color[s] = c;

        for(int adjV : arrayLists.get(s)){
            if(color[adjV] == c){
                judge = false;
                return;
            }
            if(color[adjV] == 0){
                dfs(adjV, -1*c);
            }
        }
    }
    public static void bfs(int i, int c){
        Queue<Integer> que = new LinkedList<>();
        que.add(i);
        color[i] = c;
        while(!que.isEmpty() && judge){
            int v = que.poll();

            for(int adjv : arrayLists.get(v)){
                if(color[adjv] == 0){
                    que.add(adjv);
                    color[adjv] = color[v] * -1; //input other color
                }else if(color[adjv]+color[v] != 0){
                    //not bipartite graph
                    judge = false;
                    return;
                }
            }
        }
    }
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        while(T-->0){
            int V = in.nextInt();
            int E = in.nextInt();

            arrayLists = new ArrayList<>();
            color = new int[V+1];
            judge = true;

            for(int i=0; i<V+1; i++){
                arrayLists.add(new ArrayList<Integer>());
                color[i] = 0;
            }
            //two-way
            while(E-->0){
                int v_1 = in.nextInt();
                int v_2 = in.nextInt();

                arrayLists.get(v_1).add(v_2);
                arrayLists.get(v_2).add(v_1);
            }

            //non-connection graph must be searched all path
            for(int i=1; i<V+1; i++){
                if(!judge)break;
                if(color[i]==0){
                    dfs(i, R);
                   // bfs(i,R);
                }
            }
            if(judge){
                sb.append("YES").append("\n");
            }else{
                sb.append("NO").append("\n");
            }
        }
        System.out.print(sb);
    }
}
