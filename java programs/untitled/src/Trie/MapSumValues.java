package Trie;

public class MapSumValues {
    Node root;

    public MapSumValues() {
        root = new Node();
    }

    public void insert(String key, int val) {
        char[] arr = key.toCharArray();
        Node cur = root;
        for(char c : arr){
            if(cur.children[c - 'a'] == null) cur.children[c - 'a'] = new Node();
            cur.children[c - 'a'].value += val;
            cur = cur.children[c - 'a'];
        }
        if(cur.isEnd){
            int endValue = cur.endValue;
            cur.endValue = val;
            cur = root;
            for(char c : arr){

                cur = cur.children[c - 'a'];
                cur.value -= endValue;
            }
        }
        else{
            cur.isEnd = true;
            cur.endValue = val;
        }
    }

    public int sum(String prefix) {
        char[] arr = prefix.toCharArray();
        Node cur = root;
        for(char c : arr){
            if(cur.children[c - 'a'] == null) return 0;
            cur = cur.children[c - 'a'];
        }
        return cur.value;
    }

    class Node{
        int value;
        int endValue;
        boolean isEnd;
        Node[] children = new Node[26];
    }

}
