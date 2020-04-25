package com.ymd.SBE.firstexmaple;

import java.util.*;

public class Solution {
    static boolean flag = false;

    public static void main(String[] args) {
        char[][] chars = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        System.out.println(exist(chars, "ABCB"));
    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int i, int j, String word, int num) {
        if (num == word.length()) {
            flag = true;
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(num)) {
            return false;
        }
        if (!flag) {
            char c = board[i][j];
            board[i][j] = '.';
            boolean dfs = dfs(board, i + 1, j, word, num + 1);
            boolean dfs1 = dfs(board, i - 1, j, word, num + 1);
            boolean dfs2 = dfs(board, i, j + 1, word, num + 1);
            boolean dfs3 = dfs(board, i, j - 1, word, num + 1);
            board[i][j] = c;
            return dfs || dfs1 || dfs2 || dfs3;
        } else {
            return true;
        }

    }
}