//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。） 
// Related Topics 递归 链表 
// 👍 1114 👎 0


package leetcode.editor.cn;

import leetcode.editor.cn.base.ListNode;

class SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new SwapNodesInPairs().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public ListNode swapPairs(ListNode head) {
//            return swapPairsRecursion(head);// 递归
            return swapPairsOrderly(head);// 迭代
        }

        private ListNode swapPairsRecursion(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = head.next;
            head.next = swapPairsRecursion(newHead.next);
            newHead.next = head;
            return newHead;
        }

        /**
         * 迭代：按顺序，每三个ListNode为一组进行替换
         * @param head
         * @return
         */
        private ListNode swapPairsOrderly(ListNode head) {
            ListNode node = new ListNode(0, head);
            ListNode ans = node;
            while (node.next != null && node.next.next != null) {
                ListNode a = node.next;
                ListNode b = a.next;
                ListNode c = b.next;
                node.next = b;
                b.next = a;
                a.next = c;
                node = node.next.next;
            }
            return ans.next;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}