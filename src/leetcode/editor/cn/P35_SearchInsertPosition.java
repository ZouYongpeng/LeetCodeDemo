//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 请必须使用时间复杂度为 O(log n) 的算法。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,3,5,6], target = 5
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,3,5,6], target = 2
//输出: 1
// 
//
// 示例 3: 
//
// 
//输入: nums = [1,3,5,6], target = 7
//输出: 4
// 
//
// 示例 4: 
//
// 
//输入: nums = [1,3,5,6], target = 0
//输出: 0
// 
//
// 示例 5: 
//
// 
//输入: nums = [1], target = 0
//输出: 0
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// nums 为无重复元素的升序排列数组 
// -104 <= target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 1183 👎 0


package leetcode.editor.cn;

class SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new SearchInsertPosition().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int searchInsert(int[] nums, int target) {
            int ans = 0;
            int size = nums.length;
            if (size == 0) {
                return ans;
            }

            int l = 0, r = size - 1;
            while (l <= r) {
                if (nums[l] >= target) {
                    return l;
                } else if (nums[r] == target) {
                    return r;
                } else if (nums[r] < target) {
                    return r+1;
                }
                int middle = (l + r) / 2;
                if (nums[middle] < target) {
                    l = middle + 1;
                } else if (nums[middle] > target) {
                    r = middle - 1;
                } else {
                    return middle;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}