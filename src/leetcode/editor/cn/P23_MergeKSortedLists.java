//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 1593 👎 0


package leetcode.editor.cn;

import leetcode.editor.cn.base.ListNode;

class MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
//            return mergeKListsOrderly(lists);
            return mergeKLists(lists, 0, lists.length - 1);
        }

        /**
         * 有序组合
         *
         * @param lists
         * @return
         */
        private ListNode mergeKListsOrderly(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            ListNode node = lists[0];
            for (int i = 1; i < lists.length; i++) {
                node = mergeTwoLists(node, lists[i]);
            }
            return node;
        }

        /**
         * 分治组合
         *
         * @return
         */
        private ListNode mergeKLists(ListNode[] lists, int l, int r) {
            if (l == r) {
                return lists[l];
            }
            if (l > r) {
                return null;
            }
            int middle = (l + r) / 2;
            return mergeTwoLists(
                    mergeKLists(lists, l, middle),
                    mergeKLists(lists, middle + 1, r)
            );
        }

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            }
            ListNode node = new ListNode();
            ListNode ans = node;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    node.val = l1.val;
                    l1 = l1.next;
                } else {
                    node.val = l2.val;
                    l2 = l2.next;
                }
                node.next = new ListNode();
                node = node.next;
            }
            if (l1 == null) {
                node.val = l2.val;
                node.next = l2.next;
            } else {
                node.val = l1.val;
                node.next = l1.next;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}