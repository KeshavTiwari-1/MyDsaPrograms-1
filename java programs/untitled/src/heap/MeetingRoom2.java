package heap;

import java.util.*;

public class MeetingRoom2 {

    //heap
    public int minMeetingRooms(int[] start, int[] end) {
        int[][] meetings = new int[start.length][];
        for (int i = 0; i<start.length; i++){
            meetings[i] = new int[]{start[i], end[i]};
        }
        Arrays.sort(meetings,(p,q)-> Integer.compare(p[0], q[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p,q)-> Integer.compare(p[1], q[1])
        );
        for(int i = 0; i<meetings.length; i++) System.out.println(Arrays.toString(meetings[i]));
        int max = 0;
        for(int i = 0; i<meetings.length; i++){
            int s = meetings[i][0];
            int e = meetings[i][1];
            while (!pq.isEmpty() && pq.peek()[1] <= s) pq.poll();
            pq.offer(meetings[i]);
            if(max < pq.size()) max = pq.size();
        }
        return max;
    }

//    // prefix sum o(n)
//    public int minMeetingRooms(int[] start, int[] end) {
//        int n = 0;
//        for(int i : end){
//            if(i > n) n = i;
//        }
//        int[] delta = new int[n+1];
//        for(int s : start) delta[s]++;
//        for(int e : end) delta[e]--;
//        int max = 0;
//        for(int i = 1; i<=n; i++){
//            delta[i]+=delta[i-1];
//            if (delta[i] > max) max = delta[i];
//        }
//        return max;
//    }

//    // two pointers
//    public int minMeetingRooms(int[] start, int[] end) {
//        int count = 0;
//        int maxCount = 0;
//        Arrays.sort(start);
//        Arrays.sort(end);
//        int i = 0, j = 0, n = start.length;
//        while(i<n){
//            if(start[i] < end[j]){
//                count++;
//                i++;
//                if(maxCount < count) maxCount = count;
//            }
//            else{
//               count--;
//               j++;
//            }
//        }
//        return maxCount;
//    }





//    public int minMeetingRooms(int[] start, int[] end) {
//        int n = start.length;
//        ArrayList<Meeting> meetings = new ArrayList<>();
//        for(int i = 0; i < start.length; i++){
//            meetings.add(new Meeting(start[i], start[i]));
//        }
//        Collections.sort(meetings, new Comparator<Meeting>() {
//            @Override
//            public int compare(Meeting m1, Meeting m2) {
//                return Integer.compare(m1.start, m2.start);
//            }
//        });
//        int i = 0, j = 0, maxCount = 0;
//        while (true){
//
//            if(maxCount < j-i+1){
//
//            }
//        }
//
//    }


}

//class Meeting{
//    int start;
//    int end;
//    Meeting(int start, int end){
//        this.start = start;
//        this.end = end;
//    }
//}
