package org.javaboy.list;

/**
 * 删除链表中的重复节点
 * @author supanpan
 * @date 2024/08/04
 */
public class RemoveDuplicationList {

    /**
     * 哑节点: dummy 节点用于简化边界条件的处理，它总是指向结果链表的头节点。
     * 遍历原链表: 使用 curr 指针遍历原始链表，使用 prev 指针构建新链表。
     * 跳过重复元素: 在内部 while 循环中跳过所有连续的重复元素，并使用一个布尔变量 duplicate 来标识当前是否有重复元素。
     * 连接非重复元素: 如果当前元素没有重复，则将其连接到新链表中。
     * 切断连接: 最后将 prev.next 设置为 null，以切断新链表和原始链表之间的连接。
     * 返回结果链表: 返回新链表的头节点 dummy.next。
     *
     */
    public static ListNode removeDuplication(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0); // 创建一个哑节点作为新链表的头部
        dummy.next = head;
        ListNode prev = dummy; // prev用于遍历新链表
        ListNode curr = head; // curr用于遍历原始链表

        while (curr != null) {
            boolean duplicate = false;
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next; // 跳过所有重复的元素
                duplicate = true;
            }

            if (duplicate) {
                // 发现重复，跳过最后一个重复的元素
                curr = curr.next;
                continue;
            }

            // 没有重复，将curr添加到新链表中
            prev.next = curr;
            prev = prev.next;

            // 向前移动curr
            curr = curr.next;
        }

        // 切断新链表和原始链表的连接
        prev.next = null;

        // 返回新链表的头部（跳过哑节点）
        return dummy.next;
    }

    public static void main(String[] args) {
        RemoveDuplicationList solution = new RemoveDuplicationList();

        // 创建测试链表: 1->2->3->3->4->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        // 移除重复元素
        ListNode result = solution.removeDuplication(head);

        // 打印结果链表
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 期望输出: 1 2 5
    }
}
