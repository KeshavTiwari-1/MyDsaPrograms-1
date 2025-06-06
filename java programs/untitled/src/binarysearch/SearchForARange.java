package binarysearch;

public class SearchForARange {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] ans = new int[2];
        while (left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){

                int start = left, end= mid;
                // search lower bound b left and mid
                while (start < end){
                    int m = start + (end - start)/2;
                    if(nums[m] < target) start = m + 1;
                    else end = m;
                }
                ans[0] = start;
                start = mid;
                end = right;
                while (start < end){
                    int m = start + (end - start)/2;
                    if(nums[m] > target) end = m;
                    else start = m + 1;
                }
                ans[1] = (nums[start] == target) ? start : start - 1;
                return ans;
                // search upper bound bw mid and right
            }
            else if (nums[mid] > target){
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        ans[0] = -1;
        ans[1] = -1;
        return ans;
    }

    // by lower bound and upper bound
    public int[] searchRange1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] ans = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        //upper bound
        while (left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        if(left < nums.length  && nums[left] == target) {
            ans[0] = left;
        }
        else return ans;
        // upper bound
        right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left)/2 + 1; // make mid biased to the right
            if(nums[mid] > target){
                right = mid - 1;
            }
            else left = mid;
        }
        ans[1] = right;
        return ans;
    }
}


