package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeFreeTime {
//    public List<Interval> employeeFreeTime(int[][] schedule) {
//        // convert input to leetcode format
//        List<List<Interval>> list = new ArrayList<>();
//        for (int[] emp : schedule){
//            List<Interval> employee = new ArrayList<>();
//            for(int j = 0; j<emp.length; j= j+2){
//                Interval i = new Interval(emp[j], emp[j+1]);
//                employee.add(i);
//            }
//            list.add(employee);
//        }
//        // main logic start
//        // merge overlapping input;
//        PriorityQueue<Interval> pq = new PriorityQueue<>(
//                (p,q) -> Integer.compare(p.start , q.start)
//        );
//        for (List<Interval> emp : list) pq.addAll(emp);
//        List<Interval> mergedInterval = new ArrayList<>();
//        while (!pq.isEmpty()){
//            if(mergedInterval.isEmpty()) {mergedInterval.addLast(pq.poll()); continue;}
//            Interval cur = pq.poll();
//            Interval pre = mergedInterval.getLast();
//            if(pre.end < cur.start){
//                mergedInterval.addLast(cur);
//            }
//            else{
//
//            }
//        }
//    }
}

class Interval{
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
