//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1856 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        solution.longestCommonPrefix(new String[]{"flower","flow","flight"});
        solution.longestCommonPrefix(new String[]{"dog","racecar","car"});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            StringBuilder ans = new StringBuilder();
            int minLength = Integer.MAX_VALUE;
            int size = strs.length;
            if (size == 1) {
                return strs[0];
            }
            for (int i = 0; i < size; i++) {
                minLength = Math.min(minLength, strs[i].length());
            }

            for (int j = 0; j < minLength; j++) {
                char c = strs[0].charAt(j);
                for (int i = 1; i < size; i++) {
                    if (c != strs[i].charAt(j)) {
                        return ans.toString();
                    } else if (i == size - 1) {
                        ans.append(c);
                    }
                }
            }
            return ans.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}