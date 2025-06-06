package binarysearch;

public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        while (left < right){
            int mid = left + (right - left + 1)/2; // right biased
            if(letters[mid]> target){
                right = mid - 1;
            }
            else {
                left = mid;
            }
        }
        if(letters[left] > target){
            return letters[left];
        }
        else if(left + 1 < letters.length){
            return letters[left + 1];
        }
        else {
            return letters[0];
        }
    }
    private  static void main(){

    }
}
