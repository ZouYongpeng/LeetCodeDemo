//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 递归 链表 
// 👍 1358 👎 0


package leetcode.editor.cn;

import leetcode.editor.cn.base.ListNode;

import java.util.List;

class ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (k <= 1) {
                return head;
            }
            ListNode hair = new ListNode(0, head);
            ListNode pre = hair;

            while (head != null) {
                ListNode tail = pre;
                for (int i = 0; i < k; i++) {
                    tail = tail.next;
                    if (tail == null) {
                        return hair.next;
                    }
                }
                ListNode next = tail.next;
                ListNode[] reverseNodes = reverse(head, tail);
                head = reverseNodes[0];
                tail = reverseNodes[1];

                //把子链表重新接回原链表
                pre.next = head;
                tail.next = next;
                pre = tail;
                head = tail.next;
            }
            return hair.next;
        }

        private ListNode[] reverse(ListNode head, ListNode tail) {
            ListNode p = head;
            ListNode prev = tail.next;
            while (prev != tail) {
                ListNode next = p.next;
                p.next = prev;
                prev = p;
                p = next;
            }
            return new ListNode[]{tail, head};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}