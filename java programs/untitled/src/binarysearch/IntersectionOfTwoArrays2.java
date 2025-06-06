package binarysearch;

import java.util.Arrays;

public class IntersectionOfTwoArrays2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] hash = new int[1001];
        for (int n : nums1){
            hash[n]++;
        }
        int index = 0;
        for(int n : nums2){
            if(hash[n] > 0){
                nums1[index++] = n;
                hash[n]--;
            }
        }
        return Arrays.copyOfRange(nums1, 0, index);
    }
}
