package Trie.autocompletesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AutoCompleteSystem {
    Trie root;
    StringBuilder sb;

    AutoCompleteSystem(){
        root = new Trie();
        sb = new StringBuilder();
    }

    AutoCompleteSystem(String[] sentences, int[] times) {
        this();
        for(int i = 0; i < sentences.length; i++){
            root.insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if(c == '#'){
            root.insert(sb.toString(), 1);
            sb = new StringBuilder();
            return res;
        }
        PriorityQueue<Trie> pq = new PriorityQueue<>(
                (a , b) -> a.val == b.val ? b.word.compareTo(a.word) : a.val - b.val
        );
        sb.append(c);
        Trie node = root.search(sb.toString());
        dfs(node, pq);
        while (!pq.isEmpty()){
            res.addFirst(pq.poll().word);
        }
        return res;
    }

    private void dfs(Trie node, PriorityQueue<Trie> pq) {
        if(node == null) return;
        if(node.val > 0){
            pq.offer(node);
            if(pq.size() > 3) pq.poll();
        }
        for(Trie child : node.children){
            dfs(child , pq);
        }
    }
}
