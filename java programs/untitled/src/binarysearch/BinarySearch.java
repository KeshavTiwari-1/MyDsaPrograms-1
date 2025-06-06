package binarysearch;

public class BinarySearch {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] > target) right = mid - 1;
            else left = mid+1;
        }
        return -1;
    }

    // upper bound = place target after largest value that is less than or equal to the target.
    // or upper bound is the index where all lower index value is less or equal to the target
    // place left at largest index where all left side value is less or equal to the target so our answer lies at left -1
    public int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] <= target) left = mid + 1;
            else right = mid;
        }
        if(left > 0 && nums[left - 1] == target) return left - 1;
        return -1;
    }

    // Lower Bound - place target at lowest position where all right side values are greater than or equal to target.
    // lower bound - place target at largest position where all left values are less than target
    // place left at largest index where all left side value is less than target so our answer lies at left
    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] >= target) right = mid;
            else left = mid+1;
        }
        if(left < nums.length && nums[left] == target) return left;
        return -1;
    }
}
