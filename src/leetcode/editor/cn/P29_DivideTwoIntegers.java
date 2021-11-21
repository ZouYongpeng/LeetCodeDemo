//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 位运算 数学 
// 👍 765 👎 0


package leetcode.editor.cn;

class DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new DivideTwoIntegers().new Solution();
        solution.divide(10, 3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int divide(int dividend, int divisor) {
            if (dividend == 0) {
                return 0;
            } else if (divisor == 1) {
                return dividend;
            } else if (dividend == divisor) {
                return 1;
            } else if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            } else if (divisor == Integer.MIN_VALUE) {
                return 0;
            }

            // 将所有的正数取相反数，这样就只需要考虑一种情况
            int ans = 0;
            int flag = ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
                    ? 1 : -1;
            dividend = dividend > 0 ? -dividend : dividend;
            divisor = divisor > 0 ? -divisor : divisor;

            // 对于 10/3 这种情况，此时 dividend = -10， divisor = -3
            // 需要在[1, Integer.MAX_VALUE]找到一个最大的非负数Z（为3），使得 Z * divisor >= dividend
            // 如果 mid * divisor >= dividend，那个继续在[mid+1, Integer.MAX_VALUE]继续找
            // 如果 mid * divisor < dividend，那么就继续在[left, mid - 1]继续找
            int left = 1, right = Integer.MAX_VALUE;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
//                boolean check = quickAdd(dividend, divisor, mid);
                boolean check = quickAdd_long(dividend, divisor, mid);
                if (check) { // mid * divisor >= dividend
                    ans = mid;
                    if (mid == Integer.MAX_VALUE) {
                        break;
                    }
                    left = mid + 1;
                } else {    // mid * divisor < dividend
                    right = mid - 1;
                }
            }
            return flag * ans;

        }

        /**
         * 参考 P50 {@link PowxN} 快速幂运算实现 快速和运算
         * @param x
         * @param y
         * @param z
         * @return
         */
        private boolean quickAdd(int x, int y, int z) {
            // x / y = z
            // x 和 y 是负数，z 是正数
            // 判断 z * y >= x 是否成立
//            System.out.println("quickAdd : x = "+x+", y = "+y+", z = "+z);
            int result = 0, add = y;
            while (z != 0) {
                if ((z & 1) != 0) { // 即Z为奇数
                    if (result < x - add) {
                        return false;
                    }
                    result += add;
                }
                if (z != 1) {
                    if (add < x - add) {
                        return false;
                    }
                    add += add;
                }
                z = z >> 1;
            }
            return true;
        }

        private boolean quickAdd_long(int x, int y, int z) {
            long ans = 0, add = y;
            while (z != 0) {
                if (z % 2 == 1) {
                    if (ans < x - add) {
                        return false;
                    }
                    ans += add;
                }
                if (z != 1) {
                    if (add < x - add) {
                        return false;
                    }
                    add += add;
                }
                z /= 2;
            }
            return ans >= x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}