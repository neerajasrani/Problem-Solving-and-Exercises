/**
 * Created by Neeraj Asrani on 12/27/16.
 * This set of Linked List questions was received as a handout in a Tech interview meetup.
 * All problems are solved, Method names are self explanatory to the problems being solved.
 * In addition you will see java doc comments in the comments section
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
        while (true) {
            if (fast_ptr !=null && fast_ptr.next != null) {
                fast_ptr = fast_ptr.next.next;
                slow_ptr = slow_ptr.next;
            } else {
                break;
            }
            if (fast_ptr==slow_ptr) {
                return true;
            }
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
        while (true) {
            if (fast_ptr.next!=null && fast_ptr.next.next!=null) {
                fast_ptr = fast_ptr.next.next;
                slow_ptr = slow_ptr.next;
            }
            else {
                break;
            }
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
        Node index_ptr = head;
        Node prev_to_index_ptr = head;
        Node current_ptr = head;
        while (current_ptr != null) {
            while (current_ptr != null && hops_until_null < index) {
                current_ptr = current_ptr.next;
                hops_until_null++;
            }
            if (hops_until_null == index) {
                prev_to_index_ptr = index_ptr;
                index_ptr = current_ptr;
                hops_until_null = 0;
            } else {
                break;
            }
        }
        if (prev_to_index_ptr == head && index_ptr == head) { // edge case if n > size of Linked List
            return null;
        }
        current_ptr = prev_to_index_ptr;
        for (int i = 0; i < hops_until_null; i++) {
            current_ptr = current_ptr.next;
        }
        return current_ptr.value;
    }

    /**
     * Method to delete node at a index position.
     * Add a method to delete first node in the linkedList
     * Add a method to delete last node in the LinkedList
     * Add a method to delete node at index
     * @param index
     */
    void deleteNodeAt(int index) { // assuming 0 "zero" based subsequence of index
        if (head == null) {
            return;
        }
        Node fast_ptr = head;
        Node prev_to_fast_ptr = head;
        Node prev_to_prev_to_fast_ptr = head;
        Node slow_ptr = head;
        int c = 0;

        if (index==0) { // if index = 0
            head = head.next;
        }
        while (fast_ptr.next != null && fast_ptr.next.next != null && c < index) {
//            if (fast_ptr.next !=null && fast_ptr.next!=null && fast_ptr.next.next!=null)
            prev_to_prev_to_fast_ptr = fast_ptr;
            prev_to_fast_ptr = fast_ptr.next;
            fast_ptr = fast_ptr.next.next;
            c += 2;
        }
        if (c == index) {
            if (head == tail) { // edge case if there is only one element in the list and we delete it
                head = tail = null;
            }

            if (fast_ptr == tail) { // edge case when fast_ptr is the last element
                prev_to_fast_ptr.next = null;
                tail = prev_to_fast_ptr;
            }
            prev_to_fast_ptr.next = fast_ptr.next;
            fast_ptr = null; // For GC
        } else { // c > index
            prev_to_prev_to_fast_ptr.next = fast_ptr;
            prev_to_fast_ptr = null;
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
        // A cool way to initialize that makes code a little unreadable
        this.head = new Node(new Node(new Node(new Node(new Node(null, "e"), "d"), "c"), "b"), "a");
        Node a = new Node (null, "a");
        Node b = new Node (null, "b");
        Node c = new Node (null, "c");
        Node d = new Node (null, "d");
        Node e = new Node (null, "e");
        this.head=a; // head points to beignning of list node "a"
        a.next=b;
//        b.next=c;
//        c.next=b;
//        d.next=e; // node with cycle

        Node newHead = head;

        while (newHead.next != null) {
            newHead = newHead.next;
        }
        tail = newHead;


    }

    void initializeLinkedListWithCycle() {
        Node a = new Node (null, "a");
        Node b = new Node (null, "b");
        Node c = new Node (null, "c");
        Node d = new Node (null, "d");
        Node e = new Node (null, "e");
        this.head=a; // head points to beignning of list node "a"
        a.next=b;
        b.next=c;
        c.next=b;
        d.next=e; // node with cycle
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
        System.out.println(result);
    }

}

public class LinkedListQuestions {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedListWithCycle = new LinkedList();

        // Linked List With Cycle
        linkedListWithCycle.initializeLinkedListWithCycle();
        System.out.println("isCyclePresent = [" + linkedListWithCycle.isCyclePresent() + "]");

        // Linked List
        linkedList.initializeLinkedList();
        linkedList.insertBeforeHead("ba");
        linkedList.insertBeforeHead("bba");
        linkedList.insertAfterTail("ae");
        linkedList.insertAfterTail("af");
        System.out.println("isCyclePresent = [" + linkedList.isCyclePresent() + "]");
        linkedList.print();
        System.out.println("middle node with a even numbered LL size = [" + linkedList.findMiddleNode() + "]");
        linkedList.insertAfterTail("aaf");
        linkedList.insertAfterTail("aaaf");
        linkedList.insertAfterTail("aaaaf");
        linkedList.insertBeforeHead("bbbf");
        linkedList.insertBeforeHead("cbbf");
        linkedList.print();
        System.out.println("middle node with a odd numbered LL size = [" + linkedList.findMiddleNode() + "]");
        System.out.println("11th fron end of LL size = [" + linkedList.findNthNodeFromEnd(11) + "]");
        System.out.println("Deleting 5th node of LL, here it looks like after deletion");
        linkedList.deleteNodeAt(5);
        linkedList.print();
        linkedList.initializeLinkedList();
        linkedList.insertAfterTail("c");
        linkedList.insertAfterTail("d");
        linkedList.print();
        System.out.println("middle node with a even numbered LL size = [" + linkedList.findMiddleNode() + "]");
        System.out.println("4th fron end of LL size = [" + linkedList.findNthNodeFromEnd(4) + "]");
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
