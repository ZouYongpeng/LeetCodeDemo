//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 
// 👍 1343 👎 0


package leetcode.editor.cn;

class ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new ZigzagConversion().new Solution();
        solution.convert("PAYPALISHIRING", 3);
        solution.convert("PAYPALISHIRING", 4);
//        solution.convert("A", 1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convert(String s, int numRows) {
            if (numRows <= 1) {
                return s;
            }
//            return firstMethod(s, numRows);
            return secondMethod(s, numRows);
        }

        private String firstMethod(String s, int numRows) {
            StringBuilder[] arrays = new StringBuilder[numRows];
            for (int i = 0; i < numRows; i++) {
                arrays[i] = new StringBuilder();
            }
            int rowIndex = 1;
            boolean isDown = true;
            int size = s.length();
            for (int i = 0; i < size; i++) {
                arrays[rowIndex - 1].append(s.charAt(i));
                rowIndex = getNextIndex(rowIndex, numRows, isDown);
                if (rowIndex == 1 || rowIndex == numRows) {
                    isDown = !isDown;
                }
            }

            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                ans.append(arrays[i]);
            }
            System.out.println("convert [" + s + "] : " + ans.toString());
            return ans.toString();
        }

        public int getNextIndex(int rowIndex, int numRows, boolean isDown) {
            if (isDown) {
                return rowIndex != numRows ? rowIndex + 1 : rowIndex - 1;
            } else {
                return rowIndex != 1 ? rowIndex - 1 : rowIndex + 1;
            }
        }

        public String secondMethod(String s, int numRows) {// 6
            StringBuilder ans = new StringBuilder();
            int totalTrans = (numRows - 1) * 2; //10
            for (int i = 0; i < numRows; i++) { // 0  1 2 3 4 5
                int trans = totalTrans - i * 2; // 10 8 6 4 2 10
                for (int j = i; j < s.length(); ) {
                    ans.append(s.charAt(j));
                    j = j + (trans == totalTrans || trans == 0 ? totalTrans : trans);
                    trans = (trans == totalTrans || trans == 0 ? totalTrans : totalTrans - trans);
                }
            }
//            System.out.println("convert [" + s + "] : " + ans.toString());
            return ans.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}