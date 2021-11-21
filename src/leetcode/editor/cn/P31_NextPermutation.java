//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 
// 👍 1414 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

class NextPermutation {
    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void nextPermutation(int[] nums) {
            int size = nums.length;
            if (size <= 1) {
                return;
            }

            // 首先从后向前查找第一个顺序对 (i,i+1)，满足 nums[i] < nums[i+1]。
            // 这样「较小数」即为 nums[i]。此时 [i+1,n) 必然是下降序列。
            int i = size - 2;
            while (i >= 0 && nums[i] >= nums[i+1]) {
                i--;
            }

            // 如果找到了顺序对，那么在区间 [i+1,n) 中从后向前查找第一个元素 j 满足 nums[i] < nums[j]。
            // 这样「较大数」即为 nums[j]。
            if (i >= 0) {
                int j = size - 1;
                while (j > i && nums[i] >= nums[j]) {
                    j--;
                }
                swap(nums, i, j);
            }
            // 此时 [i+1,n) 必为降序，需要变为升序
            reverse(nums, i + 1, size - 1);
        }

        private void reverse(int[] nums, int start, int end) {
            int size = nums.length;
            if (start >= 0 && end > start && end < size) {
                while (start < end) {
                    swap(nums, start, end);
                    start++;
                    end--;
                }
            }
        }

        private void swap(int[] nums, int a, int b) {
            int size = nums.length;
            if (a != b && a >= 0 && a < size && b >= 0 && b < size) {
                int temp = nums[a];
                nums[a] = nums[b];
                nums[b] = temp;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}