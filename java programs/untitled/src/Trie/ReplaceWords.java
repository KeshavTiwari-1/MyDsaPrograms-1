package Trie;

import java.util.List;

public class ReplaceWords {

    /*
    In English, we have a concept called root, which can be
    followed by some other word to form another longer word -
    let's call this word derivative.
    For example, when the root "help" is followed by the word
    "ful", we can form a derivative "helpful".

    Given a dictionary consisting of many roots and a sentence
    consisting of words separated by spaces, replace all the
    derivatives in the sentence with the root forming it. If
    a derivative can be replaced by more than one root, replace
    it with the root that has the shortest length.

    Return the sentence after the replacement.
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        String[] senArray = sentence.split(" ");
        TrieNode root = makeTrie(dictionary);
        StringBuilder sb = new StringBuilder();
        for(String sen : senArray){
            if(!sb.isEmpty()) sb.append(" ");
            sb.append(getShortestString(root, sen));
        }
        return sb.toString();
    }

    private String getShortestString(TrieNode root, String sen) {
        StringBuilder sb = new StringBuilder();
        boolean setString = false;
        char[] chars = sen.toCharArray();
        for (char c : chars){
            if(root == null || root.children[c - 'a'] == null) break;
            sb.append(c);
            root = root.children[c - 'a'];
            if(root.isEnd) {
                setString = true;
                break;
            }
        }
        if(setString) return sb.toString();
        return sen;
    }

    private TrieNode makeTrie(List<String> dictionary) {
        TrieNode root = new TrieNode();
        for(String word : dictionary){
            char[] chars = word.toCharArray();
            TrieNode cur = root;
            for(char c : chars){
                if(cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
                cur = cur.children[c - 'a'];
            }
            cur.isEnd = true;
        }
        return root;
    }
}
