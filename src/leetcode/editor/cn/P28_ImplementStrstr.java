//实现 strStr() 函数。 
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。 
//
// 
//
// 说明： 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "hello", needle = "ll"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
// 
//
// 示例 3： 
//
// 
//输入：haystack = "", needle = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= haystack.length, needle.length <= 5 * 104 
// haystack 和 needle 仅由小写英文字符组成 
// 
// Related Topics 双指针 字符串 字符串匹配 
// 👍 1111 👎 0


package leetcode.editor.cn;

class ImplementStrstr {
    public static void main(String[] args) {
        Solution solution = new ImplementStrstr().new Solution();
        execute(solution, "hello", "ll");
        execute(solution, "aaaaa", "bba");
        execute(solution, "", "");
    }

    private static void execute(Solution solution, String haystack, String needle) {
        System.out.println("execute : " +
                "haystack = "+haystack+", needle = "+needle+
                " -> "+solution.strStr(haystack, needle));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            return strStr_my(haystack, needle);
        }

        /**
         * 执行耗时:1880 ms,击败了5.04% 的Java用户
         * 内存消耗:38.3 MB,击败了83.09% 的Java用户
         *
         * @param haystack
         * @param needle
         * @return
         */
        private int strStr_my(String haystack, String needle) {
            if (needle == null || needle.length() == 0) {
                return 0;
            }
            for (int index = 0; index <= haystack.length() - needle.length(); index++) {
                int i = index, j = 0;
                while (i < haystack.length() && j < needle.length()) {
//                    System.out.println("check - "+index+" : a["+i+"] = "+haystack.charAt(i)+", b["+j+"] = "+needle.charAt(j));
                    if (haystack.charAt(i) == needle.charAt(j)) {
                        if (j == needle.length() - 1) {
                            return i - j;
                        }
                        i++;
                        j++;
                    } else {
                        break;
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}