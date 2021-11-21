//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 
// 👍 1532 👎 0


package leetcode.editor.cn;

import java.util.Stack;

class LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new LongestValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
//            return longestValidParentheses_DP(s);
            return longestValidParentheses_Stack(s);
        }

        private int longestValidParentheses_DP(String s) {
            int ans = 0;
            int size = s.length();
            // dp[i] : 以下标i字符结尾的最长有效括号的长度
            int[] dp = new int[size];
            for (int i = 1; i < size; i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i-1) == '(') {
                        // ...()
                        dp[i] = (i > 2 ? dp[i-2] : 0) + 2;
                    } else {
                        // ...))
                        if (i > dp[i-1] && s.charAt(i - dp[i-1] -1) == '(') {
                            // ...((...))
                            if (i - dp[i-1] >= 2) {
                                dp[i] = dp[i - dp[i-1] - 2] + dp[i-1] + 2;
                            } else {
                                dp[i] = dp[i-1] + 2;
                            }
                        }
                    }
                    ans = Math.max(ans, dp[i]);
                }
            }
            return ans;
        }
        
        private int longestValidParentheses_Stack(String s) {
            int ans = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int size = s.length();
            for (int i = 0; i < size; i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        ans = Math.max(ans, i - stack.peek());
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}