import java.util.*;
import java.io.*;

public class boj_16724 {
    static int N,M;
    static char[] arr;
    static int[] parent;
    public static int find(int x){
        if(parent[x] == x){
            return x;
        }else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x!=y){
            if(x<y)parent[y]=x;
            else parent[x]=y;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N*M+1];
        arr = new char[N*M+1];
        for(int i=0; i< parent.length; i++)parent[i] = i;
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            for(int j=1; j<M+1; j++){
                int idx = (i-1)*M + j;
                arr[idx] = temp.charAt(j-1);
                if(arr[idx] == 'D'){
                    union(idx,idx+M);
                }else if(arr[idx] == 'U'){
                    union(idx,idx-M);
                }else if(arr[idx] == 'L'){
                    union(idx,idx-1);
                }else{ //Right
                    union(idx,idx+1);
                }
            }
        }
        HashSet<Integer> parent_num = new HashSet<>();
        for(int i=1; i< parent.length; i++){
            parent_num.add(find(parent[i]));
        }
        System.out.print(parent_num.size());
    }
}
