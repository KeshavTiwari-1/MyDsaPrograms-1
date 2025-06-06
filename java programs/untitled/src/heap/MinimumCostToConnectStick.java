package heap;

import java.util.*;

public class MinimumCostToConnectStick {
//    // heap
//    public static int minCost(int[] arr) {
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        for(int a : arr) pq.offer(a);
//        int cost = 0;
//        while (pq.size() > 1){
//            int x = pq.poll() + pq.poll();
//            cost += x;
//            pq.offer(x);
//        }
//        return cost;
//    }

    // array solution
    public static int minCost(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        List<Integer> nr = new ArrayList<>();
        int i = 0;
        int totalCost = 0;
        while(n-i + nr.size() > 1){
            int cost = 0;
            int k = 2;
            while (k-- > 0){
                if(i<n){
                    if(nr.size() > 0){
                        if(arr[i] <= nr.getFirst()){
                            cost+=arr[i];
                            i++;
                        }
                        else {
                            cost += nr.removeFirst();
                        }
                    }
                    else {
                        cost+=arr[i];
                        i++;
                    }
                }
                else{
                    cost += nr.removeFirst();
                }
            }
            totalCost+=cost;
            nr.addLast(cost);
        }
        return totalCost;
    }

}
