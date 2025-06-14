package Trie.addandsearchworddsa;

public class TrieNode {
    boolean isWord;
    TrieNode[] children = new TrieNode[27];

    void insert(String word){
        TrieNode node = this;
        for(char c : word.toCharArray()){
            int idx = getIdx(c);
            if(node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isWord = true;
    }

    boolean search(String prefix){
        return helper(prefix.toCharArray(), 0, this);
    }

    private boolean helper(char[] chars, int index, TrieNode node){
        if(node == null) return false;
        if(index == chars.length){
            return node.isWord;
        }
        int idx = getIdx(chars[index]);
        if(idx == 26){
            for(TrieNode tr : node.children){
                if(helper(chars, index + 1, tr)) return true;
            }
            return false;
        }

        return helper(chars, index + 1, node.children[idx]);
    }

    static int getIdx(char c){
        return c == '.' ? 26 : c - 'a';
    }
}
