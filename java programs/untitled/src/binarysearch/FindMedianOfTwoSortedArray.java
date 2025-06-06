package binarysearch;

public class FindMedianOfTwoSortedArray {

    // binary search
    // to find position of mid
    // in second we are searching the lower bound of the mid and calculating left count and right count
    // if(lc == rc) then we got the mid
    // if(lc < rc) then mid will be right of mid
    // if(rc < lc) then mid will be left of mid.
    //  pos-processing if either of one array is exhausted
    public double findMedianSortedArrays(int[] n1, int[] n2) {
        int l1 = 0, l2 = 0, r1 = n1.length - 1, r2 = n2.length - 1;
        int leftCount = 0, rightCount = 0;
        int leftMaxValue = Integer.MIN_VALUE, rightMinValue = Integer.MAX_VALUE;
        while(l1 <= r1 && l2<=r2){
            int mid = l1 + (r1 - l1)/2;
            // lower Bound
            int lowerBound = search(n2, l2, r2, n1[mid]);
            int lc = leftCount + (mid - l1) + (lowerBound - l2); // exclude mid
            int rc = rightCount + (r1 - mid) + (r2 - lowerBound + 1); // exclude mid
            if(lc == rc) return (double) n1[mid];
            if(lc - rc == 1){
                int x1 = n1[mid];
                int x2 = leftMaxValue;
                if(lowerBound - 1<=r2 && lowerBound - 1 >=0){
                    x2 = Math.max(x2, n2[lowerBound - 1]);
                }
                if(mid - 1>=l1){
                    x2 = Math.max(x2, n1[mid -1]);
                }

                return (double) (x1 + x2)/2;
            }
            if(rc - lc == 1){
                int x1 = n1[mid];
                int x2 = Math.min(lowerBound <= r2 ? n2[lowerBound] : rightMinValue,
                        mid+1 <= r1 ? n1[mid + 1] : rightMinValue);
                return (double) (x1 + x2)/2;
            }
            if(lc > rc){
                rightCount  += (r1 - mid + 1) + (r2 - lowerBound + 1);
                rightMinValue = n1[mid];
                r1 = mid - 1;
                r2 = lowerBound - 1;
            }
            if(rc > lc){
                leftCount += mid - l1 + 1 + lowerBound - l2;
                leftMaxValue = n1[mid];
                l1 = mid + 1;
                l2 = lowerBound;
            }
        }
        // post-processing when n1 exhausted and there are still elements remaining in n2
        if(l1 <= r1){
            if((n1.length + n2.length) % 2 != 0){
                int index = (n1.length + n2.length)/2 - leftCount + l1;
                return (double) n1[index];
            }
            else{
                int index1 = (n1.length + n2.length)/2 - leftCount -1 + l1;
                return (double)(n1[index1] + n1[index1 + 1]) /2;
            }
        }
        if(l2<=r2){
            if((n1.length + n2.length) % 2 != 0){
                int index = (n1.length + n2.length)/2 - leftCount + l2;
                return (double) n2[index];
            }
            else{
                int index1 = (n1.length + n2.length)/2 - leftCount -1 + l2;
                return (double)(n2[index1] + n2[index1 + 1]) /2;
            }
        }
        return -10000;
    }

    private int search(int[] n, int l, int r, int x) {
        while (l < r){
            int mid = l + (r - l)/2;
            if(n[mid] < x) l = mid + 1;
            else r = mid;
        }
        if(n[l] < x) return l + 1;
        return l;
    }

    //second approach
    // median index will be at mi = (m + n + 1)/2
    // so we divide the search space in two parts left and right
    // suppose we take x (0 to m) element from first array in left and right has m-x.
    // so in left from second array we have to take mi - x element and to right n - (mi-x) element
    // check if this combination is correct or not -
    // 2 conditions
    // x1(largest of first array left segment) <= y2(minimum of second array right segment)
    // y1(largest of second array left segment) <= x2 (minimum of first array right segment)
    // if both conditions true then we found median accordingly
    // if not
    // 1. x1 > y2 - > median on the left side so right = mid - 1;
    // 2  y1 > x2 - > median on the right side so left = mid + 1;
    public double findMedianSortedArrays1(int[] n1, int[] n2){
        int m = n1.length, n = n2.length;
        //for convenience first array should be larger where we are applying binary search so no post-processing required
        if(m > n){
            return findMedianSortedArrays1(n2, n1);
        }
        int median = (m + n + 1)/2; // in case of total odd numbers left will have 1 extra element
        int l = 0, r = m; // take l to r number of elements from n1
        while (l <= r){
            int x = l + (r - l)/2; // means taking x element form n1 to left side
            int x1 = x - 1 >= 0 ? n1[x - 1] : Integer.MIN_VALUE;
            int x2 = x < m ? n1[x] : Integer.MAX_VALUE;
            int y1 = median - x > 0 ? n2[median - x - 1] : Integer.MIN_VALUE;
            int y2 = median - x < n ? n2[median - x] : Integer.MAX_VALUE;
            if(x1 <= y2 && x2 >= y1){
                if((m + n) % 2 == 0){
                    return (double)(Math.max(x1, y1) + Math.min(y2,x2))/2;
                }
                else{
                    return (double) Math.max(x1, y1);
                }
            }
            else if(x1 > y2){
                r = x - 1;
            }
            else {
                l = x + 1;
            }

        }
        return -1;
    }
}
