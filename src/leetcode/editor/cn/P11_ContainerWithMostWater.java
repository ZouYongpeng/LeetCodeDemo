//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：height = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：height = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 2 <= n <= 105 
// 0 <= height[i] <= 104 
// 
// Related Topics 贪心 数组 双指针 
// 👍 2908 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

class ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
        solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7});
        solution.maxArea(new int[]{1,1});
        solution.maxArea(new int[]{4,3,2,1,4});
        solution.maxArea(new int[]{1,2,1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            return maxArea_DoublePointer(height);
//            return maxArea_DP(height);
        }

        private int maxArea_DoublePointer(int[] height) {
            int ans = 0;
            int l = 0;
            int r = height.length - 1;
            while (l < r) {
                ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
                if (height[l] < height[r]) {
                    l++;
                } else {
                    r--;
                }
            }
            return ans;
        }

        /**
         * 因为 0 <= height[i] <= 10^4，所以创建dp数组内存会超出限制
         * @param height
         * @return
         */
        private int maxArea_DP(int[] height) {
            int size = height.length;
            if (size < 2) {
                return 0;
            }
            int[][] dp = new int[size][size];
            for (int i = size - 2; i >= 0; i--) {
                for (int j = i + 1; j < size; j++) {
                    dp[i][j] = max(
                            (j - i) * Math.min(height[i], height[j]),
                            dp[i+1][j],
                            dp[i][j-1]
                    );
                }
            }
            System.out.println("maxArea : "+ Arrays.toString(height)+" -> "+dp[0][size-1]);
            return dp[0][size-1];
        }

        private int max(int a, int b, int c) {
            return Math.max(a, Math.max(b, c));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}