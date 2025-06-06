package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumNumberOfRefuelingStops {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Arrays.sort(stations, Comparator.comparing(p->p[0]));//o(nlogn)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p,q)->Integer.compare(q[1], p[1])
        );
        int count = 0;
        for(int[] station : stations){//o(n+nlogn)
            if(startFuel>=target) return count;
            if(startFuel >= station[0] && startFuel + station[1] >= target) return count + 1;
            if(startFuel>=station[0]) pq.offer(station);
            else{
                while (!pq.isEmpty() && startFuel < station[0]){
                    int[] st = pq.poll();
                    startFuel+=st[1];
                    count++;
                }
                if(startFuel < station[0]) return -1;
                else {
                    pq.offer(station);
                }
            }
        }
        //o(nlogn)
        while (!pq.isEmpty()){
            if (startFuel >= target) break;
            startFuel+=pq.poll()[1];
            count++;
        }
        if(startFuel<target) return -1;
        return count;
    }
}

