package Trie.autocompletesearch;

public class Trie {
    int val;
    String word = "";
    Trie[] children = new Trie[27];

    void insert(String w, int val){
        Trie node = this;
        for(char c : w.toCharArray()){
            int idx = getIdx(c);
            if(node.children[idx] == null) node.children[idx] = new Trie();
            node = node.children[idx];
        }
        node.val += val;
        node.word = w;
    }

    Trie search(String prefix){
        Trie node = this;
        for(char c : prefix.toCharArray()){
            int idx = getIdx(c);
            if(node.children[idx] == null) return null;
            node = node.children[idx];
        }
        return node;
    }

    static int getIdx(char c){
        return c == ' ' ? 26 : c - 'a';
    }
}
