import Trie.FindMaximumXor;
import Trie.ReplaceWords;
import Trie.WordSearch2;
import binarysearch.*;
import heap.*;

import java.util.*;


public class Main {
    public static final int[][] mat = new int[][]{{1,3,11},{2,4,6}};
    public static final int k = 9;

    public static void main(String[] args) {
        WordSearch2 sol = new WordSearch2();
        System.out.println(
                sol.findWords(
                       new char[][]{{'a','a'}},
                        new String[] {"aaa"}
        ));
//        System.out.println(
//                sol.findWords(
//                        new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},
//                        new String[] {"oath","pea","eat","rain"}
//                ));
    }

    private static void printTreeNode(TreeNode t) {
        System.out.println();
        List<TreeNode> queue = new LinkedList<>();
        queue.add(t);
        int count = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<TreeNode> list = new LinkedList<>();
            while (size >0){
                TreeNode node = queue.removeFirst();
                if(node == t && count-- <= 0) break;
                if(node != null) System.out.print(node.val + ",");
                else System.out.print("null,");
                if(node != null) {
                    list.addLast(node.left);
                    list.addLast(node.right);
                }
                size--;
            }
            if(count == -1) break;
            queue = list;
        }
    }

    private static TreeNode makeTree() {
        char[] arr = {'1','2','3'};
        return makeTree(arr, 0);
    }

    private static TreeNode makeTree(char[] arr, int i){
        if(i>= arr.length) return null;
        if(arr[i] == 'n') return null;
        return new TreeNode(arr[i] - '0', makeTree(arr, 2*i+1), makeTree(arr, 2*i+2));
    }

}


