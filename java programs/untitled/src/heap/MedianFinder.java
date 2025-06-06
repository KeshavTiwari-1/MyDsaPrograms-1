package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(left.isEmpty() && right.isEmpty()){
            left.offer(num);
            return;
        }
        if(num <= left.peek()) left.offer(num);
        else right.offer(num);
        if(left.size() != right.size()){
            int x = (left.size() - right.size()) /2;
            if(x > 0){
                while (x-- >0){
                    right.offer(left.poll());
                }
            }
            else {
                while (x++ < 0){
                    left.offer(right.poll());
                }
            }
        }

        }


    public double findMedian() {
        if(left.size() == right.size()) {
            return ((double) left.peek() + (double) right.peek()) / (double) 2;
        }
        if(left.size()> right.size())  return (double) left.peek();
        return (double) right.peek();
    }
}

