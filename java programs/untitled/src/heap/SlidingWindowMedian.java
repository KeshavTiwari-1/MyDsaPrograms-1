package heap;

import java.util.TreeSet;
import java.util.function.Supplier;

public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {

        // Always use Integer.compare(int zz, int b) because in overflow cases(-1, 2147483648) a-b give wrong comparison.
        TreeSet<Integer> left = new TreeSet<>(
                (p,q)-> nums[p] != nums[q] ? Integer.compare(nums[q], nums[p]) : q - p
        );
        TreeSet<Integer> right = new TreeSet<>(
                (p,q)-> nums[p] != nums[q] ? Integer.compare(nums[p], nums[q]) : p - q
        );

        // Supplier<Double> median = ()-> {
        //     if(k%2 == 0) return ((double) nums[left.first()] + (double)right.first())/(double)2;
        //     else return (double) right.first();
        // };

        Supplier<Double> median = (k % 2 == 0) ?
                () -> ((double) nums[left.first()] + nums[right.first()]) / 2 :
                () -> (double) nums[right.first()];

        Runnable balance = () -> {
            while(left.size() > right.size()) right.add(left.pollFirst());
        };

        for(int i = 0; i < k; i++){
            left.add(i);
        }
        balance.run();
        double[] res = new double[nums.length - k + 1];
        res[0] = median.get();
        for(int i = k, r = 1; i < nums.length; i++,r++){
            // starting of window i - k, can be present in left or right
            if(!left.remove(i-k)) right.remove(i-k);
            // ADD new ith element in left so left size will be equal to right size or right size + 1
            right.add(i);
            // adding a element from right (smallest in right)
            left.add(right.pollFirst());
            // balance the tree.
            // if nums[i] is less than
            balance.run();
            res[r] = median.get();
        }
        return res;
    }
}
