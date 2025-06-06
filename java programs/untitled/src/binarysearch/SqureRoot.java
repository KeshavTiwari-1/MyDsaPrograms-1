package binarysearch;

public class SqureRoot {

    // trying to solve it basic binary search
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        while (left <= right){
            int mid = left + (right - left)/2;
            if((long) mid * mid  <= (long) x && (long) (mid +1 ) * (mid + 1) > (long) x){
                return mid;
            }
            else if((long) mid * mid < x){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return left;
    }

    // trying to solve it upper bound binary search
    public int mySqrt1(int x) {
        long left = 0;
        long right = (long)x + 1;
        while (left < right){
            long mid = left + (right - left)/2;
            if( mid * mid  <= (long) x){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        return (int)left - 1;
    }

}
