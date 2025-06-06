package binarysearch;

import java.util.Arrays;

public class TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r){
            int sum = numbers[l]+numbers[r];
            if(sum == target){
                return new int[]{l+1,r+1};
            }
            else if(sum > target){
                int idx = Arrays.binarySearch(numbers, l+1, r, target - numbers[l]);
                if(idx < 0){
                    idx = (-1) * (idx + 1) - 1;
                }
                r = idx;
            }
            else{
                int idx = Arrays.binarySearch(numbers, l+1, r, target - numbers[r]);
                if(idx < 0){
                    idx = (-1) * (idx + 1);
                }
                l = idx;
            }
        }
        return new int[]{-1,-1};
    }
}
