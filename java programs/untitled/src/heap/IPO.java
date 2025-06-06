package heap;

import java.util.PriorityQueue;

public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        PriorityQueue<Integer> notEligible = new PriorityQueue<>(
                (p,q) ->  Integer.compare(capital[p], capital[q])
        );
        PriorityQueue<Integer> eligible = new PriorityQueue<>(
                (p,q)-> Integer.compare(profits[q], profits[p])
        );
        for(int i = 0; i < n; i++){
            if(capital[i]<=w) eligible.offer(i);
            else notEligible.offer(i);
        }
        while (k-- > 0 && !eligible.isEmpty()){
            int index = eligible.poll();
            w = w + profits[index];
            while (!notEligible.isEmpty() && capital[notEligible.peek()] <= w){
                eligible.offer(notEligible.poll());
            }
        }
        return w;
    }
}
