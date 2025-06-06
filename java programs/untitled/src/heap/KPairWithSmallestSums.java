package heap;

import java.util.*;

public class KPairWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int size) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(
                (p,q) -> nums1[p[0]] + nums2[p[1]] - nums1[q[0]] -nums2[q[1]]
        );
        Set<String> seen = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        pq.offer(new Integer[]{0,0});
        seen.add(Arrays.toString(new Integer[]{0,0}));
        int k = size;
        while (k-- > 0){
            Integer[] kthPair = pq.poll();
            ans.add(Arrays.asList(nums1[kthPair[0]], nums2[kthPair[1]]));
            if(ans.size() >= size) return ans;
            Integer[] a = kthPair.clone();
            a[0]++;
            if(seen.add(Arrays.toString(a))){
                pq.offer(a);
            }
            Integer[] b = kthPair.clone();
            b[1]++;
            if(seen.add(Arrays.toString(b))){
                pq.offer(b);
            }

        }

        return ans;
    }
}
