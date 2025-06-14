package Trie.addandsearchworddsa;

public class WordDictionary {
    TrieNode trieNode;

    public WordDictionary() {
        trieNode = new TrieNode();
    }

    public void addWord(String word) {
        trieNode.insert(word);
    }

    public boolean search(String word) {
        return trieNode.search(word);
    }
}
