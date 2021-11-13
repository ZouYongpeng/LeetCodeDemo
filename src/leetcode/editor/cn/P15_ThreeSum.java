//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 3964 👎 0


package leetcode.editor.cn;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> lists = new ArrayList<>();
            int size = nums.length;
            if (size < 3) {
                return lists;
            }
            for (int i = 0; i < size; i++) {
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                int k = size - 1;
                for (int j = i + 1; j < size; j++) {
                    if (j > i + 1 && nums[j] == nums[j-1]) {
                        continue;
                    }
                    while (j < k && nums[i] + nums[j] + nums[k] > 0) {
                        k--;
                    }
                    if (j == k) {
                        break;
                    } else if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        lists.add(list);
                    }
                }
            }
            return lists;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}