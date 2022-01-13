import java.util.*;
import java.io.*;

public class boj_3273 {
   /* public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] arr = new int[size];
        for(int i=0; i<size; i++){
            arr[i] = in.nextInt();
        }
        int find_partial_sum = in.nextInt();

        int start = 0;
        int end = arr.length-1;
        int cnt = 0;
        Arrays.sort(arr);

        while(start<end){
            if(arr[start]+arr[end] == find_partial_sum){
                cnt++;
                start++;
                end--;
            }else if(arr[start]+arr[end] > find_partial_sum){
                end--;
            }else if(arr[start]+arr[end] < find_partial_sum){
                start++;
            }
        }
        System.out.print(cnt);
    } */
}
