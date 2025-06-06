package heap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KthSmallElementInSortedMatri {
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p,q)-> p[0]- q[0]
        );
        Set<String> seen = new HashSet<>();
        int[] point = new int[]{mat[0][1], 0, 0};
        pq.offer(point);
        seen.add(getKey(point[1], point[2]));
        k--;
        while (k-- > 0){
            int[] pointer = pq.poll();
            if(pointer[1] < n-1 && seen.add(getKey(pointer[1] + 1, pointer[2]))){
                int[] np = pointer.clone();
                np[1]++;
                np[0] = mat[np[1]][np[2]];
                pq.offer(np);
            }
            if(pointer[2] < m-1 && seen.add(getKey(pointer[1], pointer[2] + 1))){
                int[] np = pointer.clone();
                np[2]++;
                np[0] = mat[np[1]][np[2]];
                pq.offer(np);
            }
        }
        return pq.peek()[0];
    }

    private String getKey(int i, int j) {
        return i + "," + j;
    }
}
