//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
// Related Topics 数组 回溯 
// 👍 743 👎 0


package leetcode.editor.cn;

import java.util.*;

class CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> valueCountList = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            valueCountList = getValueCountList(candidates);
            dfs(target, 0);
            return ans;
        }

        private List<int[]> getValueCountList(int[] candidates) {
            List<int[]> valueCountList = new ArrayList<>();
            for (int num : candidates) {
                int size = valueCountList.size();
                if (valueCountList.isEmpty() || num != valueCountList.get(size - 1)[0]) {
                    valueCountList.add(new int[]{num, 1});
                } else {
                    valueCountList.get(size - 1)[1]++;
                }
            }
            return valueCountList;
        }

        public void dfs(int target, int index) {
            if (target == 0) {
                ans.add(new ArrayList<>(combine));
                return;
            }

            int size = valueCountList.size();
            if (index == size || target < valueCountList.get(index)[0]) {
                return;
            }

            int value = valueCountList.get(index)[0];
            int count = valueCountList.get(index)[1];
            if (target < value) {
                return;
            }

            dfs(target, index + 1);

            // 最多选择相同数字的个数
            int most = Math.min(target / value, count);
            for (int i = 1; i <= most; ++i) {
                combine.add(value);
                dfs(target - i * value, index + 1);
            }
            for (int i = 1; i <= most; ++i) {
                combine.remove(combine.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}