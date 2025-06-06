package heap;

import java.util.*;

public class TaskScheduler {

    // o(n)
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char c : tasks){//o(n)
            freq[c-'A'] +=1;
        }
        List<Pair<Character, Integer>> list = new ArrayList<>();
        for(char c = 'A'; c<='Z'; c++){//o(1)
            if(freq[c - 'A'] > 0) list.add(new Pair<>(c, freq[c-'A']));
        }
        list.sort(Comparator.comparing(Pair::getValue));//o(1)
        List<StringBuilder> sbList = new ArrayList<>();
        int k = 0;
        int maxFreq = 0;
        while (list.size() > 0){//o(n)
            Pair<Character, Integer> pair = list.removeLast();
            if(sbList.isEmpty()){
                int i = 0;
                maxFreq = pair.getValue();
                while (i++ < pair.getValue()){
                    sbList.add(new StringBuilder(pair.getKey().toString()));

                }
            }
            else{
                int x = pair.getValue();
                int maxK = (x == maxFreq) ? sbList.size()-1 : sbList.size()-2;
                while (x-- > 0){
                    sbList.get(k).append(pair.getKey());
                    k++;
                    if(k > maxK){
                        k=0;
                    }
                }
            }
        }
        int count = 0;
        for(int l = 0; l < sbList.size()-1; l++){//o(n)
            if (sbList.get(l).length() >= n+1) count+=sbList.get(l).length();
            else count+=(n+1);
        }
        count+=sbList.getLast().length();
        return count;
    }

    // better solution o(n)
    public int leastInterval1(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char c : tasks){//o(n)
            freq[c-'A'] +=1;
        }
        Arrays.sort(freq);
        int maxFreq = freq[25];
        int idleSlot = (freq[25]-1) * n;
        for(int i = 24; i >= 0 && freq[i] > 0; i--){
            idleSlot-= Math.min(maxFreq-1, freq[i]);
        }
        // idle slot can be minus
        return idleSlot > 0 ? tasks.length + idleSlot : tasks.length;
    }
}
