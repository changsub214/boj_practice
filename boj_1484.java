import java.util.*;
import java.io.*;

public class boj_1484 {
    public static void main(String[] args)throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int[] arr = new int[100001];
        for(int i=1; i<arr.length; i++){
            arr[i] = i;
        }
        int start = 1; //remember_weight
        int end = 2; //new_weight
        int cnt = 0;
        while(start<end){
            if((arr[end]*arr[end]-arr[start]*arr[start])<n){
                end++;
            }else if((arr[end]*arr[end]-arr[start]*arr[start])>n){
                start++;
            }else{
                sb.append(end).append("\n");
                start++;
                end++;
                cnt++;
            }
        }
        if(cnt==0){
            System.out.print(-1);
        }else{
            System.out.print(sb);
        }
    }
}
