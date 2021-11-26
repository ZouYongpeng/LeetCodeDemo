//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [['5','3','.','.','7','.','.','.','.'],['6','.','.','1','9','5','.'
//,'.','.'],['.','9','8','.','.','.','.','6','.'],['8','.','.','.','6','.','.','.'
//,'3'],['4','.','.','8','.','3','.','.','1'],['7','.','.','.','2','.','.','.','6'
//],['.','6','.','.','.','.','2','8','.'],['.','.','.','4','1','9','.','.','5'],['
//.','.','.','.','8','.','.','7','9']]
//输出：[['5','3','4','6','7','8','9','1','2'],['6','7','2','1','9','5','3','4','8'
//],['1','9','8','3','4','2','5','6','7'],['8','5','9','7','6','1','4','2','3'],['
//4','2','6','8','5','3','7','9','1'],['7','1','3','9','2','4','8','5','6'],['9','
//6','1','5','3','7','2','8','4'],['2','8','7','4','1','9','6','3','5'],['3','4','
//5','2','8','6','1','7','9']]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 
// 👍 1035 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SudokuSolver {
    public static void main(String[] args) {
        Solution solution = new SudokuSolver().new Solution();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                stringBuilder.append(board[i][j] + "  ");
            }
            System.out.println(stringBuilder);
        }
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 如line[2][3] == true，表示数字4在第二行出现
        private boolean[][] line = new boolean[9][9];
        private boolean[][] column = new boolean[9][9];
        private boolean[][][] block = new boolean[3][3][9];
        // 存储空白格，
        private List<int[]> spaces = new ArrayList<>();
        private boolean isSuccess = false;

        public void solveSudoku(char[][] board) {
            // 先遍历, 初始化各数组
            ergodic(board);

            // 再递归
            recursion(board, 0);
        }

        //
        private void ergodic(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        spaces.add(new int[]{i, j});
                    } else {
                        int num = board[i][j] - '0' - 1;
                        line[i][num] = true;
                        column[j][num] = true;
                        block[i/3][j/3][num] = true;
                    }
                }
            }
        }

        private void recursion(char[][] board, int spaceIndex) {
            if (spaceIndex == spaces.size()) {
                isSuccess = true;
                return;
            }
            int i = spaces.get(spaceIndex)[0];
            int j = spaces.get(spaceIndex)[1];
            for (int num = 1; num <= 9 && !isSuccess; num++) {
                if (!line[i][num - 1] && !column[j][num - 1] && !block[i/3][j/3][num - 1]) {
                    line[i][num - 1] = true;
                    column[j][num - 1] = true;
                    block[i/3][j/3][num - 1] = true;

                    board[i][j] = (char) (num + '0');
                    recursion(board, spaceIndex + 1);

                    line[i][num - 1] = false;
                    column[j][num - 1] = false;
                    block[i/3][j/3][num - 1] = false;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}