//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 有效括号组合需满足：左括号必须以正确的顺序闭合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 2145 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
        solution.generateParenthesis(3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> ans = new ArrayList<>();
            backTrack(ans, new StringBuilder(), 0, 0, n);
            return ans;
        }

        private void backTrack(
                List<String> ans, StringBuilder stringBuilder,
                int open, int close, int max) {
            if (stringBuilder.length() == max * 2) {
//                System.out.println("backTrack add : "+stringBuilder.toString());
                ans.add(stringBuilder.toString());
                return;
            }

            if (open < max) {
                stringBuilder.append('(');
                backTrack(ans, stringBuilder, open+1, close, max);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            if (open > close) {
                stringBuilder.append(')');
                backTrack(ans, stringBuilder, open, close+1, max);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}