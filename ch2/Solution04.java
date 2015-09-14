package ch2;

/*
 * We are going to create two new linked lists, one containing
 * elements less than x and one for elements greater than x. In
 * one pass we can add the elements to these lists accordingly and
 * then join them together.
 * Time complexity: O(n)
 * Space complxity: O(n)
 */

public class Solution04 {
	
	private Node partition(Node head, int x) {
        if (head == null || head.next == null){	// if list is null or contains only one element
            return head;
        }
        
        // For both lists we are going to keep two pointers:
        // one to its head and one for iterating
        Node leftHead = new Node(0);	// to make things simple we initialize the lists with a dummy value 0
        Node leftStart = leftHead;
        Node rightHead = new Node(0);
        Node rightStart = rightHead;
        
        while (head != null){	// until the original list is not fully iterated
           if (head.n < x){		// if current node value is less than x, add the node to
        	   leftStart.next = head;	// left list
        	   leftStart = leftStart.next;
           } else {				// else if it is equal or greater than x, add it to right list
        	   rightStart.next = head;
        	   rightStart = rightStart.next;
           }
           head = head.next;	// go to the next node in original list
        }
        
        
        rightStart.next = null;	// at the end, set right list's last node's next to null
        leftStart.next = rightHead.next;	// and set the left list's last node to right list's head's next (bcoz we used a dummy node)
        return leftHead.next;	// return the left list's head's next (bcoz we used a dummy node)
    }
	
	public static void main(String[] args) {
		Solution04 s4 = new Solution04();
		Node head = new Node(3);
		head.add(5);
		head.add(8);
		head.add(5);
		head.add(10);
		head.add(2);
		head.add(1);
		System.out.print("List before: ");
		head.printLList();
		System.out.print("List after: ");
		s4.partition(head, 5).printLList();
	}

}
