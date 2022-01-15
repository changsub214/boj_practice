import java.util.*;
import java.io.*;

public class boj_1655 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> min_pq = new PriorityQueue<>(); //minHeap
        PriorityQueue<Integer> max_pq = new PriorityQueue<>(Collections.reverseOrder()); //maxHeap

        int T = Integer.parseInt(br.readLine());

        while(T-- >0){
            int n = Integer.parseInt(br.readLine());
            if(max_pq.size() == min_pq.size()){
                max_pq.add(n);
            }else{
                min_pq.add(n);
            }
            if(!max_pq.isEmpty() && !min_pq.isEmpty()){
                if(max_pq.peek()>min_pq.peek()){
                    int temp = max_pq.poll();
                    max_pq.add(min_pq.poll());
                    min_pq.add(temp);
                }
            }

            /* time over
            if(max_pq.size() == min_pq.size()){
                if(max_pq.size() ==0 && min_pq.size() == 0){
                    max_pq.add(n);
                }else{
                    if(n>min_pq.peek()){
                        int temp = n;
                        n = min_pq.poll();
                        min_pq.add(temp);
                    }
                    max_pq.add(n);
                }
            }else if(max_pq.size() != min_pq.size()){
                if(n< max_pq.peek()){
                    int temp = n;
                    n = max_pq.poll();
                    max_pq.add(temp);
                }
                min_pq.add(n);
            } */
            sb.append(max_pq.peek()).append('\n');
        }
        System.out.print(sb);
    }
}
