import java.util.*;
import java.io.*;

public class boj_2150 {
    static int V, E, group_num = 0;
    static boolean[] visit;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] rever_graph;
    static ArrayList<Integer>[] result;
    static Stack<Integer> stack = new Stack<>();
    static TreeMap<Integer, Integer> map = new TreeMap<>();
    public static void dfs(int start){
        visit[start] = true;
        int len = graph[start].size();
        for(int i=0; i<len; i++){
            int nw = graph[start].get(i);
            if(!visit[nw]){
                dfs(nw);
            }
        }
        stack.add(start);
    }
    public static void dfs2(int start){
        visit[start] = true;
        result[group_num].add(start);
        int len = rever_graph[start].size();
        for(int i=0; i<len; i++){
            int nw = rever_graph[start].get(i);
            if(!visit[nw]){
                dfs2(nw);
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visit = new boolean[V+1];
        graph = new ArrayList[V+1];
        rever_graph = new ArrayList[V+1];
        result = new ArrayList[V+1];
        for(int i=0; i<V+1; i++){
            graph[i] = new ArrayList<>();
            rever_graph[i] = new ArrayList<>();
            result[i] = new ArrayList<>();
        }
        while(E -- > 0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[s].add(d);
            rever_graph[d].add(s);
        }
        for(int i=1; i<V+1; i++){
            if(!visit[i]){
                dfs(i);
            }
        }
        Arrays.fill(visit,false); //역방향 그래프에서 한번 더 dfs 하기 위함
        while(!stack.isEmpty()){
            int start = stack.pop();
            if(visit[start])continue;
            dfs2(start);
            group_num++;
        }
        sb.append(group_num).append("\n");
        for(int i=0; i<group_num; i++){
            Collections.sort(result[i]);
            map.put(result[i].get(0),i);
        }
        map.keySet().forEach(key -> {
            int value = map.get(key);
            int len = result[value].size();
            for(int i=0; i<len; i++){
                int v = result[value].get(i);
                sb.append(v + " ");
            }
            sb.append(-1 + "\n");
        });
        System.out.print(sb);
    }
}
