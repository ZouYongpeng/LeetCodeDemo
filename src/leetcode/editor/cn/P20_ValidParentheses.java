//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2757 👎 0


package leetcode.editor.cn;

import java.util.*;

class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            int size = s.length();
            if (size % 2 != 0) {
                return false;
            }

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < size; i++) {
                if (isLeft(s.charAt(i))) {
                    stack.push(s.charAt(i));
                } else {
                    if (stack.isEmpty() || !isYes(stack.pop(), s.charAt(i))) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }

        private boolean isLeft(char c) {
            return c == '(' || c == '{' || c == '[' ;
        }

        private boolean isYes(char left, char right) {
            return (left == '(' && right == ')') ||
                    (left == '{' && right == '}') ||
                    (left == '[' && right == ']');
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}