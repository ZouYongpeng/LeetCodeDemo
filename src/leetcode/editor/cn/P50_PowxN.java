//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
// Related Topics 递归 数学 
// 👍 787 👎 0


package leetcode.editor.cn;

class PowxN {
    public static void main(String[] args) {
        Solution solution = new PowxN().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double myPow(double x, int n) {
            long N = n;
//            return n > 0 ? quickMul_DiGui(x, N) : (1.0 / quickMul_DiGui(x, -N));
            return n > 0 ? quickMul_DieDai(x, N) : (1.0 / quickMul_DieDai(x, -N));
        }

        private double quickMul_DiGui(double x, long n) {
            if (n == 0) {
                return 1.0;
            }
            double y = quickMul_DiGui(x, n / 2);
            return (n % 2 == 0) ? (y * y) : (y * y * x);
        }

        private double quickMul_DieDai(double x, long n) {
            double ans = 1.0;
            double contributeX = x;
            while (n > 0) {
                if (n % 2 == 1) {
                    ans *= contributeX;
                }
                contributeX *= contributeX;
                n /= 2;
            }
            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}