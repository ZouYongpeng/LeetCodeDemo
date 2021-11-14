//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 4281 👎 0


package leetcode.editor.cn;

class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        solution.longestPalindrome("babad");
        solution.longestPalindrome("cbbd");
        solution.longestPalindrome("a");
        solution.longestPalindrome("ac");
        solution.longestPalindrome("aaaa");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
//            return longestPalindrome_DP(s);
            return longestPalindrome_Expand(s);
        }

        private String longestPalindrome_DP(String s) {
            if (s == null || s.length() == 1) {
//                System.out.println("longestPalindrome: "+s+" -> "+ s);
                return s;
            }

            int size = s.length();
            int start = 0;
            int maxLength = 1;

            // dp[i][j] : s(i..j)为回文串时为true，否则false
            boolean[][] dp = new boolean[size][size];
            for (int i = 0; i < size; i++) {
                dp[i][i] = true;
            }

            // 先枚举字串长度
            for (int l = 1; l <= size; l++) {
                for (int i = 0; i < size - 1; i++) {
                    int j = i + l;
                    if (j >= size) {
                        break;
                    }
                    if (s.charAt(i) != s.charAt(j)) {
                        dp[i][j] = false;
                    } else {
                        if (j - i < 2) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }

                        if (dp[i][j] && maxLength < j - i + 1) {
                            start = i;
                            maxLength = j - i + 1;
                        }
                    }
                }
            }
            String substring = s.substring(start, start + maxLength);
//            System.out.println("longestPalindrome: "+s+" -> "+ substring);
            return substring;
        }

        private String longestPalindrome_Expand(String s) {
            if (s == null || s.length() == 1) {
                return s;
            }

            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int length = Math.max(
                        expandAroundCenter(s, i, i),
                        expandAroundCenter(s, i, i + 1)
                );
                if (length > end - start + 1) {
                    if (length % 2 == 0) {
                        start = i - length / 2 + 1;
                    } else {
                        start = i - length / 2;
                    }
                    end = i + length / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            int length = 0;
            while (left >= 0 && right < s.length() &&
                    s.charAt(left) == s.charAt(right)) {
                length = right - left + 1;
                left--;
                right++;
            }
            return length;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}