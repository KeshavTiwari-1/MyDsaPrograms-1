package binarysearch;

public class FindTheDuplicateNumber {

    // slow fast pointer o(n)
    public int findDuplicate1(int[] nums) {
        int slow = 0;
        int fast = 0;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // binary search o(nlogn)
    public int findDuplicate(int[] nums) {
        int l = 1;
        int r = nums.length - 1;
        while (l < r){
            int mid = l + (r - l)/2;
            int notGreaterElement = search(nums, mid);
            if(notGreaterElement <= mid) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private int search(int[] nums, int target){
        int count = 0;
        for(int n : nums) {
            if(n <= target) count ++;
        }
        return count;
    }
}
