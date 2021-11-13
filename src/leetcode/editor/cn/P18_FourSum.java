//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b
//], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 
// Related Topics 数组 双指针 排序 
// 👍 1000 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FourSum {
    public static void main(String[] args) {
        Solution solution = new FourSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            List<List<Integer>> lists = new ArrayList<>();
            int size = nums.length;
            if (size < 4) {
                return lists;
            }
            for (int i = 0; i < size; i++) {
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                for (int j = i+1; j < size; j++) {
                    if (j > i+1 && nums[j] == nums[j-1]) {
                        continue;
                    }
                    int l = size - 1;
                    for (int k = j + 1; k < size; k++) {
                        if (k > j + 1 && nums[k] == nums[k-1]) {
                            continue;
                        }
                        while (k < l && nums[i] + nums[j] + nums[k] + nums[l] > target) {
                            l--;
                        }
                        if (k == l) {
                            break;
                        } else if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            list.add(nums[l]);
                            lists.add(list);
                        }
                    }
                }

            }
            return lists;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}