package leetcode.editor.cn.base;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode createListNode(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        ListNode listNode = new ListNode();
        ListNode node = listNode;
        for (int i = 0; i < nums.length; i++) {
            node.val = nums[i];
            node.next = i == (nums.length - 1) ? null : new ListNode();
            node = node.next;
        }
        return listNode;
    }

    public static void printfListNode(ListNode listNode) {
        StringBuilder stringBuilder = new StringBuilder();
        while (listNode != null) {
            stringBuilder.append(listNode.val);
            stringBuilder.append(listNode.next == null ? "" : "->");
            listNode = listNode.next;
        }
        System.out.println("printfListNode : "+stringBuilder.toString());
    }

}
