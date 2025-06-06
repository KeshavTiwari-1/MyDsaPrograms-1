package heap;

import java.util.*;

public class KDistancesStringArrangeMent {
    public String rearrangeString(String s, int k) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (p,q) -> q.getValue() - p.getValue()
        );
        for(Map.Entry<Character, Integer> e : map.entrySet()){
            pq.offer(e);
        }
        int maxCount = pq.peek().getValue();
        if((maxCount -1) * (k-1) > n - maxCount) return "";

        List<StringBuilder> list = new ArrayList<>();
        for(int i = 0; i<maxCount; i++){
            list.add(new StringBuilder());
        }
        int size = pq.size();
        int l = 0;
        for(int i = 0; i < size; i++){
            Map.Entry<Character, Integer> e = pq.poll();
            int freq = e.getValue();
            while (freq-- > 0){
                list.get(l).append(e.getKey());
                l = (l + 1) % (e.getValue() == maxCount ? list.size() : list.size()-1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder stringBuilder : list) {
            sb.append(stringBuilder);
        }
        return sb.toString();
    }
}
