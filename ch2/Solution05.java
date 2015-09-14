package ch2;

import java.util.LinkedList;

public class Solution05 {
	
	/*
	 * sumList adds nodes of a linked list which form a number in reverse
	 * for e.g. l1 = 7->1->6
	 * 			l2 = 5->9->2
	 * will give the output: 2->1->9
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 */
	
	private Node sumList(Node l1, Node l2) {
		Node dummyHead = new Node(0);	// the final result will be added to this list
		Node p = l1, q = l2, curr = dummyHead;	// backup pointers for the three lists
		int carry = 0;					// this keeps track of carry
		while (p != null || q != null) {	// this loop will stop when both list pointers become null
			int x = (p != null) ? p.n : 0;	// takes value from current node of list1 if it exists, otherwise takes 0
			int y = (q != null) ? q.n : 0;	// takes value from current node of list2 if it exists, otherwise takes 0
			int digit = carry + x + y;		// adds the carry and numbers from list1 and list2
			carry = digit / 10;				// if the summed number is >= 10, we need to fetch the carry
			curr.next = new Node(digit % 10);	// add the digit of the summed number as next node of result list
			curr = curr.next;				// update position
			if (p != null) p = p.next;		// move to next node in list1
			if (q != null) q = q.next;		// move to next node in list2
		}
		if (carry > 0) {					// if there is a carry, when all additions have been performed
			curr.next = new Node(carry);	// add it as the last node
		}
		return dummyHead.next;
	}
	
	// FOLLOW-UP:
	
	/*
	 * When the digits are stored in forward order, first we make sure that
	 * the smaller list is padded with zeros so that the wrong digits are 
	 * not added together (padZeros function takes care of that). We also
	 * have a addHelper function which takes the tail elements of the lists
	 * repeatedly, adds them together and forwards the carry. These additions
	 * are added to the front of a result list.
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 */
	
	private LinkedList<Integer> padZeros(LinkedList<Integer> head, int no){	// adds no number of zeros at the end of a list
		for(int i=0;i<no;i++){
			head.addLast(0);
		}
		return head;	
	}
	
	private LinkedList<Integer> sumListFollowup(LinkedList<Integer> l1, LinkedList<Integer> l2){
		int len1 = l1.size();	// get the size of both lists
		int len2 = l2.size();
		
		if(len1<len2){		// identify which list needs to be padded with zeros
			l1 = padZeros(l1, len2-len1);
		}
		else if(len2<len1){
			l2 = padZeros(l2, len1-len2);
		}
		
		LinkedList<Integer> resultList = new LinkedList<Integer>();		// create a list to store final output
		resultList = addHelper(l1, l2, resultList);						// add lists l1 and l2
		return resultList;
	}
	
	private LinkedList<Integer> addHelper(LinkedList<Integer> l1, LinkedList<Integer> l2, LinkedList<Integer> resultHead){
		int carry = 0;	// initialize carry
		while(!l1.isEmpty() && !l2.isEmpty()){	// until both lists have nodes remaining
			int sum = l1.pollLast() + l2.pollLast() + carry;	// calculate sum of digits and previous carry
			carry = sum / 10;			// get current carry
			int digit = sum % 10;		// get the digit to add to result list
			resultHead.addFirst(digit);
		}
		if(carry > 0)					// at the end if there is still a carry
			resultHead.addFirst(carry);	// add it to the starting of result list
		return resultHead;
	}
	
	public static void main(String[] args) {
		Solution05 s5 = new Solution05();
		
		Node list1 = new Node(7);
		list1.add(1);
		list1.add(6);
		
		Node list2 = new Node(5);
		list2.add(9);
		list2.add(2);
		
		System.out.print("List1: ");
		list1.printLList();
		
		System.out.print("List2: ");
		list2.printLList();
		
		System.out.print("Sum: ");
		s5.sumList(list1, list2).printLList();
		
		// Follow up:
		
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		list3.add(7);
		list3.add(1);
		list3.add(9);
		
		LinkedList<Integer> list4 = new LinkedList<Integer>();
		list4.add(2);
		list4.add(9);
		
		System.out.println("  \nList3: " + list3.toString().replace("[", "").replace("]", "").replace(", ","->"));
		
		System.out.println("List4: " + list4.toString().replace("[", "").replace("]", "").replace(", ","->"));
		
		System.out.println("Sum: " + s5.sumListFollowup(list3, list4).toString().replace("[", "").replace("]", "").replace(", ","->"));

	}

}
