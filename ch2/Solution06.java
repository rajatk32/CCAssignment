package ch2;

import java.util.Stack;

public class Solution06 {
	
	/*
	 * We are going to use two pointers fast and slow to find out the
	 * middle of the linked list. Fast is going to be twice as fast as
	 * slow. Simultaneously we are also going to push the elements read
	 * by slow in a stack. Next we will compare further elements in the
	 * stack till the later half of the linked list to check if it is
	 * a palindrome.
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 * 
	 */
	
	private boolean isPalindrome(Node head) {
	    Node fast = head;	// first set both pointers to head
	    Node slow = head;
	    
	    Stack<Integer> st = new Stack<Integer>();	// initialize stack
	    
	    while(fast!=null && fast.next!=null){	// we check fast!=null so that we don't go past the last node;
	    										// fast.next is checked for cases such as when there is only one node in the list
	    	st.push(slow.n);					// add element to stack
	    	slow = slow.next;					// advance slow and fast pointers
	    	fast = fast.next.next;
	    }
	    
	    if(fast!=null){		// for even-length list, fast will stop at the last node and since there is not a definite center
	    	slow = slow.next;	// slow needs to advance one step
	    }
	    
	    while(slow!=null){	// iterate the second half of the list
	    	if(slow.n != st.pop())	// if the node values don't match
	    		return false;		// return false straight away
	    	slow = slow.next;
	    }
	    return true;		// if all values matched, return true
	}
	
	public static void main(String[] args) {
		Solution06 s6 = new Solution06();
		Node head = new Node(1);
		head.add(2);
		head.add(2);
		head.add(1);
		System.out.print("List: ");
		head.printLList();
		System.out.print("is a palindrome? ");
		System.out.println(s6.isPalindrome(head));
	}

}
