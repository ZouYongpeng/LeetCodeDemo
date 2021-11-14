//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 6998 👎 0


package leetcode.editor.cn;

import leetcode.editor.cn.base.ListNode;

import static leetcode.editor.cn.base.ListNode.createListNode;
import static leetcode.editor.cn.base.ListNode.printfListNode;

class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        doCalculate(solution, new int[]{2,4,3}, new int[]{5,6,4});
        doCalculate(solution, new int[]{0}, new int[]{0});
        doCalculate(solution, new int[]{1}, new int[]{9,9,9});
        doCalculate(solution, new int[]{9,9,9,9,9,9,9}, new int[]{9,9,9,9});
    }

    private static void doCalculate(Solution solution, int[] firstArray, int[] secondArray) {
        ListNode l1 = createListNode(firstArray);
        ListNode l2 = createListNode(secondArray);
        printfListNode(l1);
        printfListNode(l2);
        ListNode l3 = solution.addTwoNumbers(l1, l2);
        printfListNode(l3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode node = new ListNode();
            ListNode ans = node;
            int ext = 0;
            while (l1 != null || l2 != null) {
                ListNode newNode = add(l1, l2, ext);
                l1 = l1 != null ? l1.next : null;
                l2 = l2 != null ? l2.next : null;
                ext = newNode.next.val;
                node.val = newNode.val;
                if (l1 != null || l2 != null) {
                    node.next = new ListNode();
                } else if (ext != 0) {
                    node.next = new ListNode(ext);
                } else {
                    node.next = null;
                }
                node = node.next;
            }
            return ans;
        }

        public ListNode add(ListNode l1, ListNode l2, int ext) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + ext;
            int val = sum % 10;
            ListNode next = new ListNode(sum / 10);
            return new ListNode(val, next);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}