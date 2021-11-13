//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。 
//
// 返回这三个数的和。 
//
// 假定每组输入只存在恰好一个解。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0], target = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 1000 
// -1000 <= nums[i] <= 1000 
// -104 <= target <= 104 
// 
// Related Topics 数组 双指针 排序 
// 👍 944 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

class ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new ThreeSumClosest().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int size = nums.length;
            int minSum = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < size; i++) {
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                int j = i + 1;
                int k = size - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum < target) {
                        if (Math.abs(target - sum) < Math.abs(target - minSum)) {
                            minSum = sum;
                        }
                        j++;
                        while (j < k && nums[j] == nums[j-1]) {
                            j++;
                        }
                    } else if (sum == target) {
                        return target;
                    } else {
                        if (Math.abs(target - sum) < Math.abs(target - minSum)) {
                            minSum = sum;
                        }
                        k--;
                        while (j < k && k < size - 1 && nums[k] == nums[k+1]) {
                            k--;
                        }
                    }
                }
            }
            return minSum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}