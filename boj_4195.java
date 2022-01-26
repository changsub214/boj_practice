import java.util.*;
import java.io.*;

public class boj_4195 {
    static int T, F;
    static int[] parent;
    static int[] count;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y){
                parent[y] = x;
                count[x] += count[y];
            }
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while(T-- >0) {
            st = new StringTokenizer(br.readLine());
            F = Integer.parseInt(st.nextToken());
            parent = new int[2 *F];
            count = new int[2 *F];
            Arrays.fill(count,1);
            HashMap<String, Integer> hm = new HashMap<>();
            int num = 0;
            while (F-- > 0) {
                st = new StringTokenizer(br.readLine());
                String temp1 = st.nextToken();
                String temp2 = st.nextToken();

                if(!hm.containsKey(temp1)){
                    parent[num] = num;
                    hm.put(temp1,num++);
                }
                if(!hm.containsKey(temp2)){
                    parent[num] = num;
                    hm.put(temp2,num++);
                }
                union(hm.get(temp1),hm.get(temp2));
                sb.append(count[find(hm.get(temp2))]).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }
}
