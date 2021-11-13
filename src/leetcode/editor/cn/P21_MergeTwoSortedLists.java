//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 2025 👎 0


package leetcode.editor.cn;

import leetcode.editor.cn.base.ListNode;

class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
        ListNode l1 = ListNode.createListNode(new int[]{});
        ListNode l2 = ListNode.createListNode(new int[]{});
        ListNode.printfListNode(l1);
        ListNode.printfListNode(l2);
        ListNode l3 = solution.mergeTwoLists(l1, l2);
        ListNode.printfListNode(l3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
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