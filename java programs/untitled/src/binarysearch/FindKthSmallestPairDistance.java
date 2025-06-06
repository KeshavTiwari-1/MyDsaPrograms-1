package binarysearch;

import java.util.Arrays;

public class FindKthSmallestPairDistance {

    // Approach
    // sort the array
    // find max distance m
    // binary search between 0 to m
    // for every search find how many pairs have length less than mid
    // to find no of pairs we can use sliding window
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = nums[n-1] - nums[0];
        int l = 0, r = m;
        while (l<r){
            int mid = l + (r - l)/2;
            int noOfPairs = findPairsLessThanEqualToMid(nums, mid);
            if(noOfPairs < k) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    // Sliding Window O(n)
    private int findPairsLessThanEqualToMid(int[] nums, int mid) {
        int i = 0;
        int j = 1;
        int count = 0;
        while (j < nums.length){
            if(nums[j] - nums[i] <= mid){
                count += (j-i);
                j++;
            }
            else {
                i++;
            }

            if(i == j) j++;
        }
        return count;
    }
}
