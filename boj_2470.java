import java.util.*;
import java.io.*;

public class boj_2470 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        String[] r = br.readLine().split(" ");
        for(int i=0; i<T; i++){
            arr[i] = Integer.parseInt(r[i]);
        }
        Arrays.sort(arr);

        int start = 0;
        int end = arr.length-1;
        int temp_s = arr[0];
        int temp_e = arr[arr.length-1];
        int difference = Integer.MAX_VALUE;
        int temp;
        while(start<end){
            int partial_sum = arr[start] + arr[end];
            temp = Math.abs(partial_sum);
            if(temp<difference){
                difference = temp;
                temp_s = arr[start];
                temp_e = arr[end];
            }
            if(Math.abs(arr[start]+arr[end-1]) < Math.abs(arr[start+1]+arr[end])){
                end--;
            }else{
                start++;
            }
        }
        System.out.print(temp_s + " " + temp_e);

        /* time over
        import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int[] arr = new int[T];
        for(int i=0; i<T; i++){
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);

        int start = 0;
        int end = arr.length-1;
        int temp_s = arr[0];
        int temp_e = arr[arr.length-1];
        int difference = Integer.MAX_VALUE;
        int temp;
        while(start<end){
            int partial_sum = arr[start] + arr[end];
            temp = Math.abs(partial_sum);
            if(temp<difference){
                difference = temp;
                temp_s = arr[start];
                temp_e = arr[end];
            }
            if(partial_sum <= 0){
                start++;
            }else if(partial_sum > 0){
                end--;
            }
        }
        System.out.print(temp_s + " " + temp_e);
    }
}


         */
    }
}
