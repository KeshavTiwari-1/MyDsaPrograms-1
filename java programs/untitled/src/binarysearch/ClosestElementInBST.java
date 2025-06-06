package binarysearch;

public class ClosestElementInBST {
    static int minDiff(Node root, int k) {
        Node cur = root;
        int closestVal = Integer.MAX_VALUE;
        while (cur != null){
            if(dif(cur.data , k) < closestVal){
                closestVal = dif(cur.data , k);
            }
            if(cur.data > k){
                cur = cur.left;
            }
            else if(cur.data < k){
                cur = cur.right;
            }
            else{
                break;
            }
        }
        return closestVal;
    }

    private static int dif(int a, int b){
        return a > b ? a - b : b - a;
    }
}
