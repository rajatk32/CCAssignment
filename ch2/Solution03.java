package ch2;

public class Solution03 {
	
	/*
	 * An elegant solution here is to backup the next node of the node
	 * that we are given, copy its data to the given node and point it to
	 * next's next node
	 * Time complexity: O(1)
	 * Space complexity: O(1)
	 */
	
	public static boolean deleteNode(Node n) {
		if (n == null || n.next == null) {	// if list is empty or has only one node
			return false;
		}
		Node next = n.next;
		n.n = next.n;
		n.next = next.next;
		return true;
	}
	
	public static void main(String[] args) {
		Solution03 s3 = new Solution03();
		Node head = new Node(1);
		Node second = new Node(2);
		Node third = new Node(3);
		Node four = new Node(4);
		head.next = second;
		second.next = third;
		third.next = four;
		System.out.print("Original list is: ");
		head.printLList();
		System.out.println("Passing 2 as middle node:");
		s3.deleteNode(second);
		System.out.print("The list is now:");
		head.printLList();
	}

}
