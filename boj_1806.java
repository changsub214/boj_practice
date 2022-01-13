import java.util.*;
import java.io.*;

public class boj_1806 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int S = in.nextInt();
        int[] arr = new int[N+1];
        for(int i=0; i<N; i++){
            arr[i] = in.nextInt();
        }
        int start =0;
        int end =0;
        int len =Integer.MAX_VALUE;
        int partial_sum = 0;
        /*
        * 어떤 부분합이 찾고자하는 부분합보다 크더라도 찾고자하는 부분합의 최소길이를 찾는 것이기 때문에
        * 어떤 부분합의 길이를 가져와도 괜찮으며(왜냐하면 어차피 찾고자하는 부분합보단 뭔갈 더 더해야하는데
        * 이는 길이가 더 길어진다는 뜻이니까)
        * 그 전에 찾고자하는 부분합중에 하나를 찾았다면 그 길이가 최소길이로
        * 보유중일 것이기 때문에 최소길이가 바뀌지 않음 결국에 최소길이가 바뀌려면 찾고자하는 부분합 중에
        * 최소길이가 되면 바뀐다는 발상을 해야만 풀 수 있음
        * */
        while(start<=N && end<=N){
            if(partial_sum >= S && len > end-start){
                int temp_len = end-start;
                len = Math.min(len, temp_len);
            }
            if(partial_sum>=S){
                partial_sum -= arr[start++];
            }else if(partial_sum<S){
                partial_sum += arr[end++];
            }
        }
        if(len == Integer.MAX_VALUE){
            System.out.print(0);
        }else{
            System.out.print(len);
        }
    }
}
