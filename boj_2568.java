import java.util.*;
import java.io.*;

public class boj_2568 {
    static class Node implements Comparable<Node>{
        int left;
        int right;
        public Node(int left, int right){
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.left - o.left;
        }
    }
    static Node[] arr;
    static Node[] tr;
    static Integer[] dp;
    static boolean[] visit;
    static int line;
    public static int LIS(int n){
        int cnt = 0;
        for(int i=1; i<n; i++){
            if(arr[i].right > dp[cnt]){
                dp[++cnt] = arr[i].right;
                tr[i] = new Node(cnt, arr[i].left);
            }else{
                int len = binarysearch(0, cnt, arr[i].right);
                dp[len] = arr[i].right;
                tr[i] = new Node(len, arr[i].left);
            }
        }
        return cnt;
    }
    public static int binarysearch(int left, int right, int value){
        int mid = 0;
        while(left<right){
            mid = (left+right)/2;
            if(dp[mid] < value){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return right;
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        line = Integer.parseInt(st.nextToken());
        arr = new Node[line];
        dp = new Integer[line];
        visit = new boolean[500001];
        tr = new Node[line];
        for(int i=0; i<line; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[i] = new Node(n1,n2);
            visit[n1] = true;
        }
        Arrays.sort(arr);
        dp[0] = arr[0].right;
        tr[0] = new Node(0, arr[0].left);
        int res = LIS(line);
        int r = res+1;
        System.out.println(line - r);

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=line-1; i>=0; i--){
            if(tr[i].left == res){
                list.add(tr[i].right);
                res--;
            }
        }
        for(int i=0; i<list.size(); i++){
            visit[list.get(i)] = false;
        }
        for(int i=0; i< visit.length; i++){
            if(visit[i])System.out.println(i);
        }
    }
}
