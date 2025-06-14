package Trie;

import java.util.HashSet;
import java.util.Set;

public class FindMaximumXor {

    // Make trie of every number in reverse order to get Maximum XOR
    public int findMaximumXOR(int[] nums) {
        int maximumXor = Integer.MIN_VALUE;
        Trie root = new Trie();
        for(Integer num : nums){
           insertInTrie(num, root);
        }
        for(Integer num : nums){
            int xor = findXor(num, root);
            if(xor > maximumXor) maximumXor = xor;
        }
        return maximumXor;
    }

    private int findXor(Integer num, Trie node) {
        char[] chars = Integer.toBinaryString(num).toCharArray();
        int n = chars.length;
        for(int i = 31; i > 0; i--){
            int c = n - i >= 0 ? chars[n-i] - '0' : 0;
            if(c == 1){
                if(node.children[0] != null) node = node.children[0];
                else node = node.children[1];
            }
            else {
                if (node.children[1] != null) node = node.children[1];
                else node = node.children[0];
            }
        }
        return num ^ node.val;
    }

    private void insertInTrie(Integer num, Trie node) {
        char[] chars = Integer.toBinaryString(num).toCharArray();
        int n = chars.length;
        for(int i = 31; i > 0; i--){
            int c = n - i >= 0 ? chars[n-i] - '0' : 0;
            if(node.children[c] == null) node.children[c] = new Trie();
            node = node.children[c];
        }
        node.val = num;
    }

    class Trie{
        int val;
        Trie[] children = new Trie[2];
    }

    // bit manipulation
    // https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1057/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap
    // todo
    public int findMaximumXOR1(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}
