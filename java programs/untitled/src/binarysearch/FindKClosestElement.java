package binarysearch;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/learn/card/binary-search/135/template-iii/945/
public class FindKClosestElement {
    // search element by binary search and then with two pointers make the k closest element window
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int mid = left + (right - left)/2;
            if(arr[mid] < x) left = mid + 1;
            else right = mid;
        }
        // find closest k element to x;
        int i = left;
        int j = left - 1;
        while (k-- > 0){
            if(i < arr.length && j>=0){
                if(x - arr[j] <= arr[i] - x){
                    j--;
                }
                else i++;
            }
            else if (i<arr.length) {
                i++;
            }
            else {
                j--;
            }
        }
        j++;
        i--;
        List<Integer> list = new ArrayList<>();
        while (j<=i){
            list.add(arr[j]);
            j++;
        }
        return list;
    }


    //optimal solution - search for the lower bound of the window itself
    public List<Integer> findClosestElements1(int[] arr, int k, int x){
        int left = 0;
        int right = arr.length - k; // for last k element window starting element index is (arr.length -k);
        while (left < right){
            int mid = left + (right - left)/2;
            // so our range of k element is mid to mid + k -1 index.
            // x can be present in this window or left or the window or right of the window
            // we are looking for the lower bound or first element is k size window so
            // if arr[mid](starting point) is better than arr[mid+k](starting point) then arr[mid + k] will not be part of our answer and
            // if arr[mid + k] is better than arr[mid] can't be starting point
            // four cases -
            // 1. -------x------arr[mid]------arr[mid + k]   discard arr[mid + k] right = mid;
            // 2. --------arr[mid]--x----------------arr[mid+k] discard arr[mid + k] right = mid
            // 3. --------arr[mid]----------------x--arr[mid + k] discard arr[mid] left = mid+1
            // 4. --------arr[mid]-------------------arr[mid+k]---x-- discard arr[mid] left = mid+1
            // above 4 conditions can be solved by below equation x-arr[mid] > arr[mid+k]-x
            if(x - arr[mid] > arr[mid + k] - x){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = left; i<left+k; i++){
            list.add(arr[i]);
        }
        return list;
    }

    private int mod(int a, int b){
        return a - b >= 0 ? a-b : b-a;
    }
}
