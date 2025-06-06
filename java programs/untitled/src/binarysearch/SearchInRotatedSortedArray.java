package binarysearch;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if(target == nums[mid]) return mid;
            // target is present first part of rotated array
            if(target >= nums[0]){
                // mid is present in first part of rotated array
                if(nums[mid] >= nums[0]){
                    if(target > nums[mid]){
                        left = mid + 1;
                    }
                    else{
                        right = mid - 1;
                    }
                }
                // mid is present in second part of rotted array
                else{
                    right = mid - 1;
                }
            }
            // target present in second part of rotated array
            else {
                // mid is present is first part of array
                if(nums[mid] >= nums[0]){
                    left = mid + 1;
                }
                // mid in second part of array
                else{
                    if(target > nums[mid]){
                        left = mid + 1;
                    }
                    else{
                        right = mid - 1;
                    }
                }
            }
        }
        return -1;
    }
}

