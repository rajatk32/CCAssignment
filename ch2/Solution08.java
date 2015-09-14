package ch2;

public class Solution08 {
	
	/*
	 * Assuming the list that is given as a parameter has a loop in it,
	 * we can find out the first node at the beginning of the loop by 
	 * using two pointers. We move one of the pointers to the next position
	 * and the other to next.next position (twice as fast as the other). These
	 * pointers will then meet at a certain point. From here we move one of the
	 * pointers back to the head node and advance both pointers 1 position 
	 * ahead at a time. They will meet again at the start node of the loop.
	 * 
	 * Time complexity: O(n)
	 * Space complexity: O(1)
	 */
	
	private Node loopDetection(Node head){
		Node slow = head, fast = head;		// set both pointers to head initially
		
		while(fast.next!=null){				// until fast reaches the end node
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast)				// if slow and fast meet, then we know there is a loop
				break;
		}
		
		if(fast.next == null)				// if we have reached the end of list and there are no
			return null;					// further elements, return null
		
		slow = head;						// reset slow to head
		while(slow!=fast){					// until both slow and fast pointers meet
			slow = slow.next;				// advance them one step at a time
			fast = fast.next;
		}
		return fast;						// return the intersection node
	}
	
	public static void main(String[] args) {
		Solution08 s8 = new Solution08();
		Node one = new Node(1);
		Node two = new Node(2);
		Node three = new Node(3);
		Node four = new Node(4);
		Node five = new Node(5);
		Node six = new Node(6);
		Node seven = new Node(7);
		
		one.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		five.next = six;
		six.next = seven;
		seven.next = four;		// here is the loop
		
		Node result = s8.loopDetection(one);
		if(result!=null)
			System.out.println(result.n);
		
	}

}
