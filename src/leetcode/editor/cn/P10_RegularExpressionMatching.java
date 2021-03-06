//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4： 
//
// 
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5： 
//
// 
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 
// 👍 2492 👎 0


package leetcode.editor.cn;

class RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch(String s, String p) {
            int size_s = s.length();
            int size_p = p.length();
            // dp[i][j] : s(0..i)和p(0..j)能匹配成功
            boolean[][] dp = new boolean[size_s + 1][size_p + 1];
            dp[0][0] = true;

            for (int i = 0; i <= size_s; i++) {
                for (int j = 1; j <= size_p; j++) {
                    if (p.charAt(j - 1) == '*') {
                        if (isMatch(s, i, p, j - 1)) {
                            // * 匹配零次或多次
                            dp[i][j] = dp[i][j - 2] | dp[i - 1][j];
                        } else {
                            // * 匹配零次
                            dp[i][j] = dp[i][j - 2];
                        }
                    } else {
                        if (isMatch(s, i, p, j)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            return dp[size_s][size_p];
        }

        private boolean isMatch(String s, int i, String p, int j) {
            if (i == 0) {
                return false;
            }
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}