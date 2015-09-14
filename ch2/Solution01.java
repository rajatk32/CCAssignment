package ch2;

import java.util.Hashtable;

public class Solution01 {
	
	/*
	 * removeDuplicatesWithBuffer function uses a HashTable to keep
	 * track of duplicates
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 */
	
	private Node removeDuplicatesWithBuffer(Node head) {
		if (head == null)	// if list is empty
			return null;
		if (head.next == null)	// if list contains only one node
			return head;
		Hashtable hs = new Hashtable();
		Node previous = head;
		Node current = head;
		while (current != null) {	// until there are no more nodes left
			if (hs.containsKey(current.n)) {	// if hash table contains node value
				previous.next = current.next;	// remove this node by breaking links
				current = previous;
			} else {
				hs.put(current.n, true);		// else add node value to hash table  
				previous = current;
			}
			current = current.next;			// advance to next node
		}
		return head;						// return head of list
	}
	
	/*
	 * removeDuplicatesWithoutBuffer function removes duplicates
	 * without using any additional data structures
	 * Time complexity: O(n^2)
	 * Space complexity: O(1)
	 */
	
	private Node removeDuplicatesWithoutBuffer(Node head) {
		// we are going to use three pointers: leftrunner, right, previous
		// leftrunner: to iterate all nodes prior to right pointer

		// after leftrunner reaches upto right pointer, right pointer
		// is incremented by one position and leftrunner is again placed
		// at 1st position

		// for e.g. If right pointer is at position 3, leftrunner begins
		// from 1st position compares it with right, and then moves to
		// 2nd position and again compares it with right...after this
		// leftrunner terminates when it reaches the 3rd position where
		// right pointer is...In the next iteration, right pointer is
		// placed at 4th position and leftrunner begins again at 1st position

		// previous always points to the node which is just before right
		// if a duplicate is found, i.e. the node at right is a duplicate
		// previous's next is pointed to right's next to eliminate right

		if (head == null)
			return null;
		if (head.next == null)
			return head;
		Node leftrunner = head, previous = head, right = head.next;
		while (right != null) {
			leftrunner = head;
			while (leftrunner != right) {
				if (leftrunner.n == right.n) { // if duplicate is found
					previous.next = right.next;
					right = previous;
				} else {
					leftrunner = leftrunner.next;
				}
			}
			previous = right;
			right = right.next;
		}
		return head;
	}
	
	public static void main(String[] args) {
		Solution01 s1 = new Solution01();
		Node head = new Node(1);
		head.add(2);
		head.add(1);
		head.add(1);
		System.out.println("Linked List before:");
		head.printLList();
		Node result = s1.removeDuplicatesWithoutBuffer(head);
		System.out.println("Linked List after removing duplicates (without buffer):");
		result.printLList();
		Node result2 = s1.removeDuplicatesWithBuffer(head);
		System.out.println("Linked List after removing duplicates (with buffer):");
		result2.printLList();
	}

}
