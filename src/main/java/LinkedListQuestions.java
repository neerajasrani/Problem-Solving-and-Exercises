/**
 * Created by nickamac on 12/27/16.
 */
class LinkedList {

    /**
     * Head of Linked List
     */
    private Node head;
    /**
     * Tail of Linked List
     */
    private Node tail;

    /**
     * Detect cycle in a Linked List
     */
    boolean isCyclePresent() {
        if (head == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        Node fast_ptr = head;
        Node slow_ptr = head;
        while (slow_ptr != null) {
            fast_ptr = fast_ptr.next.next;
            slow_ptr = slow_ptr.next;
        }
        if (slow_ptr == null) {
            return false;
        } else if (fast_ptr == slow_ptr) {
            return true;
        }
        return false;
    }

    /**
     * Reverse Linked List : Iterative approach
     */
    void ReverseIterLL() {

    }

    /**
     * Reverse Linked List : Recursive approach
     */
    void ReverseRecurLL() {

    }


    /**
     * Find middle Node in the Linked List
     */
    String findMiddleNode() {
        if (head == null) {
            return null;
        }
        if (head.next == null) { // if head==tail
            return head.value;
        }
        Node fast_ptr = head;
        Node slow_ptr = head;
        while (fast_ptr.next != null || fast_ptr.next.next != null) {
            fast_ptr = fast_ptr.next.next;
            slow_ptr = slow_ptr.next;
        }
        return slow_ptr.value;
    }

    /**
     * Find nth node from index
     *
     * @param index
     */

    String findNthNodeFromEnd(int index) {
        if (head == null) {
            return null;
        }
        int hops_until_null = 0;
        Node index_th_ptr = head;
        Node prev_index_th_ptr = head;
        Node current_ptr = head;
        while (current_ptr != null) {
            while (current_ptr == null || hops_until_null <= index) {
                current_ptr = current_ptr.next;
                hops_until_null++;
            }
            if (hops_until_null == index) {
                prev_index_th_ptr = index_th_ptr;
                index_th_ptr = current_ptr;
                hops_until_null = 0;
            } else {
                break;
            }
        }
        if (prev_index_th_ptr == head && index_th_ptr == head) { // edge case if n > size of Linked List
            return null;
        }
        current_ptr = prev_index_th_ptr;
        for (int i = 0; i <= hops_until_null; i++) {
            current_ptr = current_ptr.next;
        }
        return current_ptr.value;
    }

    /**
     * Method to delete node at a index position.
     * Add a method to delete first node in the linkedList
     * Add a method to delete last node in the LinkedList
     * Add a method to delete node at index
     */
    void deleteNodeAt(int index) { // assuming 0 "zero" based subsequence of index
        if (head == null) {
            return;
        }
        Node fast_ptr = head;
        Node prev_fast_ptr = head;
        Node prev_prev_fast_ptr = head;
        Node slow_ptr = head;
        int c = 0;
        while (fast_ptr.next != null || fast_ptr.next.next != null || c >= index) {
            fast_ptr = fast_ptr.next.next;
            prev_fast_ptr = fast_ptr.next;
            prev_prev_fast_ptr = fast_ptr;
            c += 2;
        }
        if (c == index) {
            if (head == tail) { // edge case if there is only one element in the list and we delete it
                head = tail = null;
            }

            if (fast_ptr == tail) { // edge case when fast_ptr is the last element
                prev_fast_ptr.next = null;
                tail = prev_fast_ptr;
            }
            prev_fast_ptr.next = null;
            fast_ptr = null; // For GC
        } else { // c > index
            prev_prev_fast_ptr.next = fast_ptr;
            prev_fast_ptr = null;
        }

    }

    void insertBeforeHead(String value) {
        Node newHead = new Node(null, value);
        if (head == null && tail == null) {
            head = new Node(null, "head_dummy_value");
            tail = new Node(null, "tail_dummy_value");
            head = newHead;
            tail = newHead;
            newHead.next = null;
            return;
        }
        newHead.next = head;
        head = newHead;
    }

    void insertAfterTail(String value) {
        Node newTail = new Node(null, value);
        if (head == null && tail == null) {
            head = new Node(null, "head_dummy_value");
            tail = new Node(null, "tail_dummy_value");
            head = newTail;
            newTail.next = null;
            return;
        }
        tail.next = newTail;
        tail = newTail;
    }

    String valueAt(int index) {
        if (index < 0) {
            return null; //edge case
        }
        Node h = new Node(null, "d");
        h = head;
        int c = 0;
        while (h != null && c != index) {
            h = h.next;
            c++;
        }
        if (h == null) {
            return null; // value not found and end of tree has been traversed
        }
        return h.value; //value found , return the value
    }

    void initializeLinkedList() {
        this.head = new Node(new Node(new Node(new Node(new Node(null, "e"), "d"), "c"), "b"), "a");
    }

    void initializeTail() {
        Node newHead = head;

        while (newHead.next != null) {
            newHead = newHead.next;
        }
        tail = newHead;
    }

    void print() {
        if (head == null) {
            return;
        }
        StringBuffer result = new StringBuffer();
        Node newHead = head;
        while (newHead.next != null) {
            result.append(newHead.value).append("->");
            newHead = newHead.next;
        }
        result.append(newHead.value);
        System.out.print(result);
    }

}

public class LinkedListQuestions {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.initializeLinkedList();
        linkedList.initializeTail();
        linkedList.insertBeforeHead("ba");
        linkedList.insertBeforeHead("bba");
        linkedList.insertAfterTail("ae");
        linkedList.insertAfterTail("af");
        linkedList.print();
    }
}

class Node {
    Node next;
    String value;

    Node(Node next, String value) {
        this.next = next;
        this.value = value;
    }
}
