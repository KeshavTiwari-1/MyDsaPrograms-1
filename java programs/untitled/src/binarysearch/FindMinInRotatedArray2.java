package binarysearch;

public class FindMinInRotatedArray2 {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        return findMinhelp(nums, left, right);
    }

    public int findMinhelp(int[] nums, int left, int right) {
        if(left > right){
            return Integer.MAX_VALUE;
        }
        while (left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }
            else if(nums[mid] == nums[right] && nums[mid] == nums[left]){
                return Math.min(findMinhelp(nums, left, mid-1), findMinhelp(nums, mid + 1, right));
            }
            else{
                right = mid;
            }
        }
        return nums[left];
    }
}
