package binarysearch;

public class SplitArrayLargestSum {
    // https://leetcode.com/explore/learn/card/binary-search/146/more-practices-ii/1042/
    // prepare prefix array
    // find the lowest element
    // binary search b/w the lowest element and prefix array last element
    // in binary search find if mid as the largest sub array sum is possible or not
    // if yes right = mid else left = mid + 1
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n];
        int left = Integer.MAX_VALUE;
        for (int i = 0; i<n; i++){
            pre[i] = nums[i] + (i > 0 ? pre[i-1] : 0);
            if(left > nums[i]) left = nums[i];
        }
        if(k == 1) return pre[n-1];
        int right = pre[n-1];
        while (left < right){
            int mid = left + (right - left)/2;
            if(canMidBePossibleAsLargestSum(pre, mid, k)){
                right = mid;
            }
            else left = mid + 1;
        }
        return left;
    }

    private boolean canMidBePossibleAsLargestSum(int[] pre, int mid, int k) {
        int i = 0;
        int n = pre.length;
        int preI = -1;
        k--;
        while (k >= 0){
            while ( i  < n - k && pre[i] - (preI < 0 ? 0 : pre[preI]) <= mid) i++;
            if(i == preI) return false;
            preI = i - 1;
            k--;
        }
        return i == n;
    }
}
