package Trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearch2 {
    // backtracking
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        int n = board.length;
        int m = board[0].length;
        for(String s : words){
            boolean flag = false;
            for(int i = 0 ; i < n && !flag; i++){
                for(int j = 0; j < m && !flag; j++){
                    if(backtracking(s.toCharArray(), 0, board, i, j, new boolean[n][m])){
                        res.add(s);
                        flag = true;
                    }
                }
            }
        }
        return res;
    }

    private boolean backtracking(char[] charArray, int index, char[][] board, int i, int j, boolean[][] visited) {
        int n = board.length;
        int m = board[0].length;
        if(index >= charArray.length) return true;
        if(i<0 || i>=n || j <0 || j>=m) return false;
        if(visited[i][j]) return false;
        if(board[i][j] == charArray[index]){
            visited[i][j] = true;
            boolean ans = backtracking(charArray, index + 1, board, i+1, j, visited) ||
                    backtracking(charArray, index + 1, board, i-1, j, visited) ||
                    backtracking(charArray, index + 1, board, i, j + 1, visited) ||
                    backtracking(charArray, index + 1, board, i, j - 1, visited);
            visited[i][j] = false;
            return ans;
        }
        return false;
    }
}
