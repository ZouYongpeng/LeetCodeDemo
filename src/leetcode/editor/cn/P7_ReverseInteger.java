//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 3218 👎 0


package leetcode.editor.cn;

class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        solution.reverse(1534236469);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int reverse(int x) {
            int ans = 0;
            while (x != 0) {
                int temp = x % 10;
                if (isOut(ans, temp)) {
                    return 0;
                }
                ans = ans * 10 + temp;
                x /= 10;
            }
            return ans;
        }

        private boolean isOut(int num, int temp) {
            long value = num * 10L + temp;
            return value < Integer.MIN_VALUE || value > Integer.MAX_VALUE;
//            return num > Integer.MAX_VALUE / 10 ||
//                    (num == Integer.MAX_VALUE / 10 && temp > Integer.MAX_VALUE % 10) ||
//                    num < Integer.MIN_VALUE / 10 ||
//                    (num == Integer.MIN_VALUE / 10 && temp < Integer.MIN_VALUE % 10);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}