package heap;

import java.util.*;
//https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/description/
// TODO : Binary Search Solution
public class KthSmallestSum {

    public int kthSmallest(int[][] mat, int k) {
        // storing sum at zero and then pointers of row staring from 1 indexed;
        int n = mat.length;
        int m = Math.min(mat[0].length, k);
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p,q) -> p[n] - q[n]
        );
        Set<String> seen = new HashSet<>();
        int[] pointer = new int[n + 1];
        for(int i = 0; i < n; i++){
            pointer[n]+=mat[i][0];
        }
        pq.offer(pointer);
        seen.add(Arrays.toString(pointer));
        k--;
        while (k-- >0){
            int[] p = pq.poll();
            for(int i = 0; i<n; i++){
                if(p[i] >= m-1) continue;
                int[] newp = p.clone();
                newp[n] -= mat[i][newp[i]];
                newp[i]++;
                newp[n] += mat[i][newp[i]];
                if(seen.add(Arrays.toString(newp))){
                    pq.offer(newp);
                }
            }
        }
        return pq.peek()[n];
    }


//    // o(row * k * min(k,collum)*logk)
//        public int kthSmallest(int[][] mat, int k) {
//            int col = Math.min(k, mat[0].length);
//            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
//            pq.offer(0);
//            for(int[] row : mat){
//                PriorityQueue<Integer> nextPq = new PriorityQueue<>(Collections.reverseOrder());
//                for(int i : pq){
//                    for(int j = 0; j < col; j++){
//                        nextPq.offer(i + row[j]);
//                        if(nextPq.size() > k) nextPq.poll();
//                    }
//                }
//                pq = nextPq;
//            }
//            return pq.peek();
//        }

}
